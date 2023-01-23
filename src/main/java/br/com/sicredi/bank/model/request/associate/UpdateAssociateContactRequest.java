package br.com.sicredi.bank.model.request.associate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static br.com.sicredi.bank.utils.MessageValidation.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAssociateContactRequest {

    @NotNull(message = ASSOCIATE_PHONE_NOT_NULL)
    @NotBlank(message = ASSOCIATE_PHONE_NOT_BLANK)
    @Schema(example = "71999999999")
    private String phone;

    @NotNull(message = ASSOCIATE_EMAIL_NOT_NULL)
    @Email(message = ASSOCIATE_EMAIL_INVALID, regexp = ".+[@].+[\\.].+")
    @Schema(example = "nome.sobrenome@mail.com")
    private String email;

}
