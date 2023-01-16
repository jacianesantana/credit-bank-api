package br.com.sicredi.bank.mapper;

import br.com.sicredi.bank.controller.response.contract.FindContractResponse;
import br.com.sicredi.bank.controller.response.contract.ListContractsResponse;
import br.com.sicredi.bank.entity.ContractEntity;
import org.springframework.stereotype.Component;

@Component
public class ContractMapper {

    public ListContractsResponse contractToListContractsResponse(ContractEntity contractEntity) {
        return ListContractsResponse.builder()
                .id(contractEntity.getId())
                .paidOff(contractEntity.getPaidOff())
                .build();
    }

    public FindContractResponse contractToFindContractResponse(ContractEntity contractEntity) {
        return FindContractResponse.builder()
                .id(contractEntity.getId())
                .associate(contractEntity.getAssociate())
                .product(contractEntity.getProduct())
                .value(contractEntity.getValue())
                .paidOff(contractEntity.getPaidOff())
                .hireDate(contractEntity.getHireDate())
                .expirationDate(contractEntity.getExpirationDate())
                .installmentsPaid(contractEntity.getInstallmentsPaid())
                .installmentsRemaining(contractEntity.getInstallmentsRemaining())
                .firstPaymentDate(contractEntity.getFirstPaymentDate())
                .build();
    }

}
