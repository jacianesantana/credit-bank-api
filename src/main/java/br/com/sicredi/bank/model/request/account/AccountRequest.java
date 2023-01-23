package br.com.sicredi.bank.model.request.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static br.com.sicredi.bank.utils.MessageValidation.ACCOUNT_AGENCY_NOT_NULL;
import static br.com.sicredi.bank.utils.MessageValidation.ACCOUNT_NUMBER_NOT_NULL;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    @NotNull(message = ACCOUNT_AGENCY_NOT_NULL)
    @Schema(example = "1000")
    private Integer agency;

    @NotNull(message = ACCOUNT_NUMBER_NOT_NULL)
    @Schema(example = "12345678")
    private Integer number;

}
