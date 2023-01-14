package br.com.sicredi.bank.mapper;

import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.TransactionEntity;
import br.com.sicredi.bank.entity.enums.TransactionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static br.com.sicredi.bank.entity.enums.TransactionType.DEPOSITO;
import static br.com.sicredi.bank.entity.enums.TransactionType.SAQUE;

@Component
public class TransactionMapper {

    public TransactionEntity requestToTransaction(AccountEntity account, TransactionType type, BigDecimal value) {
        var transaction = TransactionEntity.builder()
                .type(type)
                .value(value)
                .createdAt(LocalDateTime.now());

        if (type.equals(DEPOSITO)) {
            transaction.creditAccount(account);
        } else if (type.equals(SAQUE)) {
            transaction.debitAccount(account);
        }

        return transaction.build();
    }
}
