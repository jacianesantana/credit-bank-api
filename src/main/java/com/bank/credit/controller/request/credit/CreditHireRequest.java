package com.bank.credit.controller.request.credit;

import com.bank.credit.model.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditHireRequest {

    private Long associateId;
    private Long accountId;
    private ProductType productType;
    private Integer numberOfInstallments;
    private BigDecimal value;
}
