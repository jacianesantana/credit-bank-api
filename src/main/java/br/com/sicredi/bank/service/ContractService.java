package br.com.sicredi.bank.service;

import br.com.sicredi.bank.entity.ContractEntity;
import br.com.sicredi.bank.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;

    public List<ContractEntity> findContracts(Long associateId) {
        log.info("Buscando contratos para o associado com id: {}", associateId);
        return contractRepository.findByIdAssociate(associateId);
    }

    public ContractEntity sign(ContractEntity contractEntity) {
        return contractRepository.save(contractEntity);
    }
}
