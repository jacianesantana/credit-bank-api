package br.com.sicredi.bank.service.transaction;

import br.com.sicredi.bank.model.entity.AccountEntity;
import br.com.sicredi.bank.model.entity.TransactionEntity;
import br.com.sicredi.bank.model.enums.TransactionType;
import br.com.sicredi.bank.exception.FindEntityException;
import br.com.sicredi.bank.exception.InsufficientBalanceException;
import br.com.sicredi.bank.exception.SaveEntityException;
import br.com.sicredi.bank.mapper.AccountMapper;
import br.com.sicredi.bank.mapper.TransactionMapper;
import br.com.sicredi.bank.repository.TransactionRepository;
import br.com.sicredi.bank.service.account.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static br.com.sicredi.bank.builder.AccountBuilder.*;
import static br.com.sicredi.bank.builder.TransactionBuilder.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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
        when(transactionMapper.toTransaction(any(), any(AccountEntity.class), any(TransactionType.class),
                any(BigDecimal.class))).thenReturn(transaction);
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transaction);
        when(accountMapper.accountToAccountResponse(any(AccountEntity.class))).thenReturn(accountResponse);

        var response = transactionService.deposit(transactionRequest).getBody();

        assertNotNull(response);
        assertEquals(newBalance, response.getNewBalance());
    }

    @Test
    void depositThrowsSaveEntityException() {
        var account = buildAccount();
        var transactionRequest = buildDepositTransactionRequest();

        when(accountService.findByAgencyAndNumber(anyInt(), anyInt())).thenReturn(account);
        doThrow(RuntimeException.class).when(accountService).save(any(AccountEntity.class));

        assertThrows(SaveEntityException.class, () -> transactionService.deposit(transactionRequest).getBody());
    }

    @Test
    void depositShouldReturnFindEntityException() {
        var transactionRequest = buildDepositTransactionRequest();

        when(accountService.findByAgencyAndNumber(anyInt(), anyInt())).thenThrow(new FindEntityException("anyMessage"));

        assertThrows(FindEntityException.class, () -> transactionService.deposit(transactionRequest));
    }

    @Test
    void withdrawSuccess() {
        var account = buildAccount();
        var transactionRequest = buildWithdrawTransactionRequest();
        var transaction = buildTransaction(TransactionType.WITHDRAW);
        var accountResponse = buildAccountResponse();
        var newBalance = account.getBalance().subtract(transactionRequest.getValue());

        when(accountService.findByAgencyAndNumber(anyInt(), anyInt())).thenReturn(account);
        doNothing().when(accountService).save(any(AccountEntity.class));
        when(transactionMapper.toTransaction(any(AccountEntity.class), any(), any(TransactionType.class),
                any(BigDecimal.class))).thenReturn(transaction);
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transaction);
        when(accountMapper.accountToAccountResponse(any(AccountEntity.class))).thenReturn(accountResponse);

        var response = transactionService.withdraw(transactionRequest).getBody();

        assertNotNull(response);
        assertEquals(newBalance, response.getNewBalance());
    }

    @Test
    void withdrawShouldReturnSaveEntityException() {
        var account = buildAccount();
        var transactionRequest = buildWithdrawTransactionRequest();
        var transaction = buildTransaction(TransactionType.WITHDRAW);

        when(accountService.findByAgencyAndNumber(anyInt(), anyInt())).thenReturn(account);
        doNothing().when(accountService).save(any(AccountEntity.class));
        when(transactionMapper.toTransaction(any(AccountEntity.class), any(AccountEntity.class), any(TransactionType.class),
                any(BigDecimal.class))).thenReturn(transaction);
        when(transactionRepository.save(any(TransactionEntity.class))).thenThrow(new RuntimeException());

        assertThrows(SaveEntityException.class, () -> transactionService.withdraw(transactionRequest));
    }

    @Test
    void withdrawShouldReturnInsufficientBalanceException() {
        var account = buildAccount();
        account.setBalance(BigDecimal.valueOf(99));
        var transactionRequest = buildWithdrawTransactionRequest();

        when(accountService.findByAgencyAndNumber(anyInt(), anyInt())).thenReturn(account);

        assertThrows(InsufficientBalanceException.class, () -> transactionService.withdraw(transactionRequest));
    }

    @Test
    void withdrawShouldReturnFindEntityException() {
        var transactionRequest = buildWithdrawTransactionRequest();

        when(accountService.findByAgencyAndNumber(anyInt(), anyInt())).thenThrow(new FindEntityException("anyMessage"));

        assertThrows(FindEntityException.class, () -> transactionService.withdraw(transactionRequest));
    }

    @Test
    void transferSuccess() {
        var debitAccount = buildAccount();
        var creditAccount = buildOtherAccount();
        var transactionRequest = buildTransactionRequest();
        var transaction = buildTransaction(TransactionType.TRANSFER);
        var debitAccountResponse = buildAccountResponse();
        var newBalance = debitAccount.getBalance().subtract(transactionRequest.getValue());

        when(accountService.findByAgencyAndNumber(debitAccount.getAgency(), debitAccount.getNumber()))
                .thenReturn(debitAccount);
        when(accountService.findByAgencyAndNumber(creditAccount.getAgency(), creditAccount.getNumber()))
                .thenReturn(creditAccount);
        doNothing().when(accountService).save(any(AccountEntity.class));
        when(transactionMapper.toTransaction(any(AccountEntity.class), any(AccountEntity.class), any(TransactionType.class),
                any(BigDecimal.class))).thenReturn(transaction);
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transaction);
        when(accountMapper.accountToAccountResponse(any(AccountEntity.class))).thenReturn(debitAccountResponse);

        var response = transactionService.transfer(transactionRequest).getBody();

        assertNotNull(response);
        assertEquals(newBalance, response.getNewBalance());
    }

    @Test
    void transferThrowsSaveEntityException() {
        var debitAccount = buildAccount();
        var creditAccount = buildOtherAccount();
        var transactionRequest = buildTransactionRequest();

        when(accountService.findByAgencyAndNumber(debitAccount.getAgency(), debitAccount.getNumber()))
                .thenReturn(debitAccount);
        when(accountService.findByAgencyAndNumber(creditAccount.getAgency(), creditAccount.getNumber()))
                .thenReturn(creditAccount);
        doThrow(RuntimeException.class).when(accountService).save(any(AccountEntity.class));

        assertThrows(SaveEntityException.class, () -> transactionService.transfer(transactionRequest).getBody());
    }

    @Test
    void transferThrowsInsufficientBalanceException() {
        var debitAccount = buildAccount();
        debitAccount.setBalance(BigDecimal.ZERO);
        var creditAccount = buildOtherAccount();
        var transactionRequest = buildTransactionRequest();

        when(accountService.findByAgencyAndNumber(debitAccount.getAgency(), debitAccount.getNumber()))
                .thenReturn(debitAccount);
        when(accountService.findByAgencyAndNumber(creditAccount.getAgency(), creditAccount.getNumber()))
                .thenReturn(creditAccount);

        assertThrows(InsufficientBalanceException.class, () -> transactionService.transfer(transactionRequest).getBody());
    }

    @Test
    void transferShouldReturnFindEntityException() {
        var transactionRequest = buildTransactionRequest();

        when(accountService.findByAgencyAndNumber(anyInt(), anyInt())).thenThrow(new FindEntityException("any"));

        assertThrows(FindEntityException.class, () -> transactionService.transfer(transactionRequest));
    }

    @Test
    void findAllByAccountId() {
        when(transactionRepository.findByDebitAccount(any(AccountEntity.class)))
                .thenReturn(Collections.singletonList(new TransactionEntity()));
        when(transactionRepository.findByCreditAccount(any(AccountEntity.class))).thenReturn(List.of());

        var response = transactionService.findAllByAccountId(buildAccount());

        assertEquals(1, response.size());
    }
}
