package br.com.sicredi.bank.service;

import br.com.sicredi.bank.controller.request.transaction.TransactionRequest;
import br.com.sicredi.bank.controller.response.transaction.TransactionResponse;
import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.enums.TransactionType;
import br.com.sicredi.bank.exception.InsufficientBalanceException;
import br.com.sicredi.bank.mapper.AccountMapper;
import br.com.sicredi.bank.mapper.TransactionMapper;
import br.com.sicredi.bank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        var account = accountService.findById(request.getAccount().getId());
        var newBalance = account.getBalance().add(request.getValue());
        account.setBalance(newBalance);

        return execute(account, request, DEPOSITO);
    }

    public TransactionResponse withdraw(TransactionRequest request) {
        var account = accountService.findById(request.getAccount().getId());

        if (account.getBalance().compareTo(request.getValue()) >= 0) {
            var newBalance = account.getBalance().subtract(request.getValue());
            account.setBalance(newBalance);

            return execute(account, request, SAQUE);
        }

        throw new InsufficientBalanceException("Saldo insuficiente para realizar a transação");
    }

    private TransactionResponse execute(AccountEntity account, TransactionRequest request, TransactionType type) {
        accountService.save(account);

        var transaction = transactionMapper.requestToTransaction(request, type);
        transactionRepository.save(transaction);

        return TransactionResponse.builder()
                .account(accountMapper.accountToAccountResponse(account))
                .newBalance(account.getBalance())
                .build();
    }
}
