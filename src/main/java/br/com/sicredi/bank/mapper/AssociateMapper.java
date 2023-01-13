package br.com.sicredi.bank.mapper;

import br.com.sicredi.bank.controller.response.account.AccountResponse;
import br.com.sicredi.bank.controller.response.associate.FindAssociateResponse;
import br.com.sicredi.bank.controller.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.controller.response.associate.SaveAssociateResponse;
import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.AssociateEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssociateMapper {

    public AssociateEntity saveRequestToAssociate(SaveAssociateRequest request) {
        return AssociateEntity.builder()
                .id(null)
                .name(request.getName())
                .cpf(request.getCpf())
                .birthDate(request.getBirthDate())
                .profession(request.getProfession())
                .salary(request.getSalary())
                .lastPaycheck(LocalDate.now())
                .build();
    }

    public SaveAssociateResponse associateToSaveResponse(AssociateEntity associateEntity, List<AccountEntity> accountEntities) {
        var accountResponse = accountEntities.stream()
                .map(this::toAccountResponse)
                .collect(Collectors.toList());

        return SaveAssociateResponse.builder()
                .id(associateEntity.getId())
                .name(associateEntity.getName())
                .cpf(associateEntity.getCpf())
                .accounts(accountResponse)
                .build();
    }

    public FindAssociateResponse associateToFindAssociateResponse(AssociateEntity associateEntity) {
        var accounts = associateEntity.getAccountEntitySet().stream()
                .map(this::toAccountResponse)
                .collect(Collectors.toList());

        return FindAssociateResponse.builder()
                .id(associateEntity.getId())
                .name(associateEntity.getName())
                .cpf(associateEntity.getCpf())
                .birthDate(associateEntity.getBirthDate())
                .profession(associateEntity.getProfession())
                .salary(associateEntity.getSalary())
                .lastPaycheck(associateEntity.getLastPaycheck())
                .accounts(accounts)
                .build();
    }

    public AssociateEntity findAssociateResponseToAssociate(FindAssociateResponse response) {
        return AssociateEntity.builder()
                .id(response.getId())
                .name(response.getName())
                .cpf(response.getCpf())
                .birthDate(response.getBirthDate())
                .profession(response.getProfession())
                .salary(response.getSalary())
                .lastPaycheck(response.getLastPaycheck())
                .build();
    }

    public AccountResponse toAccountResponse(AccountEntity accountEntity) {
        return AccountResponse.builder()
                .type(accountEntity.getType())
                .agency(accountEntity.getAgency())
                .number(accountEntity.getNumber())
                .build();
    }

}
