package br.com.sicredi.bank.model.request.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    @NotNull(message = "Agência não pode ser nula.")
    @Schema(example = "1000")
    private Integer agency;

    @NotNull(message = "Número da Conta não pode ser nulo.")
    @Schema(example = "12345678")
    private Integer number;

}
