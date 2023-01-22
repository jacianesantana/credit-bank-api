package br.com.sicredi.bank.model.response.account;

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
public class BalanceAccountResponse {

    @Schema(example = "5000.00")
    private BigDecimal balance;

}
