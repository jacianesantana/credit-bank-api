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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        var salary = BigDecimal.valueOf(3000);

        var response = productService.consigned(salary);

        assertEquals(ProductType.CONSIGNADO.getTaxes(), response.getTaxes());
    }

    @Test
    void consignedShouldReturnBusinessRulesException() {
        var salary = BigDecimal.valueOf(2999);

        assertThrows(BusinessRulesException.class, () -> productService.consigned(salary));
    }

    @Test
    void financingSuccess() {
        var salary = BigDecimal.valueOf(5000);

        var response = productService.financing(salary);

        assertEquals(ProductType.FINANCIAMENTO.getTaxes(), response.getTaxes());
    }

    @Test
    void financingShouldReturnBusinessRulesException() {
        var salary = BigDecimal.valueOf(4999);

        assertThrows(BusinessRulesException.class, () -> productService.financing(salary));
    }

    @Test
    void personalSuccess() {
        var salary = BigDecimal.valueOf(1500);

        var response = productService.personal(salary);

        assertEquals(ProductType.PESSOAL.getTaxes(), response.getTaxes());
    }

    @Test
    void personalShouldReturnBusinessRulesException() {
        var salary = BigDecimal.valueOf(1499);

        assertThrows(BusinessRulesException.class, () -> productService.personal(salary));
    }

}