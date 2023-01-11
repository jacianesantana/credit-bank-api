package com.bank.credit.builder;

import com.bank.credit.controller.request.associate.SaveAssociateRequest;
import com.bank.credit.controller.request.associate.UpdateAssociateRequest;
import com.bank.credit.controller.response.associate.SaveAssociateResponse;
import com.bank.credit.controller.response.associate.UpdateAssociateResponse;
import com.bank.credit.model.Associate;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AssociateBuilder {

    private static final String UPDATE_SUCCESS = "Associado atualizado com sucesso!";
    private static final String UPDATE_ERROR = "Associado com menos de 3 meses desde da última atualização!";
    private static final String DELETE_SUCCESS = "Associado excluido com sucesso!";
    private static final String DELETE_ERROR = "Associado contém contratos ativos!";

    public static SaveAssociateRequest buildSaveAssociateRequest() {
        return SaveAssociateRequest.builder()
                .name("anyName")
                .cpf("anyCpf")
                .birthDate(LocalDate.of(1993, 10, 19))
                .profession("anyProfession")
                .salary(BigDecimal.valueOf(10000))
                .build();
    }

    public static Associate buildAssociate() {
        return Associate.builder()
                .id(1L)
                .name("anyName")
                .cpf("anyCpf")
                .birthDate(LocalDate.of(1993, 10, 19))
                .profession("anyProfession")
                .salary(BigDecimal.valueOf(10000))
                .lastPaycheck(LocalDate.now())
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

    public static UpdateAssociateResponse buildUpdateAssociateResponse() {
        return UpdateAssociateResponse.builder()
                .updated(true)
                .message(UPDATE_SUCCESS)
                .build();
    }
}
