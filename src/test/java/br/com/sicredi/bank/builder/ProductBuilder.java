package br.com.sicredi.bank.builder;

import br.com.sicredi.bank.controller.request.product.ProductRequest;
import br.com.sicredi.bank.entity.ProductEntity;
import br.com.sicredi.bank.entity.enums.ProductType;

import java.math.BigDecimal;

public class ProductBuilder {

    public static ProductEntity buildProductEntity() {
        return ProductEntity.builder()
                .id(1L)
                .type(ProductType.PESSOAL)
                .taxes(ProductType.PESSOAL.getTaxes())
                .build();
    }

    public static ProductRequest buildProductRequest() {
        return ProductRequest.builder()
                .salary(BigDecimal.valueOf(10000))
                .build();
    }
}
