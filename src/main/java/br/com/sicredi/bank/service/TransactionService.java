package br.com.sicredi.bank.service;

import br.com.sicredi.bank.controller.request.transaction.DepositTransactionRequest;
import br.com.sicredi.bank.controller.request.transaction.TransferTransactionRequest;
import br.com.sicredi.bank.controller.request.transaction.WithdrawTransactionRequest;
import br.com.sicredi.bank.controller.response.transaction.TransactionResponse;
import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.TransactionEntity;
import br.com.sicredi.bank.exception.InsufficientBalanceException;
import br.com.sicredi.bank.exception.SaveEntityException;
import br.com.sicredi.bank.mapper.AccountMapper;
import br.com.sicredi.bank.mapper.TransactionMapper;
import br.com.sicredi.bank.repository.ContractRepository;
import br.com.sicredi.bank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.sicredi.bank.entity.enums.TransactionType.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public TransactionResponse deposit(DepositTransactionRequest request) {
        var account = accountService.findByAgencyAndNumber(request.getCreditAccount().getAgency(),
                request.getCreditAccount().getNumber());
        var newBalance = account.getBalance().add(request.getValue());
        account.setBalance(newBalance);

        try {
            accountService.save(account);

            var transaction = transactionMapper.toTransaction(null, account,
                    DEPOSITO, request.getValue());

            transactionRepository.save(transaction);

            return TransactionResponse.builder()
                    .account(accountMapper.accountToAccountResponse(account))
                    .newBalance(account.getBalance())
                    .build();
        } catch (Exception e) {
            throw new SaveEntityException("Falha interna ao finalizar a transação.");
        }
    }

    public TransactionResponse withdraw(WithdrawTransactionRequest request) {
        var account = accountService.findByAgencyAndNumber(request.getDebitAccount().getAgency(),
                request.getDebitAccount().getNumber());

        if (account.getBalance().compareTo(request.getValue()) >= 0) {
            var newBalance = account.getBalance().subtract(request.getValue());
            account.setBalance(newBalance);

            try {
                accountService.save(account);

                var transaction = transactionMapper.toTransaction(account, null,
                        SAQUE, request.getValue());

                transactionRepository.save(transaction);
            } catch (Exception e) {
                throw new SaveEntityException("Falha interna ao finalizar a transação.");
            }

            return TransactionResponse.builder()
                    .account(accountMapper.accountToAccountResponse(account))
                    .newBalance(account.getBalance())
                    .build();
        } else {
            throw new InsufficientBalanceException("Saldo insuficiente para realizar a transação");
        }
    }

    public TransactionResponse transfer(TransferTransactionRequest request) {
        var debitAccount = accountService.findByAgencyAndNumber(request.getDebitAccount().getAgency(),
                request.getDebitAccount().getNumber());
        var creditAccount = accountService.findByAgencyAndNumber(request.getCreditAccount().getAgency(),
                request.getCreditAccount().getNumber());

        if (debitAccount.getBalance().compareTo(request.getValue()) >= 0) {
            var newDebitAccountBalance = debitAccount.getBalance().subtract(request.getValue());
            debitAccount.setBalance(newDebitAccountBalance);

            var newCreditAccountBalance = creditAccount.getBalance().add(request.getValue());
            creditAccount.setBalance(newCreditAccountBalance);

            try {
                accountService.save(debitAccount);
                accountService.save(creditAccount);

                var transaction = transactionMapper.toTransaction(debitAccount, creditAccount,
                        TRANSFERENCIA, request.getValue());

                transactionRepository.save(transaction);
            } catch (Exception e) {
                throw new SaveEntityException("Falha interna ao finalizar a transação.");
            }

            return TransactionResponse.builder()
                    .account(accountMapper.accountToAccountResponse(debitAccount))
                    .newBalance(debitAccount.getBalance())
                    .build();
        } else {
            throw new InsufficientBalanceException("Saldo insuficiente para realizar a transação");
        }
    }

    public List<TransactionEntity> findAllByAccountId(AccountEntity account) {
        var debits = transactionRepository.findByDebitAccount(account);
        var credits = transactionRepository.findByCreditAccount(account);
        debits.addAll(credits);

        return debits;
    }
}
