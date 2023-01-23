package br.com.sicredi.bank.model.request.associate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static br.com.sicredi.bank.utils.MessageValidation.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAssociatePaycheckRequest {

    @NotNull(message = ASSOCIATE_PROFESSION_NOT_NULL)
    @NotBlank(message = ASSOCIATE_PROFESSION_NOT_BLANK)
    @Schema(example = "Engenheiro de Software")
    private String profession;

    @NotNull(message = ASSOCIATE_SALARY_NOT_NULL)
    @Min(value = 1, message = ASSOCIATE_SALARY_INVALID)
    @Schema(example = "4000.00")
    private BigDecimal salary;

}
