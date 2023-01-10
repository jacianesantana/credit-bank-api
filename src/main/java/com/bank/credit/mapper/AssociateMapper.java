package com.bank.credit.mapper;

import com.bank.credit.controller.request.associate.SaveAssociateRequest;
import com.bank.credit.controller.request.associate.UpdateAssociateRequest;
import com.bank.credit.controller.response.account.AccountResponse;
import com.bank.credit.controller.response.associate.SaveAssociateResponse;
import com.bank.credit.model.Account;
import com.bank.credit.model.Associate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssociateMapper {

    public Associate saveRequestToAssociate(SaveAssociateRequest request) {
        return Associate.builder()
                .id(null)
                .name(request.getName())
                .cpf(request.getCpf())
                .birthDate(request.getBirthDate())
                .profession(request.getProfession())
                .salary(request.getSalary())
                .lastPaycheck(LocalDate.now())
                .build();
    }

    public SaveAssociateResponse associateToSaveResponse(Associate associate, List<Account> accounts) {
        var accountResponse = accounts.stream()
                .map(this::toAccountResponse)
                .collect(Collectors.toList());

        return SaveAssociateResponse.builder()
                .id(associate.getId())
                .name(associate.getName())
                .cpf(associate.getCpf())
                .accounts(accountResponse)
                .build();
    }

    public Associate updateRequestToAssociate(UpdateAssociateRequest associateRequest) {
        return Associate.builder().build();
    }

    private AccountResponse toAccountResponse(Account account) {
        return AccountResponse.builder()
                .type(account.getType())
                .agency(account.getAgency())
                .number(account.getNumber())
                .build();
    }
}
