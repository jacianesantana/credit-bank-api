package br.com.sicredi.bank.model.request.associate;

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

import static br.com.sicredi.bank.utils.MessageValidation.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveAssociateRequest {

    @NotNull(message = ASSOCIATE_NAME_NOT_NULL)
    @NotBlank(message = ASSOCIATE_NAME_NOT_BLANK)
    @Schema(example = "Jaciane Santana")
    private String name;

    @NotNull(message = ASSOCIATE_CPF_NOT_NULL)
    @CPF(message = ASSOCIATE_CPF_INVALID)
    @Schema(example = "01234567890")
    private String cpf;

    @NotNull(message = ASSOCIATE_BIRTH_DATE_NOT_NULL)
    @Schema(example = "1993-10-19")
    private LocalDate birthDate;

    @NotNull(message = ASSOCIATE_PHONE_NOT_NULL)
    @NotBlank(message = ASSOCIATE_PHONE_NOT_BLANK)
    @Schema(example = "71999999999")
    private String phone;

    @NotNull(message = ASSOCIATE_EMAIL_NOT_NULL)
    @Email(message = ASSOCIATE_EMAIL_INVALID, regexp = ".+[@].+[\\.].+")
    @Schema(example = "nome.sobrenome@mail.com")
    private String email;

    @NotNull(message = ASSOCIATE_PROFESSION_NOT_NULL)
    @NotBlank(message = ASSOCIATE_PROFESSION_NOT_BLANK)
    @Schema(example = "Engenheiro de Software")
    private String profession;

    @NotNull(message = ASSOCIATE_SALARY_NOT_NULL)
    @Min(value = 1, message = ASSOCIATE_SALARY_INVALID)
    @Schema(example = "4000.00")
    private BigDecimal salary;

}
