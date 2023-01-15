package br.com.sicredi.bank.service;

import br.com.sicredi.bank.builder.AssociateBuilder;
import br.com.sicredi.bank.controller.response.associate.AssociateResponse;
import br.com.sicredi.bank.exception.*;
import br.com.sicredi.bank.mapper.AssociateMapper;
import br.com.sicredi.bank.controller.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.AssociateEntity;
import br.com.sicredi.bank.entity.enums.AccountType;
import br.com.sicredi.bank.repository.AssociateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static br.com.sicredi.bank.builder.AssociateBuilder.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssociateServiceTest {

    @InjectMocks
    private AssociateService associateService;

    @Mock
    private AssociateRepository associateRepository;

    @Mock
    private AssociateMapper associateMapper;

    @Mock
    private AccountService accountService;

    @Mock
    private ContractService contractService;

    @Test
    void saveSuccess() {
        var request = AssociateBuilder.buildSaveAssociateRequest();
        var associate = buildAssociate();
        var saveResponse = AssociateBuilder.buildAssociateResponse();
        var account = new AccountEntity();

        when(associateRepository.findByCpf(anyString())).thenReturn(Optional.of(associate));
        when(associateMapper.saveRequestToAssociate(any(SaveAssociateRequest.class))).thenReturn(associate);
        when(associateRepository.save(any(AssociateEntity.class))).thenReturn(associate);
        when(accountService.create(any(AssociateEntity.class), any(AccountType.class))).thenReturn(account);
        when(associateMapper.associateWithAccountsToResponse(any(AssociateEntity.class), anyList())).thenReturn(saveResponse);

        var response = associateService.save(request);

        assertNotNull(response.getId());
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getCpf(), response.getCpf());
    }

    @Test
    void saveShouldReturnSaveEntityException() {
        var request = AssociateBuilder.buildSaveAssociateRequest();
        var associate = buildAssociate();

        when(associateMapper.saveRequestToAssociate(any(SaveAssociateRequest.class))).thenReturn(associate);
        when(associateRepository.save(any(AssociateEntity.class))).thenThrow(new RuntimeException());

        assertThrows(SaveEntityException.class, () -> associateService.save(request));
    }

    @Test
    void saveShouldReturnBusinessRulesExceptionWithMinimumAge() {
        var request = AssociateBuilder.buildSaveAssociateRequest();
        var associate = buildAssociate();
        associate.setBirthDate(LocalDate.now().minusYears(17));

        when(associateRepository.findByCpf(anyString())).thenReturn(Optional.of(associate));

        assertThrows(BusinessRulesException.class, () -> associateService.save(request));
    }

    @Test
    void saveShouldReturnBusinessRulesExceptionWithExistingCpf() {
        var request = AssociateBuilder.buildSaveAssociateRequest();

        when(associateRepository.findByCpf(anyString())).thenThrow(new RuntimeException());

        assertThrows(BusinessRulesException.class, () -> associateService.save(request));
    }

    @Test
    void findByIdSuccess() {
        var associate = buildAssociate();
        var associateResponse = buildAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToAssociateResponse(any(AssociateEntity.class))).thenReturn(associateResponse);

        var response = associateService.findById(associate.getId());

        assertEquals(associate.getId(), response.getId());
        assertEquals(associate.getCpf(), response.getCpf());
    }

    @Test
    void findByIdShouldReturnFindEntityException() {
        var associate = buildAssociate();

        when(associateRepository.findById(anyLong())).thenThrow(new RuntimeException());

        assertThrows(FindEntityException.class, () -> associateService.findById(associate.getId()));
    }

    @Test
    void updateSuccess() {
        var request = buildUpdateAssociateRequest();
        var associate = buildAssociate();
        var associateResponse = buildAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToAssociateResponse(any(AssociateEntity.class))).thenReturn(associateResponse);
        when(associateMapper.associateResponseToAssociate(any(AssociateResponse.class))).thenReturn(associate);

        associate.setProfession(request.getProfession());
        associate.setSalary(request.getSalary());

        when(associateRepository.save(any(AssociateEntity.class))).thenReturn(associate);

        var response = associateService.update(associate.getId(), request);

        assertNotNull(response.getId());
        assertEquals(request.getProfession(), response.getProfession());
        assertEquals(request.getSalary(), response.getSalary());
    }

    @Test
    void updateShouldReturnUpdateEntityException() {
        var request = buildUpdateAssociateRequest();
        var associate = buildAssociate();
        var associateResponse = buildAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToAssociateResponse(any(AssociateEntity.class))).thenReturn(associateResponse);
        when(associateMapper.associateResponseToAssociate(any(AssociateResponse.class))).thenReturn(associate);

        associate.setProfession(request.getProfession());
        associate.setSalary(request.getSalary());

        when(associateRepository.save(any(AssociateEntity.class))).thenThrow(new RuntimeException());

        assertThrows(UpdateEntityException.class, () -> associateService.update(associate.getId(), request));
    }

    @Test
    void updateShouldReturnBusinessRulesException() {
        var request = buildUpdateAssociateRequest();
        var associate = buildAssociate();
        associate.setLastPaycheck(LocalDate.now().minusMonths(2));
        var associateResponse = buildAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToAssociateResponse(any(AssociateEntity.class))).thenReturn(associateResponse);
        when(associateMapper.associateResponseToAssociate(any(AssociateResponse.class))).thenReturn(associate);

        assertThrows(BusinessRulesException.class, () -> associateService.update(associate.getId(), request));
    }

    @Test
    void deleteSuccess() {
        var associate = buildAssociate();
        var associateResponse = buildAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToAssociateResponse(any(AssociateEntity.class))).thenReturn(associateResponse);
        when(associateMapper.associateResponseToAssociate(any(AssociateResponse.class))).thenReturn(associate);
        doNothing().when(associateRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> associateService.delete(associate.getId()));
    }

    @Test
    void deleteShouldReturnDeleteEntityException() {
        var associate = buildAssociate();
        //associate.setContractSet();
        var associateResponse = buildAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToAssociateResponse(any(AssociateEntity.class))).thenReturn(associateResponse);
        when(associateMapper.associateResponseToAssociate(any(AssociateResponse.class))).thenReturn(associate);
        doNothing().when(associateRepository).deleteById(anyLong());

        assertThrows(DeleteEntityException.class, () -> associateService.delete(associate.getId()));
    }

    @Test
    void deleteShouldReturnBusinessRulesException() {
        var associate = buildAssociate();
        //associate.setContractSet();
        var associateResponse = buildAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToAssociateResponse(any(AssociateEntity.class))).thenReturn(associateResponse);
        when(associateMapper.associateResponseToAssociate(any(AssociateResponse.class))).thenReturn(associate);

        assertThrows(BusinessRulesException.class, () -> associateService.delete(associate.getId()));
    }

}