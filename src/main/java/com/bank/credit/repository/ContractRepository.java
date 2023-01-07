package com.bank.credit.repository;

import com.bank.credit.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Long, Contract> {
}
