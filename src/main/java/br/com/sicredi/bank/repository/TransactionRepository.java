package br.com.sicredi.bank.repository;

import br.com.sicredi.bank.model.entity.AccountEntity;
import br.com.sicredi.bank.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByCreditAccount(AccountEntity creditAccount);

    List<TransactionEntity> findByDebitAccount(AccountEntity debitAccount);

}
