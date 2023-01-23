package br.com.sicredi.bank.builder;

import br.com.sicredi.bank.model.entity.TransactionEntity;
import br.com.sicredi.bank.model.enums.TransactionType;
import br.com.sicredi.bank.model.request.account.AccountRequest;
import br.com.sicredi.bank.model.request.transaction.DepositTransactionRequest;
import br.com.sicredi.bank.model.request.transaction.TransferTransactionRequest;
import br.com.sicredi.bank.model.request.transaction.WithdrawTransactionRequest;
import br.com.sicredi.bank.model.response.transaction.StatementTransactionResponse;

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

    public static AccountRequest buildDebitAccountRequest() {
        return AccountRequest.builder()
                .agency(1000)
                .number(12345678)
                .build();
    }

    public static AccountRequest buildCreditAccountRequest() {
        return AccountRequest.builder()
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

    public static StatementTransactionResponse buildStatementTransaction() {
        return StatementTransactionResponse.builder()
                .type(TransactionType.TRANSFERENCIA)
                .value(BigDecimal.valueOf(100))
                .createdAt(LocalDateTime.now())
                .build();
    }

}
