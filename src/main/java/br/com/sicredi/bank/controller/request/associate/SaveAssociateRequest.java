package br.com.sicredi.bank.controller.request.associate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @CPF(message = "CPF inválido.")
    @Schema(example = "01234567890")
    private String cpf;

    @NotNull(message = "Data de nascimento não pode ser nula.")
    @Schema(example = "1993-10-19")
    private LocalDate birthDate;

    @NotNull(message = "Profissão não pode ser nulo.")
    @NotBlank(message = "Profissão não pode ficar em branco.")
    @Schema(example = "Engenheiro de Software")
    private String profession;

    @NotNull(message = "Salário não pode ser nulo.")
    @Min(value = 1)
    @Schema(example = "4000.00")
    private BigDecimal salary;

}
