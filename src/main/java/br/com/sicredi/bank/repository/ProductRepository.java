package br.com.sicredi.bank.repository;

import br.com.sicredi.bank.model.entity.ProductEntity;
import br.com.sicredi.bank.model.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByType(ProductType productType);

}
