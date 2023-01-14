package br.com.sicredi.bank.service;

import br.com.sicredi.bank.controller.request.transaction.TransactionRequest;
import br.com.sicredi.bank.controller.response.transaction.TransactionResponse;
import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.enums.TransactionType;
import br.com.sicredi.bank.exception.FindEntityException;
import br.com.sicredi.bank.exception.InsufficientBalanceException;
import br.com.sicredi.bank.exception.SaveEntityException;
import br.com.sicredi.bank.mapper.AccountMapper;
import br.com.sicredi.bank.mapper.TransactionMapper;
import br.com.sicredi.bank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static br.com.sicredi.bank.entity.enums.TransactionType.DEPOSITO;
import static br.com.sicredi.bank.entity.enums.TransactionType.SAQUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public TransactionResponse deposit(TransactionRequest request) {
        var account = validateDeposit(request);
        return registerTransaction(account, request, DEPOSITO);
    }

    private AccountEntity validateDeposit(TransactionRequest request) {
        try {
            var account = accountService.findByAgencyAndNumber(request.getCreditAccount().getAgency(),
                    request.getCreditAccount().getNumber());
            var newBalance = account.getBalance().add(request.getValue());
            account.setBalance(newBalance);

            return account;
        } catch (Exception e) {
            throw new FindEntityException("Depósito não pode ser efetuado, conta não encontrada!");
        }
    }

    public TransactionResponse withdraw(TransactionRequest request) {
        var account = validateWithdraw(request);
        return registerTransaction(account, request, SAQUE);
    }

    private AccountEntity validateWithdraw(TransactionRequest request) {
        try {
            var account = accountService.findById(request.getDebitAccount().getId());

            if (account.getBalance().compareTo(request.getValue()) >= 0) {
                var newBalance = account.getBalance().subtract(request.getValue());
                account.setBalance(newBalance);

                return account;
            }

            throw new InsufficientBalanceException("Saldo insuficiente para realizar a transação");
        } catch (NoSuchElementException e) {
            throw new FindEntityException("Saque não pode ser efetuado, conta não encontrada!");
        }
    }

    public TransactionResponse transfer(TransactionRequest request) {
        var debitAccount = validateWithdraw(request);
        var sourceAccount = registerTransaction(debitAccount, request, SAQUE);

        var creditAccount = validateDeposit(request);
        registerTransaction(creditAccount, request, DEPOSITO);

        return sourceAccount;
    }

    private TransactionResponse registerTransaction(AccountEntity account, TransactionRequest request,
                                                    TransactionType type) {
        try {
            accountService.save(account);

            var transaction = transactionMapper.requestToTransaction(account, type, request.getValue());
            transactionRepository.save(transaction);

            return TransactionResponse.builder()
                    .account(accountMapper.accountToAccountResponse(account))
                    .newBalance(account.getBalance())
                    .build();
        } catch (Exception e) {
            throw new SaveEntityException("Falha interna ao finalizar a transação.");
        }
    }

}
