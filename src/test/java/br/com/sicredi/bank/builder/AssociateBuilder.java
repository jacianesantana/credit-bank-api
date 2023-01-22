package br.com.sicredi.bank.builder;

import br.com.sicredi.bank.model.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.model.request.associate.UpdateAssociateContactRequest;
import br.com.sicredi.bank.model.request.associate.UpdateAssociatePaycheckRequest;
import br.com.sicredi.bank.model.response.associate.FindAssociateResponse;
import br.com.sicredi.bank.model.response.associate.SaveAssociateResponse;
import br.com.sicredi.bank.model.response.associate.UpdateAssociateContactResponse;
import br.com.sicredi.bank.model.response.associate.UpdateAssociatePaycheckResponse;
import br.com.sicredi.bank.model.entity.AssociateEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AssociateBuilder {

    public static SaveAssociateRequest buildSaveAssociateRequest() {
        return SaveAssociateRequest.builder()
                .name("anyName")
                .cpf("anyCpf")
                .birthDate(LocalDate.now().minusYears(18))
                .phone("anyPhone")
                .email("any@mail.com")
                .profession("anyProfession")
                .salary(BigDecimal.valueOf(10000))
                .build();
    }

    public static UpdateAssociateContactRequest buildUpdateAssociateContactRequest() {
        return UpdateAssociateContactRequest.builder()
                .phone("anyPhone")
                .email("any@mail.com")
                .build();
    }

    public static UpdateAssociatePaycheckRequest buildUpdateAssociatePaycheckRequest() {
        return UpdateAssociatePaycheckRequest.builder()
                .profession("anyProfession")
                .salary(BigDecimal.valueOf(10000))
                .build();
    }

    public static SaveAssociateResponse buildSaveAssociateResponse() {
        return SaveAssociateResponse.builder()
                .id(1L)
                .name("anyName")
                .cpf("anyCpf")
                .birthDate(LocalDate.now().minusYears(18))
                .phone("anyPhone")
                .email("any@mail.com")
                .profession("anyProfession")
                .salary(BigDecimal.valueOf(10000))
                .lastPaycheck(LocalDate.now().minusMonths(4))
                .accounts(List.of())
                .build();
    }

    public static FindAssociateResponse buildFindAssociateResponse() {
        return FindAssociateResponse.builder()
                .id(1L)
                .name("anyName")
                .cpf("anyCpf")
                .birthDate(LocalDate.now().minusYears(18))
                .phone("anyPhone")
                .email("any@mail.com")
                .profession("anyProfession")
                .salary(BigDecimal.valueOf(10000))
                .lastPaycheck(LocalDate.now().minusMonths(4))
                .accounts(List.of())
                .contracts(List.of())
                .build();
    }

    public static UpdateAssociateContactResponse buildUpdateAssociateContactResponse() {
        return UpdateAssociateContactResponse.builder()
                .id(1L)
                .phone("anyPhone")
                .email("any@mail.com")
                .build();
    }

    public static UpdateAssociatePaycheckResponse buildUpdateAssociatePaycheckResponse() {
        return UpdateAssociatePaycheckResponse.builder()
                .id(1L)
                .profession("anyProfession")
                .salary(BigDecimal.valueOf(10000))
                .lastPaycheck(LocalDate.now())
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
