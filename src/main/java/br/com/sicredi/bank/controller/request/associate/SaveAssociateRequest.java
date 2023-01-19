package br.com.sicredi.bank.controller.request.associate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
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
    @NotBlank(message = "Nome não pode ser vazio.")
    @Schema(example = "Jaciane Santana")
    private String name;

    @NotNull(message = "CPF não pode ser nulo.")
    @NotBlank(message = "CPF não pode ser vazio.")
    @CPF(message = "CPF inválido.")
    @Schema(example = "01234567890")
    private String cpf;

    @NotNull(message = "Data de nascimento não pode ser nula.")
    @Schema(example = "1993-10-19")
    private LocalDate birthDate;

    @NotNull(message = "Telefone não pode ser nulo.")
    @NotBlank(message = "Telefone não pode ser vazio.")
    @Schema(example = "71999999999")
    private String phone;

    @NotNull(message = "Email não pode ser nulo.")
    @NotBlank(message = "Email não pode ser vazio.")
    @Email(message = "Email inválido.", regexp = ".+[@].+[\\.].+")
    @Schema(example = "nome.sobrenome@mail.com")
    private String email;

    @NotNull(message = "Profissão não pode ser nulo.")
    @NotBlank(message = "Profissão não pode ser vazio.")
    @Schema(example = "Engenheiro de Software")
    private String profession;

    @NotNull(message = "Salário não pode ser nulo.")
    @Min(value = 1, message = "Salário inválido.")
    @Schema(example = "4000.00")
    private BigDecimal salary;

}
