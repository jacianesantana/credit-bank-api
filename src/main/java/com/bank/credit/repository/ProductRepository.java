package com.bank.credit.repository;

import com.bank.credit.model.Product;
import com.bank.credit.model.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByType(ProductType productType);
}
