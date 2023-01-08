package com.bank.credit.service.credit;

import com.bank.credit.controller.request.credit.CreditOptionsRequest;
import com.bank.credit.controller.response.credit.CreditOptionsResponse;
import com.bank.credit.model.enums.ProductType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class PersonalLoanService {

    private static final BigDecimal MIN_SALARY = BigDecimal.valueOf(1500);
    private static final List<Integer> PLOTS_AVAILABLE = List.of(6, 12, 18, 24);
    private static final BigDecimal MIN_VALUE = BigDecimal.valueOf(1000);
    private static final BigDecimal MAX_VALUE = BigDecimal.valueOf(20000);

    public CreditOptionsResponse options(CreditOptionsRequest request) {
        if (biggerThenMinimumSalary(request.getSalary())) {
            return CreditOptionsResponse.builder()
                    .product(ProductType.PERSONAL)
                    .taxes(ProductType.PERSONAL.getTaxes())
                    .firstPaymentDate(LocalDate.now().plusMonths(1))
                    .plotsAvailable(PLOTS_AVAILABLE)
                    .minValue(MIN_VALUE)
                    .maxValue(MAX_VALUE)
                    .build();
        }

        return CreditOptionsResponse.builder()
                .plotsAvailable(List.of())
                .build();
    }

    private Boolean biggerThenMinimumSalary(BigDecimal salary) {
        return salary.compareTo(MIN_SALARY) > 0;
    }
}
