package br.com.sicredi.bank.model.response.product;

import br.com.sicredi.bank.model.enums.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    @Schema(example = "FINANCING")
    private ProductType type;

    @Schema(example = "7")
    private Integer taxes;

    @Schema(example = "2022-02-01")
    private LocalDate firstPaymentDate;

    @Schema(example = "[12, 24, 36]")
    private List<Integer> plotsAvailable;

    @Schema(example = "1000.00")
    private BigDecimal minValue;

    @Schema(example = "100000.00")
    private BigDecimal maxValue;

}
