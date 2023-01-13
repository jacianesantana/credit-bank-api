package br.com.sicredi.bank.service;

import br.com.sicredi.bank.builder.AssociateBuilder;
import br.com.sicredi.bank.controller.response.associate.FindAssociateResponse;
import br.com.sicredi.bank.exception.FindEntityException;
import br.com.sicredi.bank.exception.UpdateEntityException;
import br.com.sicredi.bank.mapper.AssociateMapper;
import br.com.sicredi.bank.controller.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.exception.SaveEntityException;
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

    private static final String UPDATE_SUCCESS = "Associado atualizado com sucesso!";
    private static final String UPDATE_ERROR = "Associado com menos de 3 meses desde da última atualização!";
    private static final String DELETE_SUCCESS = "Associado excluido com sucesso!";
    private static final String DELETE_ERROR = "Associado contém contratos ativos!";

    @Test
    void saveSuccess() {
        var request = AssociateBuilder.buildSaveAssociateRequest();
        var associate = AssociateBuilder.buildAssociate();
        var saveResponse = AssociateBuilder.buildSaveAssociateResponse();
        var account = new AccountEntity();

        when(associateMapper.saveRequestToAssociate(any(SaveAssociateRequest.class))).thenReturn(associate);
        when(associateRepository.save(any(AssociateEntity.class))).thenReturn(associate);
        when(accountService.create(any(AssociateEntity.class), any(AccountType.class))).thenReturn(account);
        when(associateMapper.associateToSaveResponse(any(AssociateEntity.class), anyList())).thenReturn(saveResponse);

        var response = associateService.save(request);

        assertNotNull(response.getId());
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getCpf(), response.getCpf());
    }

    @Test
    void saveShouldReturnSaveEntityException() {
        var request = AssociateBuilder.buildSaveAssociateRequest();
        var associate = AssociateBuilder.buildAssociate();

        when(associateMapper.saveRequestToAssociate(any(SaveAssociateRequest.class))).thenReturn(associate);
        when(associateRepository.save(any(AssociateEntity.class))).thenThrow(new RuntimeException());

        assertThrows(SaveEntityException.class, () -> associateService.save(request));
    }

    @Test
    void findById() {
        var associate = AssociateBuilder.buildAssociate();
        var associateResponse = AssociateBuilder.buildFindAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToFindAssociateResponse(any(AssociateEntity.class))).thenReturn(associateResponse);

        var response = associateService.findById(associate.getId());

        assertEquals(associate.getId(), response.getId());
        assertEquals(associate.getCpf(), response.getCpf());
    }

    @Test
    void updateSuccess() {
        var request = AssociateBuilder.buildUpdateAssociateRequest();
        var associate = AssociateBuilder.buildAssociate();
        var associateResponse = AssociateBuilder.buildFindAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToFindAssociateResponse(any(AssociateEntity.class))).thenReturn(associateResponse);
        when(associateMapper.findAssociateResponseToAssociate(any(FindAssociateResponse.class))).thenReturn(associate);

        associate.setProfession(request.getProfession());
        associate.setSalary(request.getSalary());

        when(associateRepository.save(any(AssociateEntity.class))).thenReturn(associate);

        var response = associateService.update(1L, request);

        assertTrue(response.getUpdated());
        assertEquals(UPDATE_SUCCESS, response.getMessage());
    }

    @Test
    void updateShouldReturnFindEntityException() {
        var request = AssociateBuilder.buildUpdateAssociateRequest();

        when(associateRepository.findById(anyLong())).thenThrow(new RuntimeException());

        assertThrows(FindEntityException.class, () -> associateService.update(1L, request));
    }

    @Test
    void updateShouldReturnUpdateEntityException() {
        var request = AssociateBuilder.buildUpdateAssociateRequest();
        var associate = AssociateBuilder.buildAssociate();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));

        associate.setProfession(request.getProfession());
        associate.setSalary(request.getSalary());

        when(associateRepository.save(any(AssociateEntity.class))).thenThrow(new RuntimeException());

        assertThrows(UpdateEntityException.class, () -> associateService.update(1L, request));
    }

    @Test
    void updateShouldReturnError() {
        var request = AssociateBuilder.buildUpdateAssociateRequest();
        var associate = AssociateBuilder.buildAssociate();
        associate.setLastPaycheck(LocalDate.now().minusMonths(2));

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));

        var response = associateService.update(1L, request);

        assertFalse(response.getUpdated());
        assertEquals(UPDATE_ERROR, response.getMessage());
    }

    @Test
    void delete() {
        var associate = AssociateBuilder.buildAssociate();
        var associateResponse = AssociateBuilder.buildFindAssociateResponse();

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.associateToFindAssociateResponse(any(AssociateEntity.class))).thenReturn(associateResponse);
        when(contractService.findContracts(anyLong())).thenReturn(List.of());
        doNothing().when(associateRepository).deleteById(anyLong());

        var response = associateService.delete(1L);

        assertEquals(DELETE_SUCCESS, response.getMessage());
    }

}