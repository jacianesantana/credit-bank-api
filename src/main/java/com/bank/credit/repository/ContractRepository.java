package com.bank.credit.repository;

import com.bank.credit.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByAssociateId(Long id);
}
