package br.com.sicredi.bank.builder;

import br.com.sicredi.bank.controller.request.transaction.*;
import br.com.sicredi.bank.controller.response.transaction.StatementTransaction;
import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.TransactionEntity;
import br.com.sicredi.bank.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static br.com.sicredi.bank.builder.AccountBuilder.buildAccount;
import static br.com.sicredi.bank.builder.AccountBuilder.buildOtherAccount;

public class TransactionBuilder {

    public static TransferTransactionRequest buildTransactionRequest() {
        return TransferTransactionRequest.builder()
                .debitAccount(buildDebitAccountRequest())
                .creditAccount(buildCreditAccountRequest())
                .value(BigDecimal.valueOf(100))
                .build();
    }

    public static DepositTransactionRequest buildDepositTransactionRequest() {
        return DepositTransactionRequest.builder()
                .creditAccount(buildCreditAccountRequest())
                .value(BigDecimal.valueOf(100))
                .build();
    }

    public static WithdrawTransactionRequest buildWithdrawTransactionRequest() {
        return WithdrawTransactionRequest.builder()
                .debitAccount(buildDebitAccountRequest())
                .value(BigDecimal.valueOf(100))
                .build();
    }

    public static DebitAccountRequest buildDebitAccountRequest() {
        return DebitAccountRequest.builder()
                .agency(1000)
                .number(12345678)
                .build();
    }

    public static CreditAccountRequest buildCreditAccountRequest() {
        return CreditAccountRequest.builder()
                .agency(1234)
                .number(87654321)
                .build();
    }

    public static TransactionEntity buildTransactionDeposit() {
        return TransactionEntity.builder()
                .id(1L)
                .type(TransactionType.DEPOSITO)
                .value(BigDecimal.valueOf(100))
                .debitAccount(buildAccount())
                .creditAccount(buildOtherAccount())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static TransactionEntity buildTransaction(TransactionType type) {
        return TransactionEntity.builder()
                .id(1L)
                .type(type)
                .value(BigDecimal.valueOf(100))
                .debitAccount(buildAccount())
                .creditAccount(buildOtherAccount())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static StatementTransaction buildStatementTransaction() {
        return StatementTransaction.builder()
                .type(TransactionType.TRANSFERENCIA)
                .value(BigDecimal.valueOf(100))
                .createdAt(LocalDateTime.now())
                .build();
    }

}
