package br.com.sicredi.bank.service;

import br.com.sicredi.bank.entity.ProductEntity;
import br.com.sicredi.bank.entity.enums.ProductType;
import br.com.sicredi.bank.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity findByType(ProductType productType) {
        return productRepository.findByType(productType).orElseThrow();
    }
}
