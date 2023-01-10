package com.bank.credit.controller.response.credit;

import com.bank.credit.model.enums.ProductType;
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
public class CreditOptionsResponse {

    private ProductType product;
    private Integer taxes;
    private LocalDate firstPaymentDate;
    private List<Integer> plotsAvailable;
    private BigDecimal minValue;
    private BigDecimal maxValue;

}
