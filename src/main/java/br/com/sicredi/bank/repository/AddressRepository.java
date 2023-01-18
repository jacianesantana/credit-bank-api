package br.com.sicredi.bank.repository;

import br.com.sicredi.bank.entity.AddressEntity;
import br.com.sicredi.bank.entity.AssociateEntity;
import br.com.sicredi.bank.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    Optional<ContractEntity> findByAssociate(AssociateEntity associate);

}
