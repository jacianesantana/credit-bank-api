package br.com.sicredi.bank.service;

import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.TransactionEntity;
import br.com.sicredi.bank.entity.enums.TransactionType;
import br.com.sicredi.bank.exception.FindEntityException;
import br.com.sicredi.bank.exception.InsufficientBalanceException;
import br.com.sicredi.bank.exception.SaveEntityException;
import br.com.sicredi.bank.mapper.AccountMapper;
import br.com.sicredi.bank.mapper.TransactionMapper;
import br.com.sicredi.bank.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static br.com.sicredi.bank.builder.AccountBuilder.buildAccount;
import static br.com.sicredi.bank.builder.AccountBuilder.buildAccountResponse;
import static br.com.sicredi.bank.builder.TransactionBuilder.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
        var transactionRequest = buildDepositTransactionRequest();
        var transaction = buildTransactionDeposit();
        var accountResponse = buildAccountResponse();
        var newBalance = account.getBalance().add(transactionRequest.getValue());

        when(accountService.findByAgencyAndNumber(anyInt(), anyInt())).thenReturn(account);
        doNothing().when(accountService).save(any(AccountEntity.class));
        when(transactionMapper.requestToTransaction(any(AccountEntity.class), any(TransactionType.class),
                any(BigDecimal.class))).thenReturn(transaction);
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transaction);
        when(accountMapper.accountToAccountResponse(any(AccountEntity.class))).thenReturn(accountResponse);

        var response = transactionService.deposit(transactionRequest);

        assertEquals(newBalance, response.getNewBalance());
    }

    @Test
    void depositShouldReturnFindEntityException() {
        var transactionRequest = buildDepositTransactionRequest();

        when(accountService.findByAgencyAndNumber(anyInt(), anyInt())).thenThrow(new RuntimeException());

        assertThrows(FindEntityException.class, () -> transactionService.deposit(transactionRequest));
    }

    @Test
    void withdrawSuccess() {
        var account = buildAccount();
        var transactionRequest = buildWithdrawTransactionRequest();
        var transaction = buildTransactionWithdraw();
        var accountResponse = buildAccountResponse();
        var newBalance = account.getBalance().subtract(transactionRequest.getValue());

        when(accountService.findById(anyLong())).thenReturn(account);
        doNothing().when(accountService).save(any(AccountEntity.class));
        when(transactionMapper.requestToTransaction(any(AccountEntity.class), any(TransactionType.class),
                any(BigDecimal.class))).thenReturn(transaction);
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transaction);
        when(accountMapper.accountToAccountResponse(any(AccountEntity.class))).thenReturn(accountResponse);

        var response = transactionService.withdraw(transactionRequest);

        assertEquals(newBalance, response.getNewBalance());
    }

    @Test
    void withdrawShouldReturnSaveEntityException() {
        var account = buildAccount();
        var transactionRequest = buildWithdrawTransactionRequest();
        var transaction = buildTransactionWithdraw();

        when(accountService.findById(anyLong())).thenReturn(account);
        doNothing().when(accountService).save(any(AccountEntity.class));
        when(transactionMapper.requestToTransaction(any(AccountEntity.class), any(TransactionType.class),
                any(BigDecimal.class))).thenReturn(transaction);
        when(transactionRepository.save(any(TransactionEntity.class))).thenThrow(new RuntimeException());

        assertThrows(SaveEntityException.class, () -> transactionService.withdraw(transactionRequest));
    }

    @Test
    void withdrawShouldReturnInsufficientBalanceException() {
        var account = buildAccount();
        account.setBalance(BigDecimal.valueOf(99));
        var transactionRequest = buildWithdrawTransactionRequest();

        when(accountService.findById(anyLong())).thenReturn(account);

        assertThrows(InsufficientBalanceException.class, () -> transactionService.withdraw(transactionRequest));
    }

    @Test
    void withdrawShouldReturnFindEntityException() {
        var transactionRequest = buildWithdrawTransactionRequest();

        when(accountService.findById(anyLong())).thenThrow(new NoSuchElementException());

        assertThrows(FindEntityException.class, () -> transactionService.withdraw(transactionRequest));
    }

    @Test
    void transferSuccess() {
        var account = buildAccount();
        var transactionRequest = buildTransactionRequest();
        var transaction = buildTransactionWithdraw();
        var accountResponse = buildAccountResponse();
        var newBalance = account.getBalance().subtract(transactionRequest.getValue());

        when(accountService.findById(anyLong())).thenReturn(account);
        when(accountService.findByAgencyAndNumber(anyInt(), anyInt())).thenReturn(account);
        doNothing().when(accountService).save(any(AccountEntity.class));
        when(transactionMapper.requestToTransaction(any(AccountEntity.class), any(TransactionType.class),
                any(BigDecimal.class))).thenReturn(transaction);
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transaction);
        when(accountMapper.accountToAccountResponse(any(AccountEntity.class))).thenReturn(accountResponse);

        var response = transactionService.transfer(transactionRequest);

        assertEquals(newBalance, response.getNewBalance());
    }
}
