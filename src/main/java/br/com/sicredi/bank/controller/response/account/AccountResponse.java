package br.com.sicredi.bank.controller.response.account;

import br.com.sicredi.bank.entity.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private Long id;
    private AccountType type;
    private Integer agency;
    private Integer number;

}
