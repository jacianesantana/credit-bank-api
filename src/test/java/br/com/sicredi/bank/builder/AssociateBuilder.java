package br.com.sicredi.bank.builder;

import br.com.sicredi.bank.controller.response.associate.AssociateResponse;
import br.com.sicredi.bank.controller.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.controller.request.associate.UpdateAssociateRequest;
import br.com.sicredi.bank.entity.AssociateEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AssociateBuilder {

    public static SaveAssociateRequest buildSaveAssociateRequest() {
        return SaveAssociateRequest.builder()
                .name("anyName")
                .cpf("anyCpf")
                .birthDate(LocalDate.now().minusYears(18))
                .profession("anyProfession")
                .salary(BigDecimal.valueOf(10000))
                .build();
    }

    public static UpdateAssociateRequest buildUpdateAssociateRequest() {
        return UpdateAssociateRequest.builder()
                .profession("anyProfession")
                .salary(BigDecimal.valueOf(10000))
                .build();
    }

    public static AssociateResponse buildAssociateResponse() {
        return AssociateResponse.builder()
                .id(1L)
                .name("anyName")
                .cpf("anyCpf")
                .birthDate(LocalDate.now().minusYears(18))
                .profession("anyProfession")
                .salary(BigDecimal.valueOf(10000))
                .lastPaycheck(LocalDate.now().minusMonths(4))
                .accounts(List.of())
                .build();
    }

    public static AssociateEntity buildAssociate() {
        return AssociateEntity.builder()
                .id(1L)
                .name("anyName")
                .cpf("anyCpf")
                .birthDate(LocalDate.now().minusYears(18))
                .profession("anyProfession")
                .salary(BigDecimal.valueOf(10000))
                .lastPaycheck(LocalDate.now().minusMonths(4))
                .build();
    }

}
