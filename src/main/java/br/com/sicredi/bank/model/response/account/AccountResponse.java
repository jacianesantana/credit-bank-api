package br.com.sicredi.bank.model.response.account;

import br.com.sicredi.bank.model.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "CORRENTE")
    private AccountType type;

    @Schema(example = "1000")
    private Integer agency;

    @Schema(example = "12345678")
    private Integer number;

}
