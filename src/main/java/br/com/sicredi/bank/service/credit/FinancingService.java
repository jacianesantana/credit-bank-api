package br.com.sicredi.bank.service.credit;

import br.com.sicredi.bank.controller.request.credit.CreditOptionsRequest;
import br.com.sicredi.bank.controller.response.credit.CreditOptionsResponse;
import br.com.sicredi.bank.entity.enums.ProductType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class FinancingService {

    private static final BigDecimal MIN_SALARY = BigDecimal.valueOf(5000);
    private static final List<Integer> PLOTS_AVAILABLE = List.of(180, 240, 360);
    private static final BigDecimal MIN_VALUE = BigDecimal.valueOf(100000);
    private static final BigDecimal MAX_VALUE = BigDecimal.valueOf(500000);

    public CreditOptionsResponse options(CreditOptionsRequest request) {
        if (biggerThenMinimumSalary(request.getSalary()) && lessThanThirtyYears(request.getBirthDate())) {
            return CreditOptionsResponse.builder()
                    .product(ProductType.FINANCIAMENTO)
                    .taxes(ProductType.FINANCIAMENTO.getTaxes())
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

    private Boolean lessThanThirtyYears(LocalDate birthDate) {
        return birthDate.isAfter(LocalDate.now().minusYears(30));
    }

    private Boolean biggerThenMinimumSalary(BigDecimal salary) {
        return salary.compareTo(MIN_SALARY) > 0;
    }
}
