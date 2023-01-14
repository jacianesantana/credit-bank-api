package br.com.sicredi.bank.service;

import br.com.sicredi.bank.builder.AccountBuilder;
import br.com.sicredi.bank.builder.TransactionBuilder;
import br.com.sicredi.bank.controller.request.transaction.TransactionRequest;
import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.TransactionEntity;
import br.com.sicredi.bank.entity.enums.TransactionType;
import br.com.sicredi.bank.mapper.AccountMapper;
import br.com.sicredi.bank.mapper.TransactionMapper;
import br.com.sicredi.bank.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @Mock
    private AccountService accountService;

    @Mock
    private AccountMapper accountMapper;

    @Test
    void depositSuccess() {
        var account = AccountBuilder.buildAccount();
        var transactionRequest = TransactionBuilder.buildTransactionRequest();
        var transaction = TransactionBuilder.buildTransactionDeposit();
        var accountResponse = AccountBuilder.buildAccountResponse();

        when(accountService.findById(anyLong())).thenReturn(account);
        doNothing().when(accountService).save(any(AccountEntity.class));
        when(transactionMapper.requestToTransaction(any(TransactionRequest.class), any(TransactionType.class)))
                .thenReturn(transaction);
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transaction);
        when(accountMapper.accountToAccountResponse(any(AccountEntity.class))).thenReturn(accountResponse);

        var response = transactionService.deposit(transactionRequest);

        assertEquals(transactionRequest.getAccount().getNumber(), response.getAccount().getNumber());
        assertEquals(transactionRequest.getAccount().getType(), response.getAccount().getType());
        assertNotEquals(transactionRequest.getAccount().getBalance(), response.getNewBalance());
    }

    @Test
    void withdrawSuccess() {
        var account = AccountBuilder.buildAccount();
        var transactionRequest = TransactionBuilder.buildTransactionRequest();
        var transaction = TransactionBuilder.buildTransactionWithdraw();
        var accountResponse = AccountBuilder.buildAccountResponse();

        when(accountService.findById(anyLong())).thenReturn(account);
        doNothing().when(accountService).save(any(AccountEntity.class));
        when(transactionMapper.requestToTransaction(any(TransactionRequest.class), any(TransactionType.class)))
                .thenReturn(transaction);
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transaction);
        when(accountMapper.accountToAccountResponse(any(AccountEntity.class))).thenReturn(accountResponse);

        var response = transactionService.deposit(transactionRequest);

        assertEquals(transactionRequest.getAccount().getNumber(), response.getAccount().getNumber());
        assertEquals(transactionRequest.getAccount().getType(), response.getAccount().getType());
        assertNotEquals(transactionRequest.getAccount().getBalance(), response.getNewBalance());
    }

}