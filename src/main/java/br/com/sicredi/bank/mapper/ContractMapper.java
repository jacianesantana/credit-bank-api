package br.com.sicredi.bank.mapper;

import br.com.sicredi.bank.controller.response.contract.ContractResponse;
import br.com.sicredi.bank.entity.ContractEntity;
import org.springframework.stereotype.Component;

@Component
public class ContractMapper {

    public ContractResponse contractToContractResponse(ContractEntity contractEntity) {
        return ContractResponse.builder()
                .id(contractEntity.getId())
                .paidOff(contractEntity.getPaidOff())
                .build();
    }

}
