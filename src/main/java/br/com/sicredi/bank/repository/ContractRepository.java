package br.com.sicredi.bank.repository;

import br.com.sicredi.bank.model.entity.AssociateEntity;
import br.com.sicredi.bank.model.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Long> {

    List<ContractEntity> findByAssociate(AssociateEntity associate);

}
