package br.com.sicredi.bank.mapper;

import br.com.sicredi.bank.controller.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.controller.response.associate.AssociateResponse;
import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.AssociateEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AssociateMapper {

    private final AccountMapper accountMapper;

    public AssociateEntity saveRequestToAssociate(SaveAssociateRequest request) {
        return AssociateEntity.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .birthDate(request.getBirthDate())
                .profession(request.getProfession())
                .salary(request.getSalary())
                .lastPaycheck(LocalDate.now())
                .build();
    }

    public AssociateResponse associateWithAccountsToResponse(AssociateEntity associate, List<AccountEntity> accounts) {
        var accountResponse = accounts.stream()
                .map(accountMapper::accountToAccountResponse)
                .collect(Collectors.toList());

        return AssociateResponse.builder()
                .id(associate.getId())
                .name(associate.getName())
                .cpf(associate.getCpf())
                .birthDate(associate.getBirthDate())
                .profession(associate.getProfession())
                .salary(associate.getSalary())
                .accounts(accountResponse)
                .build();
    }

    public AssociateResponse associateToAssociateResponse(AssociateEntity associate) {
        var accounts = associate.getAccountSet().stream()
                .map(accountMapper::accountToAccountResponse)
                .collect(Collectors.toList());

        return AssociateResponse.builder()
                .id(associate.getId())
                .name(associate.getName())
                .cpf(associate.getCpf())
                .birthDate(associate.getBirthDate())
                .profession(associate.getProfession())
                .salary(associate.getSalary())
                .lastPaycheck(associate.getLastPaycheck())
                .accounts(accounts)
                .build();
    }

    public AssociateEntity associateResponseToAssociate(AssociateResponse response) {
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

}
