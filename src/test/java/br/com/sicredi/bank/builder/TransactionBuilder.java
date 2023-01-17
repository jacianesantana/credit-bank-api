package br.com.sicredi.bank.builder;

import br.com.sicredi.bank.controller.request.transaction.*;
import br.com.sicredi.bank.entity.TransactionEntity;
import br.com.sicredi.bank.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static br.com.sicredi.bank.builder.AccountBuilder.buildAccount;

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
                .id(1L)
                .balance(BigDecimal.valueOf(10000))
                .build();
    }

    public static CreditAccountRequest buildCreditAccountRequest() {
        return CreditAccountRequest.builder()
                .agency(1000)
                .number(12345678)
                .build();
    }

    public static TransactionEntity buildTransactionDeposit() {
        return TransactionEntity.builder()
                .id(1L)
                .type(TransactionType.DEPOSITO)
                .value(BigDecimal.valueOf(100))
                .debitAccount(buildAccount())
                .creditAccount(buildAccount())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static TransactionEntity buildTransactionWithdraw() {
        return TransactionEntity.builder()
                .id(1L)
                .type(TransactionType.SAQUE)
                .value(BigDecimal.valueOf(100))
                .debitAccount(buildAccount())
                .creditAccount(buildAccount())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
