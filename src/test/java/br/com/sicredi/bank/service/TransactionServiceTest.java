package br.com.sicredi.bank.service;

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

import java.math.BigDecimal;

import static br.com.sicredi.bank.builder.AccountBuilder.buildAccount;
import static br.com.sicredi.bank.builder.AccountBuilder.buildAccountResponse;
import static br.com.sicredi.bank.builder.TransactionBuilder.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
        var account = buildAccount();
        var transactionRequest = buildTransactionRequest();
        var transaction = buildTransactionDeposit();
        var accountResponse = buildAccountResponse();

        when(accountService.findByAgencyAndNumber(anyInt(), anyInt())).thenReturn(account);
        doNothing().when(accountService).save(any(AccountEntity.class));
        when(transactionMapper.requestToTransaction(any(AccountEntity.class), any(TransactionType.class),
                any(BigDecimal.class))).thenReturn(transaction);
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transaction);
        when(accountMapper.accountToAccountResponse(any(AccountEntity.class))).thenReturn(accountResponse);

        var response = transactionService.deposit(transactionRequest);

        assertEquals(transactionRequest.getCreditAccount().getNumber(), response.getAccount().getNumber());
    }

    @Test
    void withdrawSuccess() {
        var account = buildAccount();
        var transactionRequest = buildTransactionRequest();
        var transaction = buildTransactionWithdraw();
        var accountResponse = buildAccountResponse();

        when(accountService.findByAgencyAndNumber(anyInt(), anyInt())).thenReturn(account);
        doNothing().when(accountService).save(any(AccountEntity.class));
        when(transactionMapper.requestToTransaction(any(AccountEntity.class), any(TransactionType.class),
                any(BigDecimal.class))).thenReturn(transaction);
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transaction);
        when(accountMapper.accountToAccountResponse(any(AccountEntity.class))).thenReturn(accountResponse);

        var response = transactionService.deposit(transactionRequest);

        assertNotEquals(transactionRequest.getDebitAccount().getBalance(), response.getNewBalance());
    }
}