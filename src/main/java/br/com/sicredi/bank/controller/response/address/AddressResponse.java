package br.com.sicredi.bank.controller.response.address;

import br.com.sicredi.bank.entity.AssociateEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "41999999")
    private String zipCode;

    @Schema(example = "Rua Silveira Martins")
    private String streetName;

    @Schema(example = "1010")
    private String number;

    @Schema(example = "Bloco 01, Apt 305")
    private String complement;

    @Schema(example = "Aracaju")
    private String city;

    @Schema(example = "Sergipe")
    private String state;

    @Schema(example = "Brasil")
    private String country;

}
