package com.bank.credit.controller.request.associate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveAssociateRequest {

    @NotNull(message = "Nome não pode ser nulo.")
    @NotBlank(message = "Nome não pode ficar em branco.")
    @Schema(example = "Jaciane Santana")
    private String name;

    @NotNull(message = "CPF não pode ser nulo.")
    @NotBlank(message = "CPF não pode ficar em branco.")
    @Schema(example = "012.345.678-90")
    private String cpf;

    @NotNull(message = "Data de nascimento não pode ser nula.")
    @NotBlank(message = "Data de nascimento não pode ficar em branco.")
    @Schema(example = "19-10-1993")
    private LocalDate birthDate;

    @NotNull(message = "Profissão não pode ser nulo.")
    @NotBlank(message = "Profissão não pode ficar em branco.")
    @Schema(example = "Engenheiro de Software")
    private String profession;

    @NotNull(message = "Salário não pode ser nulo.")
    @NotBlank(message = "Salário não pode ficar em branco.")
    @Schema(example = "4000,00")
    private BigDecimal salary;

}
