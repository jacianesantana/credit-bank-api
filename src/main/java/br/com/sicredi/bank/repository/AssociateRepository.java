package br.com.sicredi.bank.repository;

import br.com.sicredi.bank.entity.AssociateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociateRepository extends JpaRepository<AssociateEntity, Long> {

    Optional<AssociateEntity> findByCpf(String cpf);

}
