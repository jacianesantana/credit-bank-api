package br.com.sicredi.bank.builder;

import br.com.sicredi.bank.controller.request.transaction.TransactionRequest;
import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.TransactionEntity;
import br.com.sicredi.bank.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionBuilder {

    public static TransactionRequest buildTransactionRequest() {
        return TransactionRequest.builder()
                .account(AccountBuilder.buildAccount())
                .value(BigDecimal.valueOf(100))
                .build();
    }

    public static TransactionEntity buildTransactionDeposit() {
        return TransactionEntity.builder()
                .id(1L)
                .type(TransactionType.DEPOSITO)
                .value(BigDecimal.valueOf(100))
                .account(AccountBuilder.buildAccount())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static TransactionEntity buildTransactionWithdraw() {
        return TransactionEntity.builder()
                .id(1L)
                .type(TransactionType.SAQUE)
                .value(BigDecimal.valueOf(100))
                .account(AccountBuilder.buildAccount())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
