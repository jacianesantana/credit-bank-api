package com.bank.credit.mapper;

import com.bank.credit.controller.request.associate.SaveAssociateRequest;
import com.bank.credit.controller.request.associate.UpdateAssociateRequest;
import com.bank.credit.controller.response.associate.SaveAssociateResponse;
import com.bank.credit.model.Associate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AssociateMapper {

    public Associate saveRequestToAssociate(SaveAssociateRequest request) {
        return Associate.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .birthDate(request.getBirthDate())
                .profession(request.getProfession())
                .salary(request.getSalary())
                .lastPaycheck(LocalDate.now())
                .build();
    }

    public SaveAssociateResponse associateToSaveResponse(Associate associate) {
        return SaveAssociateResponse.builder()
                .id(associate.getId())
                .name(associate.getName())
                .cpf(associate.getCpf())
                .build();
    }

    public Associate updateRequestToAssociate(UpdateAssociateRequest associateRequest) {
        return Associate.builder().build();
    }
}
