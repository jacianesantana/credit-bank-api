package br.com.sicredi.bank.mapper;

import br.com.sicredi.bank.model.response.contract.FindContractResponse;
import br.com.sicredi.bank.model.response.contract.ListContractResponse;
import br.com.sicredi.bank.model.entity.ContractEntity;
import br.com.sicredi.bank.model.response.contract.SaveContractResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContractMapper {

    public ListContractResponse contractToListContractsResponse(ContractEntity contractEntity) {
        return ListContractResponse.builder()
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

    public SaveContractResponse contractToSaveContractResponse(ContractEntity contract) {
        return SaveContractResponse.builder()
                .id(contract.getId())
                .productType(contract.getProduct().getType())
                .value(contract.getValue())
                .paidOff(contract.getPaidOff())
                .hireDate(contract.getHireDate())
                .expirationDate(contract.getExpirationDate())
                .installmentsPaid(contract.getInstallmentsPaid())
                .installmentsRemaining(contract.getInstallmentsRemaining())
                .firstPaymentDate(contract.getFirstPaymentDate())
                .build();
    }

}
