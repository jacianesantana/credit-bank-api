package br.com.sicredi.bank.controller.request.associate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAssociateContactRequest {

    @NotNull(message = "Telefone não pode ser nulo.")
    @NotBlank(message = "Telefone não pode ficar em branco.")
    @Schema(example = "71999999999")
    private String phone;

    @NotNull(message = "Email não pode ser nulo.")
    @NotBlank(message = "Email não pode ficar em branco.")
    @Email(message = "Email inválido.")
    @Schema(example = "nome.sobrenome@mail.com")
    private String email;

}
