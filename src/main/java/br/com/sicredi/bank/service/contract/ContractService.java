package br.com.sicredi.bank.service.contract;

import br.com.sicredi.bank.controller.response.contract.FindContractResponse;
import br.com.sicredi.bank.entity.AssociateEntity;
import br.com.sicredi.bank.entity.ContractEntity;
import br.com.sicredi.bank.exception.FindEntityException;
import br.com.sicredi.bank.mapper.ContractMapper;
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
    private final ContractMapper contractMapper;

    public List<ContractEntity> findContracts(AssociateEntity associate) {
        log.info("Buscando contratos para o associado com id: {}", associate.getId());
        return contractRepository.findByAssociate(associate);
    }

    public FindContractResponse findById(Long id) {
        log.info("Buscando contrato com id: {}", id);
        try {
            var contractEntity = contractRepository.findById(id).orElseThrow();
            return contractMapper.contractToFindContractResponse(contractEntity);
        } catch (Exception e) {
            log.error("Contrato não encontrado com o id: {}", id);
            throw new FindEntityException("Contrato não encontrado.");
        }
    }

}
