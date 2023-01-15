package br.com.sicredi.bank.service;

import br.com.sicredi.bank.controller.request.product.ProductRequest;
import br.com.sicredi.bank.controller.response.product.ProductResponse;
import br.com.sicredi.bank.entity.ProductEntity;
import br.com.sicredi.bank.entity.enums.ProductType;
import br.com.sicredi.bank.exception.BusinessRulesException;
import br.com.sicredi.bank.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity findByType(ProductType productType) {
        return productRepository.findByType(productType).orElseThrow();
    }

    public ProductResponse consigned(ProductRequest request) {
        var plotsAvailable = List.of(6, 12, 18, 24, 36);

        if (request.getSalary().compareTo(BigDecimal.valueOf(3000)) >= 0) {
            return ProductResponse.builder()
                    .type(ProductType.CONSIGNADO)
                    .taxes(ProductType.CONSIGNADO.getTaxes())
                    .firstPaymentDate(LocalDate.now().plusMonths(1))
                    .plotsAvailable(plotsAvailable)
                    .minValue(BigDecimal.valueOf(1000))
                    .maxValue(BigDecimal.valueOf(40000))
                    .build();
        }

        throw new BusinessRulesException("Salário abaixo do permitido para contratar este produto");
    }

    public ProductResponse financing(ProductRequest request) {
        var plotsAvailable = List.of(180, 240, 360);

        if (request.getSalary().compareTo(BigDecimal.valueOf(5000)) >= 0) {
            return ProductResponse.builder()
                    .type(ProductType.FINANCIAMENTO)
                    .taxes(ProductType.FINANCIAMENTO.getTaxes())
                    .firstPaymentDate(LocalDate.now().plusMonths(1))
                    .plotsAvailable(plotsAvailable)
                    .minValue(BigDecimal.valueOf(100000))
                    .maxValue(BigDecimal.valueOf(500000))
                    .build();
        }

        throw new BusinessRulesException("Salário abaixo do permitido para contratar este produto");
    }

    public ProductResponse personal(ProductRequest request) {
        var plotsAvailable = List.of(6, 12, 18, 24);

        if (request.getSalary().compareTo(BigDecimal.valueOf(1500)) >= 0) {
            return ProductResponse.builder()
                    .type(ProductType.PESSOAL)
                    .taxes(ProductType.PESSOAL.getTaxes())
                    .firstPaymentDate(LocalDate.now().plusMonths(1))
                    .plotsAvailable(plotsAvailable)
                    .minValue(BigDecimal.valueOf(1000))
                    .maxValue(BigDecimal.valueOf(20000))
                    .build();
        }

        throw new BusinessRulesException("Salário abaixo do permitido para contratar este produto");
    }

}
