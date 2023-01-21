package br.com.sicredi.bank.model.response.transaction;

import br.com.sicredi.bank.model.response.account.AccountResponse;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "1000.00")
    private BigDecimal newBalance;

}
