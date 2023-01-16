package br.com.sicredi.bank.controller.response.contract;

import br.com.sicredi.bank.entity.AssociateEntity;
import br.com.sicredi.bank.entity.ProductEntity;
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

    private BigDecimal value;

    @Schema(example = "false")
    private Boolean paidOff;

    private LocalDate hireDate;

    private LocalDate expirationDate;

    private Integer installmentsPaid;

    private Integer installmentsRemaining;

    private LocalDate firstPaymentDate;

}
