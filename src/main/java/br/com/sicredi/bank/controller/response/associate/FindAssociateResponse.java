package br.com.sicredi.bank.controller.response.associate;

import br.com.sicredi.bank.controller.response.account.AccountResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindAssociateResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Jaciane Santana")
    private String name;

    @Schema(example = "012.345.678-90")
    private String cpf;

    @Schema(example = "1993-10-19")
    private LocalDate birthDate;

    @Schema(example = "Engenheiro de Software")
    private String profession;

    @Schema(example = "4000.00")
    private BigDecimal salary;

    @Schema(example = "2022-01-10")
    private LocalDate lastPaycheck;

    private List<AccountResponse> accounts;

}
