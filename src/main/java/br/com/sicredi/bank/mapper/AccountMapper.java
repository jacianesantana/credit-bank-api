package br.com.sicredi.bank.mapper;

import br.com.sicredi.bank.model.response.account.AccountResponse;
import br.com.sicredi.bank.model.response.account.BalanceAccountResponse;
import br.com.sicredi.bank.model.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponse accountToAccountResponse(AccountEntity accountEntity) {
        return AccountResponse.builder()
                .id(accountEntity.getId())
                .type(accountEntity.getType())
                .agency(accountEntity.getAgency())
                .number(accountEntity.getNumber())
                .build();
    }

    public BalanceAccountResponse accountToBalanceAccountResponse(AccountEntity accountEntity) {
        return BalanceAccountResponse.builder()
                .balance(accountEntity.getBalance())
                .build();
    }

}
