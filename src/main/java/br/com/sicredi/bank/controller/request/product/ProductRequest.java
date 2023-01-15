package br.com.sicredi.bank.controller.request.product;

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
public class ProductRequest {

    @NotNull(message = "Tipo de Produto não pode ser nulo.")
    @Schema(example = "FINANCIAMENTO")
    private ProductType productType;

    @NotNull(message = "Salário não pode ser nulo.")
    @Schema(example = "5000.00")
    private BigDecimal salary;

}
