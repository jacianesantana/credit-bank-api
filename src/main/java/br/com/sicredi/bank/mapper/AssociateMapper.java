package br.com.sicredi.bank.mapper;

import br.com.sicredi.bank.controller.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.controller.response.associate.FindAssociateResponse;
import br.com.sicredi.bank.controller.response.associate.SaveAssociateResponse;
import br.com.sicredi.bank.controller.response.associate.UpdateAssociateContactResponse;
import br.com.sicredi.bank.controller.response.associate.UpdateAssociatePaycheckResponse;
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

    private final AddressMapper addressMapper;
    private final AccountMapper accountMapper;
    private final ContractMapper contractMapper;

    public AssociateEntity saveRequestToAssociate(SaveAssociateRequest request) {
        return AssociateEntity.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .birthDate(request.getBirthDate())
                .phone(request.getPhone())
                .email(request.getEmail())
                .profession(request.getProfession())
                .salary(request.getSalary())
                .lastPaycheck(LocalDate.now())
                .build();
    }

    public SaveAssociateResponse associateToSaveAssociateResponse(AssociateEntity associate, List<AccountEntity> accounts) {
        var accountResponse = accounts.stream()
                .map(accountMapper::accountToAccountResponse)
                .collect(Collectors.toList());

        return SaveAssociateResponse.builder()
                .id(associate.getId())
                .name(associate.getName())
                .cpf(associate.getCpf())
                .birthDate(associate.getBirthDate())
                .phone(associate.getPhone())
                .email(associate.getEmail())
                .profession(associate.getProfession())
                .salary(associate.getSalary())
                .accounts(accountResponse)
                .build();
    }

    public FindAssociateResponse associateToFindAssociateResponse(AssociateEntity associate) {
        var address = associate.getAddressSet().stream()
                .map(addressMapper::addressToAddressResponse)
                .collect(Collectors.toList());

        var accounts = associate.getAccountSet().stream()
                .map(accountMapper::accountToAccountResponse)
                .collect(Collectors.toList());

        var contracts = associate.getContractSet().stream()
                .map(contractMapper::contractToListContractsResponse)
                .collect(Collectors.toList());

        return FindAssociateResponse.builder()
                .id(associate.getId())
                .name(associate.getName())
                .cpf(associate.getCpf())
                .birthDate(associate.getBirthDate())
                .phone(associate.getPhone())
                .email(associate.getEmail())
                .profession(associate.getProfession())
                .salary(associate.getSalary())
                .lastPaycheck(associate.getLastPaycheck())
                .address(address)
                .accounts(accounts)
                .contracts(contracts)
                .build();
    }

    public UpdateAssociatePaycheckResponse associateToUpdateAssociatePaycheckResponse(AssociateEntity associate) {
        return UpdateAssociatePaycheckResponse.builder()
                .id(associate.getId())
                .profession(associate.getProfession())
                .salary(associate.getSalary())
                .lastPaycheck(associate.getLastPaycheck())
                .build();
    }

    public UpdateAssociateContactResponse associateToUpdateAssociateContactResponse(AssociateEntity associate) {
        return UpdateAssociateContactResponse.builder()
                .id(associate.getId())
                .phone(associate.getPhone())
                .email(associate.getEmail())
                .build();
    }

    public AssociateEntity findAssociateResponseToAssociate(FindAssociateResponse response) {
        return AssociateEntity.builder()
                .id(response.getId())
                .name(response.getName())
                .cpf(response.getCpf())
                .birthDate(response.getBirthDate())
                .phone(response.getPhone())
                .email(response.getEmail())
                .profession(response.getProfession())
                .salary(response.getSalary())
                .lastPaycheck(response.getLastPaycheck())
                .build();
    }

}
