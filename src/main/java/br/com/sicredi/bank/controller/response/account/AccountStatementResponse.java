package br.com.sicredi.bank.controller.response.account;

import br.com.sicredi.bank.controller.response.transaction.StatementTransaction;
import br.com.sicredi.bank.entity.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatementResponse {

    @Schema(example = "CORRENTE")
    private AccountType type;

    @Schema(example = "1000")
    private Integer agency;

    @Schema(example = "12345678")
    private Integer number;

    @Schema(example = "100.00")
    private BigDecimal balance;

    private List<StatementTransaction> transactions;

}
