package com.bank.credit.controller.request.associate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAssociateRequest {

    @NotNull(message = "Profissão não pode ser nulo.")
    @NotBlank(message = "Profissão não pode ficar em branco.")
    @Schema(example = "Engenheiro de Software")
    private String profession;

    @NotNull(message = "Salário não pode ser nulo.")
    @NotBlank(message = "Salário não pode ficar em branco.")
    @Schema(example = "4000,00")
    private BigDecimal salary;

}
