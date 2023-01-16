package br.com.sicredi.bank.controller.request.contract;

import br.com.sicredi.bank.entity.enums.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequest {

    @NotNull(message = "Id do Associate não pode ser nulo.")
    private Long idAssociate;

    @NotNull(message = "Tipo de Produto não pode ser nulo.")
    @Schema(example = "FINANCIAMENTO")
    private ProductType productType;

    @NotNull(message = "Número de Prestações não pode ser nulo.")
    @Schema(example = "1")
    private Integer numberOfInstallments;

    @NotNull(message = "Valor não pode ser nulo.")
    @Schema(example = "10000.00")
    private BigDecimal value;

}
