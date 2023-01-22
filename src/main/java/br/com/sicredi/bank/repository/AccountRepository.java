package br.com.sicredi.bank.repository;

import br.com.sicredi.bank.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByAgencyAndNumber(Integer agency, Integer number);

}
