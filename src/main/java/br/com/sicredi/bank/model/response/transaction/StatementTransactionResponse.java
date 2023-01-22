package br.com.sicredi.bank.model.response.transaction;

import br.com.sicredi.bank.model.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatementTransactionResponse {

    @Schema(example = "SAQUE")
    private TransactionType type;

    @Schema(example = "100.00")
    private BigDecimal value;

    @Schema(example = "2023-01-01")
    private LocalDateTime createdAt;

}
