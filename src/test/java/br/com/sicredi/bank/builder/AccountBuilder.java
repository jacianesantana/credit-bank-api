package br.com.sicredi.bank.builder;

import br.com.sicredi.bank.model.response.account.AccountResponse;
import br.com.sicredi.bank.model.entity.AccountEntity;
import br.com.sicredi.bank.model.enums.AccountType;

import java.math.BigDecimal;

public class AccountBuilder {

    public static AccountEntity buildAccount() {
        return AccountEntity.builder()
                .id(1L)
                .associate(AssociateBuilder.buildAssociate())
                .type(AccountType.CORRENTE)
                .agency(1000)
                .number(12345678)
                .balance(BigDecimal.valueOf(1000))
                .build();
    }

    public static AccountEntity buildOtherAccount() {
        return AccountEntity.builder()
                .id(2L)
                .associate(AssociateBuilder.buildAssociate())
                .type(AccountType.POUPANCA)
                .agency(1234)
                .number(87654321)
                .balance(BigDecimal.valueOf(1000))
                .build();
    }

    public static AccountResponse buildAccountResponse() {
        return AccountResponse.builder()
                .type(AccountType.CORRENTE)
                .agency(1000)
                .number(12345678)
                .build();
    }

}
