package br.com.sicredi.bank.builder;

import br.com.sicredi.bank.controller.response.account.AccountResponse;
import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.enums.AccountType;

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

    public static AccountResponse buildAccountResponse() {
        return AccountResponse.builder()
                .type(AccountType.CORRENTE)
                .agency(1000)
                .number(12345678)
                .build();
    }

}
