package br.com.sicredi.bank.mapper;

import br.com.sicredi.bank.controller.response.account.AccountResponse;
import br.com.sicredi.bank.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponse accountToAccountResponse(AccountEntity accountEntity) {
        return AccountResponse.builder()
                .type(accountEntity.getType())
                .agency(accountEntity.getAgency())
                .number(accountEntity.getNumber())
                .build();
    }

}
