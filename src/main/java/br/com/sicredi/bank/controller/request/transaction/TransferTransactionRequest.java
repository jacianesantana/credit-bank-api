package br.com.sicredi.bank.controller.request.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferTransactionRequest {

    private DebitAccountRequest debitAccount;

    private CreditAccountRequest creditAccount;

    @NotNull(message = "Valor não pode ser nulo.")
    @Schema(example = "1000.00")
    @Min(value = 1)
    private BigDecimal value;

}
