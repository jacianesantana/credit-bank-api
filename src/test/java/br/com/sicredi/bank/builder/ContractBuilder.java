package br.com.sicredi.bank.builder;

import br.com.sicredi.bank.model.request.contract.ContractRequest;
import br.com.sicredi.bank.model.entity.ContractEntity;
import br.com.sicredi.bank.model.enums.ProductType;
import br.com.sicredi.bank.model.response.contract.SaveContractResponse;

import java.math.BigDecimal;

import static br.com.sicredi.bank.builder.AssociateBuilder.buildAssociate;

public class ContractBuilder {

    public static ContractRequest buildContractRequest() {
        return ContractRequest.builder()
                .idAssociate(buildAssociate().getId())
                .productType(ProductType.PERSONAL)
                .numberOfInstallments(12)
                .value(BigDecimal.TEN)
                .build();
    }

    public static ContractEntity buildContractEntity() {
        return ContractEntity.builder()
                .id(1L)
                .paidOff(false)
                .build();
    }

    public static SaveContractResponse buildSaveContractResponse() {
        return SaveContractResponse.builder()
                .id(1L)
                .productType(ProductType.PERSONAL)
                .paidOff(false)
                .build();
    }
}
