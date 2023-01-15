package br.com.sicredi.bank.controller.response.transaction;

import br.com.sicredi.bank.controller.response.account.AccountResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private AccountResponse account;
    private BigDecimal newBalance;

}
