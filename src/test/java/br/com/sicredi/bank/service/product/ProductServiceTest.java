package br.com.sicredi.bank.service.product;

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
    void findByTypeSuccess() {
        var product = buildProductEntity();

        when(productRepository.findByType(any(ProductType.class))).thenReturn(Optional.of(product));

        var response = productService.findByType(ProductType.PESSOAL);

        assertEquals(ProductType.PESSOAL.getTaxes(), response.getTaxes());
    }

    @Test
    void listProductsWithSalary1500Success() {
        var salary = BigDecimal.valueOf(1500);

        var response = productService.listProducts(salary);

        assertEquals(1, response.size());
    }

    @Test
    void listProductsWithSalary3000Success() {
        var salary = BigDecimal.valueOf(3000);

        var response = productService.listProducts(salary);

        assertEquals(2, response.size());
    }

    @Test
    void listProductsWithSalary5000Success() {
        var salary = BigDecimal.valueOf(5000);

        var response = productService.listProducts(salary);

        assertEquals(3, response.size());
    }

    @Test
    void listProductsShouldReturnBusinessRulesException() {
        var salary = BigDecimal.valueOf(1000);

        assertThrows(BusinessRulesException.class, () -> productService.listProducts(salary));
    }

}