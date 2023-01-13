package br.com.sicredi.bank.mapper;

import br.com.sicredi.bank.controller.request.transaction.TransactionRequest;
import br.com.sicredi.bank.entity.TransactionEntity;
import br.com.sicredi.bank.entity.enums.TransactionType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionMapper {

    public TransactionEntity requestToTransaction(TransactionRequest request, TransactionType type) {
        return TransactionEntity.builder()
                .type(type)
                .value(request.getValue())
                .account(request.getAccount())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
