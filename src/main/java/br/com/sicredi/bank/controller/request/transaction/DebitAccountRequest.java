package br.com.sicredi.bank.controller.request.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DebitAccountRequest {

    @NotNull(message = "Id não pode ser nulo.")
    @Schema(example = "1")
    private Long id;

    @NotNull(message = "Saldo não pode ser nulo.")
    @Schema(example = "1000.00")
    private BigDecimal balance;

}
