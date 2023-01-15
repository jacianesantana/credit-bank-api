package br.com.sicredi.bank.service;

import br.com.sicredi.bank.entity.enums.ProductType;
import br.com.sicredi.bank.exception.BusinessRulesException;
import br.com.sicredi.bank.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.sicredi.bank.builder.ProductBuilder.buildProductEntity;
import static br.com.sicredi.bank.builder.ProductBuilder.buildProductRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void findByType() {
        var product = buildProductEntity();

        when(productRepository.findByType(any(ProductType.class))).thenReturn(Optional.of(product));

        var response = productService.findByType(ProductType.PESSOAL);

        assertEquals(ProductType.PESSOAL.getTaxes(), response.getTaxes());
    }

    @Test
    void consignedSuccess() {
        var request = buildProductRequest();
        request.setProductType(ProductType.CONSIGNADO);

        var response = productService.consigned(request);

        assertEquals(ProductType.CONSIGNADO.getTaxes(), response.getTaxes());
    }

    @Test
    void consignedShouldReturnBusinessRulesException() {
        var request = buildProductRequest();
        request.setProductType(ProductType.CONSIGNADO);
        request.setSalary(BigDecimal.valueOf(2999));

        assertThrows(BusinessRulesException.class, () -> productService.consigned(request));
    }

    @Test
    void financingSuccess() {
        var request = buildProductRequest();
        request.setProductType(ProductType.FINANCIAMENTO);

        var response = productService.financing(request);

        assertEquals(ProductType.FINANCIAMENTO.getTaxes(), response.getTaxes());
    }

    @Test
    void financingShouldReturnBusinessRulesException() {
        var request = buildProductRequest();
        request.setProductType(ProductType.FINANCIAMENTO);
        request.setSalary(BigDecimal.valueOf(4999));

        assertThrows(BusinessRulesException.class, () -> productService.financing(request));
    }

    @Test
    void personalSuccess() {
        var request = buildProductRequest();
        request.setProductType(ProductType.PESSOAL);

        var response = productService.personal(request);

        assertEquals(ProductType.PESSOAL.getTaxes(), response.getTaxes());
    }

    @Test
    void personalShouldReturnBusinessRulesException() {
        var request = buildProductRequest();
        request.setProductType(ProductType.PESSOAL);
        request.setSalary(BigDecimal.valueOf(1499));

        assertThrows(BusinessRulesException.class, () -> productService.personal(request));
    }
}