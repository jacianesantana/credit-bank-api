package com.bank.credit.controller.response.account;

import com.bank.credit.model.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private AccountType type;
    private Integer agency;
    private Integer number;
}
