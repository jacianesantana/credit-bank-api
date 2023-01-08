package com.bank.credit.controller.request.credit;

import com.bank.credit.model.enums.ProductType;
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
public class CreditOptionsRequest {

    private ProductType productType;
    private LocalDate birthDate;
    private BigDecimal salary;
}
