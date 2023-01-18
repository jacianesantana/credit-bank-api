package br.com.sicredi.bank.service;

import br.com.sicredi.bank.builder.AssociateBuilder;
import br.com.sicredi.bank.controller.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.controller.response.associate.FindAssociateResponse;
import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.AssociateEntity;
import br.com.sicredi.bank.entity.ContractEntity;
import br.com.sicredi.bank.entity.enums.AccountType;
import br.com.sicredi.bank.exception.*;
import br.com.sicredi.bank.mapper.AssociateMapper;
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
import static org.mockito.Mockito.*;

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
        var request = buildSaveAssociateRequest();
        var associate = buildAssociate();
        var saveResponse = buildSaveAssociateResponse();
        var account = new AccountEntity();

        when(associateRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        when(associateMapper.saveRequestToAssociate(any(SaveAssociateRequest.class))).thenReturn(associate);
        when(associateRepository.save(any(AssociateEntity.class))).thenReturn(associate);
        when(accountService.create(any(AssociateEntity.class), any(AccountType.class))).thenReturn(account);
        when(associateMapper.associateToSaveAssociateResponse(any(AssociateEntity.class), anyList())).thenReturn(saveResponse);

        var response = associateService.save(request);

        assertNotNull(response.getAccounts());
        assertNotNull(response.getLastPaycheck());
    }

    @Test
    void saveShouldReturnSaveEntityException() {
        var request = AssociateBuilder.buildSaveAssociateRequest();
        var associate = buildAssociate();

        when(associateRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        when(associateMapper.saveRequestToAssociate(any(SaveAssociateRequest.class))).thenReturn(associate);
        when(associateRepository.save(any(AssociateEntity.class))).thenThrow(new RuntimeException());

        assertThrows(SaveEntityException.class, () -> associateService.save(request));
    }

    @Test
    void saveShouldReturnBusinessRulesExceptionWithMinimumAge() {
        var request = AssociateBuilder.buildSaveAssociateRequest();
        request.setBirthDate(LocalDate.now().minusYears(17));

        when(associateRepository.findByCpf(anyString())).thenReturn(Optional.empty());

        assertThrows(BusinessRulesException.class, () -> associateService.save(request));
    }

    @Test
    void saveShouldReturnBusinessRulesExceptionWithExistingCpf() {
        var associate = buildAssociate();
        var request = AssociateBuilder.buildSaveAssociateRequest();

        when(associateRepository.findByCpf(anyString())).thenReturn(Optional.of(associate));

        assertThrows(BusinessRulesException.class, () -> associateService.save(request));
    }

    @Test
    void findByIdSuccess() {
        var associate = buildAssociate();
        var associateResponse = buildFindAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToFindAssociateResponse(any(AssociateEntity.class))).thenReturn(associateResponse);

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
    void updateContactSuccess() {
        var request = buildUpdateAssociateContactRequest();
        var associate = buildAssociate();
        var findAssociateResponse = buildFindAssociateResponse();
        var updateAssociateResponse = buildUpdateAssociateContactResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToFindAssociateResponse(any(AssociateEntity.class))).thenReturn(findAssociateResponse);
        when(associateMapper.findAssociateResponseToAssociate(any(FindAssociateResponse.class))).thenReturn(associate);
        when(associateRepository.save(any(AssociateEntity.class))).thenReturn(associate);
        when(associateMapper.associateToUpdateAssociateContactResponse(any(AssociateEntity.class))).thenReturn(updateAssociateResponse);

        var response = associateService.updateContact(associate.getId(), request);

        assertEquals(associate.getId(), response.getId());
        assertEquals(request.getPhone(), response.getPhone());
        assertEquals(request.getEmail(), response.getEmail());
    }

    @Test
    void updateContactShouldReturnUpdateEntityException() {
        var request = buildUpdateAssociateContactRequest();
        var associate = buildAssociate();
        var findAssociateResponse = buildFindAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToFindAssociateResponse(any(AssociateEntity.class))).thenReturn(findAssociateResponse);
        when(associateMapper.findAssociateResponseToAssociate(any(FindAssociateResponse.class))).thenReturn(associate);
        when(associateRepository.save(any(AssociateEntity.class))).thenThrow(new RuntimeException());

        assertThrows(UpdateEntityException.class, () -> associateService.updateContact(associate.getId(), request));
    }

    @Test
    void updatePaycheckSuccess() {
        var request = buildUpdateAssociatePaycheckRequest();
        var associate = buildAssociate();
        var findAssociateResponse = buildFindAssociateResponse();
        var updateAssociateResponse = buildUpdateAssociatePaycheckResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToFindAssociateResponse(any(AssociateEntity.class))).thenReturn(findAssociateResponse);
        when(associateMapper.findAssociateResponseToAssociate(any(FindAssociateResponse.class))).thenReturn(associate);
        when(associateRepository.save(any(AssociateEntity.class))).thenReturn(associate);
        when(associateMapper.associateToUpdateAssociatePaycheckResponse(any(AssociateEntity.class))).thenReturn(updateAssociateResponse);

        var response = associateService.updatePaycheck(associate.getId(), request);

        assertNotNull(response.getId());
        assertEquals(request.getProfession(), response.getProfession());
        assertEquals(request.getSalary(), response.getSalary());
        assertEquals(updateAssociateResponse.getLastPaycheck(), response.getLastPaycheck());
    }

    @Test
    void updatePaycheckShouldReturnUpdateEntityException() {
        var request = buildUpdateAssociatePaycheckRequest();
        var associate = buildAssociate();
        var findAssociateResponse = buildFindAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToFindAssociateResponse(any(AssociateEntity.class))).thenReturn(findAssociateResponse);
        when(associateMapper.findAssociateResponseToAssociate(any(FindAssociateResponse.class))).thenReturn(associate);
        when(associateRepository.save(any(AssociateEntity.class))).thenThrow(new RuntimeException());

        assertThrows(UpdateEntityException.class, () -> associateService.updatePaycheck(associate.getId(), request));
    }

    @Test
    void updatePaycheckShouldReturnBusinessRulesException() {
        var request = buildUpdateAssociatePaycheckRequest();
        var associate = buildAssociate();
        associate.setLastPaycheck(LocalDate.now().minusMonths(2));
        var findAssociateResponse = buildFindAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToFindAssociateResponse(any(AssociateEntity.class))).thenReturn(findAssociateResponse);
        when(associateMapper.findAssociateResponseToAssociate(any(FindAssociateResponse.class))).thenReturn(associate);

        assertThrows(BusinessRulesException.class, () -> associateService.updatePaycheck(associate.getId(), request));
    }

    @Test
    void deleteSuccess() {
        var associate = buildAssociate();
        var findAssociateResponse = buildFindAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToFindAssociateResponse(any(AssociateEntity.class))).thenReturn(findAssociateResponse);
        when(associateMapper.findAssociateResponseToAssociate(any(FindAssociateResponse.class))).thenReturn(associate);
        when(contractService.findContracts(any(AssociateEntity.class))).thenReturn(List.of());
        doNothing().when(associateRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> associateService.delete(associate.getId()));
    }

    @Test
    void deleteShouldReturnDeleteEntityException() {
        var associate = buildAssociate();
        var findAssociateResponse = buildFindAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToFindAssociateResponse(any(AssociateEntity.class))).thenReturn(findAssociateResponse);
        when(associateMapper.findAssociateResponseToAssociate(any(FindAssociateResponse.class))).thenReturn(associate);
        when(contractService.findContracts(any(AssociateEntity.class))).thenReturn(List.of());
        doThrow(RuntimeException.class).when(associateRepository).deleteById(anyLong());

        assertThrows(DeleteEntityException.class, () -> associateService.delete(associate.getId()));
    }

    @Test
    void deleteShouldReturnBusinessRulesException() {
        var associate = buildAssociate();
        var findAssociateResponse = buildFindAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToFindAssociateResponse(any(AssociateEntity.class))).thenReturn(findAssociateResponse);
        when(associateMapper.findAssociateResponseToAssociate(any(FindAssociateResponse.class))).thenReturn(associate);
        when(contractService.findContracts(any(AssociateEntity.class))).thenReturn(List.of(new ContractEntity()));

        assertThrows(BusinessRulesException.class, () -> associateService.delete(associate.getId()));
    }

}