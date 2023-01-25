package br.com.sicredi.bank.model.request.contract;

import br.com.sicredi.bank.model.enums.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static br.com.sicredi.bank.utils.MessageValidation.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequest {

    @NotNull(message = CONTRACT_ID_ASSOCIATE_NOT_NULL)
    private Long idAssociate;

    @NotNull(message = CONTRACT_PRODUCT_TYPE_NOT_NULL)
    @Schema(example = "FINANCING")
    private ProductType productType;

    @NotNull(message = CONTRACT_NUMBER_OF_INSTALLMENTS_NOT_NULL)
    @Schema(example = "1")
    private Integer numberOfInstallments;

    @NotNull(message = CONTRACT_VALUE_NOT_NULL)
    @Min(value = 0, message = CONTRACT_VALUE_INVALID)
    @Schema(example = "10000.00")
    private BigDecimal value;

}
