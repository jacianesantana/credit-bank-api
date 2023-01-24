package br.com.sicredi.bank.mapper;

import br.com.sicredi.bank.model.response.transaction.StatementTransactionResponse;
import br.com.sicredi.bank.model.entity.AccountEntity;
import br.com.sicredi.bank.model.entity.TransactionEntity;
import br.com.sicredi.bank.model.enums.TransactionType;
import br.com.sicredi.bank.model.response.transaction.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static br.com.sicredi.bank.model.enums.TransactionType.*;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    private final AccountMapper accountMapper;

    public TransactionEntity toTransaction(AccountEntity debitAccount, AccountEntity creditAccount,
                                                  TransactionType type, BigDecimal value) {
        var transaction = TransactionEntity.builder()
                .type(type)
                .value(value)
                .createdAt(LocalDateTime.now());

        if (type.equals(DEPOSIT)) {
            transaction.creditAccount(creditAccount);
        } else if (type.equals(WITHDRAW)) {
            transaction.debitAccount(debitAccount);
        } else if (type.equals(TRANSFER)) {
            transaction.debitAccount(debitAccount);
            transaction.creditAccount(creditAccount);
        }

        return transaction.build();
    }

    public StatementTransactionResponse transactionToStatementTransaction(AccountEntity account, TransactionEntity transaction) {
        var isDebit = false;

        if (transaction.getDebitAccount() != null) {
            isDebit = account.getId().equals(transaction.getDebitAccount().getId());
        }

        var value = transaction.getValue();

        return StatementTransactionResponse.builder()
                .type(transaction.getType())
                .value(isDebit ? value.multiply(BigDecimal.valueOf(-1)) : value)
                .createdAt(transaction.getCreatedAt())
                .build();
    }

    public TransactionResponse toTransactionResponse(AccountEntity account) {
        return TransactionResponse.builder()
                .account(accountMapper.accountToAccountResponse(account))
                .newBalance(account.getBalance())
                .build();
    }

}
