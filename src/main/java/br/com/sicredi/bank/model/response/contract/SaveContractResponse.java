package br.com.sicredi.bank.model.response.contract;

import br.com.sicredi.bank.model.entity.AssociateEntity;
import br.com.sicredi.bank.model.entity.ProductEntity;
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

    private AssociateEntity associate;

    private ProductEntity product;

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
