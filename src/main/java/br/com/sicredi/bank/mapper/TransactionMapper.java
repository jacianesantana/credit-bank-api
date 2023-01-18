package br.com.sicredi.bank.mapper;

import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.TransactionEntity;
import br.com.sicredi.bank.entity.enums.TransactionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static br.com.sicredi.bank.entity.enums.TransactionType.*;

@Component
public class TransactionMapper {

    public TransactionEntity toTransaction(AccountEntity debitAccount, AccountEntity creditAccount,
                                                  TransactionType type, BigDecimal value) {
        var transaction = TransactionEntity.builder()
                .type(type)
                .value(value)
                .createdAt(LocalDateTime.now());

        if (type.equals(DEPOSITO)) {
            transaction.creditAccount(creditAccount);
        } else if (type.equals(SAQUE)) {
            transaction.debitAccount(debitAccount);
        } else if (type.equals(TRANSFERENCIA)) {
            transaction.debitAccount(debitAccount);
            transaction.creditAccount(creditAccount);
        }

        return transaction.build();
    }

}
