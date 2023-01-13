package br.com.sicredi.bank.builder;

import br.com.sicredi.bank.controller.response.associate.FindAssociateResponse;
import br.com.sicredi.bank.controller.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.controller.request.associate.UpdateAssociateRequest;
import br.com.sicredi.bank.controller.response.associate.SaveAssociateResponse;
import br.com.sicredi.bank.entity.AssociateEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AssociateBuilder {

    public static SaveAssociateRequest buildSaveAssociateRequest() {
        return SaveAssociateRequest.builder()
                .name("anyName")
                .cpf("anyCpf")
                .birthDate(LocalDate.of(1993, 10, 19))
                .profession("anyProfession")
                .salary(BigDecimal.valueOf(10000))
                .build();
    }

    public static AssociateEntity buildAssociate() {
        return AssociateEntity.builder()
                .id(1L)
                .name("anyName")
                .cpf("anyCpf")
                .birthDate(LocalDate.of(1993, 10, 19))
                .profession("anyProfession")
                .salary(BigDecimal.valueOf(10000))
                .lastPaycheck(LocalDate.now().minusMonths(4))
                .build();
    }

    public static SaveAssociateResponse buildSaveAssociateResponse() {
        return SaveAssociateResponse.builder()
                .id(1L)
                .name("anyName")
                .cpf("anyCpf")
                .build();
    }

    public static UpdateAssociateRequest buildUpdateAssociateRequest() {
        return UpdateAssociateRequest.builder()
                .profession("anyProfession")
                .salary(BigDecimal.valueOf(10000))
                .build();
    }

    public static FindAssociateResponse buildFindAssociateResponse() {
        return FindAssociateResponse.builder()
                .id(1L)
                .name("anyName")
                .cpf("anyCpf")
                .birthDate(LocalDate.of(1993, 10, 19))
                .profession("anyProfession")
                .salary(BigDecimal.valueOf(10000))
                .lastPaycheck(LocalDate.now().minusMonths(4))
                .accounts(List.of())
                .build();
    }

}
