package br.com.sicredi.bank.service.product;

import br.com.sicredi.bank.exception.BusinessRulesException;
import br.com.sicredi.bank.model.entity.ProductEntity;
import br.com.sicredi.bank.model.enums.ProductType;
import br.com.sicredi.bank.model.response.product.ProductResponse;
import br.com.sicredi.bank.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static br.com.sicredi.bank.utils.Message.PRODUCT_BUSINESS_SALARY_ERROR;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity findByType(ProductType productType) {
        return productRepository.findByType(productType).orElseThrow();
    }

    public ResponseEntity<List<ProductResponse>> listProducts(BigDecimal salary) {
        var products = new ArrayList<ProductResponse>();

        if (salary.compareTo(BigDecimal.valueOf(1500)) < 0) {
            throw new BusinessRulesException(PRODUCT_BUSINESS_SALARY_ERROR);
        } else if ((salary.compareTo(BigDecimal.valueOf(1500)) >= 0) && (salary.compareTo(BigDecimal.valueOf(2999)) < 0)) {
            products.add(personal());
        } else if ((salary.compareTo(BigDecimal.valueOf(1500)) >= 0) && (salary.compareTo(BigDecimal.valueOf(4999)) < 0)) {
            products.add(personal());
            products.add(consigned());
        } else {
            products.add(personal());
            products.add(consigned());
            products.add(financing());
        }

        return ResponseEntity.ok(products);
    }

    private ProductResponse personal() {
        var plotsAvailable = List.of(6, 12, 18, 24);

        return ProductResponse.builder()
                .type(ProductType.PERSONAL)
                .taxes(ProductType.PERSONAL.getTaxes())
                .firstPaymentDate(LocalDate.now().plusMonths(1))
                .plotsAvailable(plotsAvailable)
                .minValue(BigDecimal.valueOf(1000))
                .maxValue(BigDecimal.valueOf(20000))
                .build();
    }

    private ProductResponse consigned() {
        var plotsAvailable = List.of(6, 12, 18, 24, 36, 48);

        return ProductResponse.builder()
                .type(ProductType.PAYROLL)
                .taxes(ProductType.PAYROLL.getTaxes())
                .firstPaymentDate(LocalDate.now().plusMonths(1))
                .plotsAvailable(plotsAvailable)
                .minValue(BigDecimal.valueOf(1000))
                .maxValue(BigDecimal.valueOf(40000))
                .build();
    }

    private ProductResponse financing() {
        var plotsAvailable = List.of(180, 240, 360);

        return ProductResponse.builder()
                .type(ProductType.FINANCING)
                .taxes(ProductType.FINANCING.getTaxes())
                .firstPaymentDate(LocalDate.now().plusMonths(1))
                .plotsAvailable(plotsAvailable)
                .minValue(BigDecimal.valueOf(100000))
                .maxValue(BigDecimal.valueOf(500000))
                .build();
    }

}
