package br.com.sicredi.bank.model.request.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static br.com.sicredi.bank.utils.MessageValidation.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @NotNull(message = ADDRESS_CEP_NOT_NULL)
    @NotBlank(message = ADDRESS_CEP_NOT_BLANK)
    @Schema(example = "41999999")
    private String zipCode;

    @NotNull(message = ADDRESS_STREET_NAME_NOT_NULL)
    @NotBlank(message = ADDRESS_STREET_NAME_NOT_BLANK)
    @Schema(example = "Rua Silveira Martins")
    private String streetName;

    @NotNull(message = ADDRESS_NUMBER_NOT_NULL)
    @NotBlank(message = ADDRESS_NUMBER_NOT_BLANK)
    @Schema(example = "1010")
    private String number;

    @Schema(example = "Bloco 01, Apt 305")
    private String complement;

    @NotNull(message = ADDRESS_CITY_NOT_NULL)
    @NotBlank(message = ADDRESS_CITY_NOT_BLANK)
    @Schema(example = "Aracaju")
    private String city;

    @NotNull(message = ADDRESS_STATE_NOT_NULL)
    @NotBlank(message = ADDRESS_STATE_NOT_BLANK)
    @Schema(example = "Sergipe")
    private String state;

    @NotNull(message = ADDRESS_COUNTRY_NOT_NULL)
    @NotBlank(message = ADDRESS_COUNTRY_NOT_BLANK)
    @Schema(example = "Brasil")
    private String country;

}
