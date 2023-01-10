package com.bank.credit.service;

import com.bank.credit.model.Product;
import com.bank.credit.model.enums.ProductType;
import com.bank.credit.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product findByType(ProductType productType) {
        return productRepository.findByType(productType).orElseThrow();
    }
}
