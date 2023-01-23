package br.com.sicredi.bank.model.response.contract;

import br.com.sicredi.bank.model.enums.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveContractResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "FINANCIAMENTO")
    private ProductType productType;

    @Schema(example = "1000.00")
    private BigDecimal value;

    @Schema(example = "false")
    private Boolean paidOff;

    @Schema(example = "2022-10-10")
    private LocalDate hireDate;

    @Schema(example = "2022-10-10")
    private LocalDate expirationDate;

    @Schema(example = "6")
    private Integer installmentsPaid;

    @Schema(example = "6")
    private Integer installmentsRemaining;

    @Schema(example = "2022-10-10")
    private LocalDate firstPaymentDate;

}
