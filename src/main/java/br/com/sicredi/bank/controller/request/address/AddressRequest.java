package br.com.sicredi.bank.controller.request.address;

import br.com.sicredi.bank.entity.AssociateEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @NotNull(message = "CEP não pode ser nulo.")
    @NotBlank(message = "CEP não pode ficar em branco.")
    @Schema(example = "41999999")
    private String zipCode;

    @NotNull(message = "Rua não pode ser nulo.")
    @NotBlank(message = "Rua não pode ficar em branco.")
    @Schema(example = "Rua Silveira Martins")
    private String streetName;

    @NotNull(message = "Número não pode ser nulo.")
    @NotBlank(message = "Número não pode ficar em branco.")
    @Schema(example = "1010")
    private String number;

    @Schema(example = "Bloco 01, Apt 305")
    private String complement;

    @NotNull(message = "Cidade não pode ser nulo.")
    @NotBlank(message = "Cidade não pode ficar em branco.")
    @Schema(example = "Aracaju")
    private String city;

    @NotNull(message = "Estado não pode ser nulo.")
    @NotBlank(message = "Estado não pode ficar em branco.")
    @Schema(example = "Sergipe")
    private String state;

    @NotNull(message = "País não pode ser nulo.")
    @NotBlank(message = "País não pode ficar em branco.")
    @Schema(example = "Brasil")
    private String country;

}
