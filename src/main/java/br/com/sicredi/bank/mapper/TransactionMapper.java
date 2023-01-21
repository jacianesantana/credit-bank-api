package br.com.sicredi.bank.mapper;

import br.com.sicredi.bank.model.response.transaction.StatementTransaction;
import br.com.sicredi.bank.model.entity.AccountEntity;
import br.com.sicredi.bank.model.entity.TransactionEntity;
import br.com.sicredi.bank.model.enums.TransactionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static br.com.sicredi.bank.model.enums.TransactionType.*;

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

    public StatementTransaction transactionToStatementTransaction(AccountEntity account, TransactionEntity transaction) {
        var isDebit = false;

        if (transaction.getDebitAccount() != null) {
            isDebit = account.getId().equals(transaction.getDebitAccount().getId());
        }

        var value = transaction.getValue();

        return StatementTransaction.builder()
                .type(transaction.getType())
                .value(isDebit ? value.multiply(BigDecimal.valueOf(-1)) : value)
                .createdAt(transaction.getCreatedAt())
                .build();
    }

}
