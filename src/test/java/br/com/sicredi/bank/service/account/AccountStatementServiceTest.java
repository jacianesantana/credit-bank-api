package br.com.sicredi.bank.service.account;

import br.com.sicredi.bank.builder.TransactionBuilder;
import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.TransactionEntity;
import br.com.sicredi.bank.mapper.TransactionMapper;
import br.com.sicredi.bank.service.transaction.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.sicredi.bank.builder.AccountBuilder.buildAccount;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountStatementServiceTest {

    @InjectMocks
    private AccountStatementService accountStatementService;

    @Mock
    private AccountService accountService;

    @Mock
    private TransactionService transactionService;

    @Mock
    private TransactionMapper transactionMapper;

    @Test
    void statementSuccecss() {
        var account = buildAccount();
        var statementTransaction = TransactionBuilder.buildStatementTransaction();

        when(accountService.findById(anyLong())).thenReturn(account);
        when(transactionService.findAllByAccountId(any(AccountEntity.class))).thenReturn(List.of());
        when(transactionMapper.transactionToStatementTransaction(any(AccountEntity.class), any(TransactionEntity.class)))
                .thenReturn(statementTransaction);

        var response = accountStatementService.statement(account.getId());

        assertEquals(account.getType(), response.getType());
        assertEquals(account.getAgency(), response.getAgency());
        assertEquals(account.getNumber(), response.getNumber());
        assertTrue(response.getTransactions().isEmpty());
    }

}