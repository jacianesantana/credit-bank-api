package br.com.sicredi.bank.builder;

import br.com.sicredi.bank.model.entity.ProductEntity;
import br.com.sicredi.bank.model.enums.ProductType;

public class ProductBuilder {

    public static ProductEntity buildProductEntity() {
        return ProductEntity.builder()
                .id(1L)
                .type(ProductType.PESSOAL)
                .taxes(ProductType.PESSOAL.getTaxes())
                .build();
    }

}
