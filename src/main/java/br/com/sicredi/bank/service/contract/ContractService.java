package br.com.sicredi.bank.service.contract;

import br.com.sicredi.bank.exception.FindEntityException;
import br.com.sicredi.bank.mapper.ContractMapper;
import br.com.sicredi.bank.model.entity.AssociateEntity;
import br.com.sicredi.bank.model.entity.ContractEntity;
import br.com.sicredi.bank.model.response.contract.FindContractResponse;
import br.com.sicredi.bank.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.sicredi.bank.utils.Message.CONTRACT_FIND_ERROR;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    public List<ContractEntity> findContracts(AssociateEntity associate) {
        return contractRepository.findByAssociate(associate);
    }

    public ResponseEntity<FindContractResponse> findById(Long id) {
        try {
            var contractEntity = contractRepository.findById(id).orElseThrow();
            var response = contractMapper.contractToFindContractResponse(contractEntity);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new FindEntityException(CONTRACT_FIND_ERROR);
        }
    }

}
