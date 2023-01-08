package com.bank.credit.service;

import com.bank.credit.model.Contract;
import com.bank.credit.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;

    public List<Contract> findContracts(Long associateId) {
        log.info("Buscando contratos para o associado com id: {}", associateId);
        return contractRepository.findByAssociateId(associateId);
    }

    public Contract sign(Contract contract) {
        return contractRepository.save(contract);
    }
}
