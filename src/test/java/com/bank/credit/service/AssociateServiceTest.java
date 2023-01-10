package com.bank.credit.service;

import com.bank.credit.controller.request.associate.SaveAssociateRequest;
import com.bank.credit.controller.request.associate.UpdateAssociateRequest;
import com.bank.credit.exception.FindEntityException;
import com.bank.credit.exception.SaveEntityException;
import com.bank.credit.exception.UpdateEntityException;
import com.bank.credit.mapper.AssociateMapper;
import com.bank.credit.model.Account;
import com.bank.credit.model.Associate;
import com.bank.credit.model.enums.AccountType;
import com.bank.credit.repository.AssociateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.bank.credit.builder.AssociateBuilder.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
    private ContractService contractService;

    private static final String UPDATE_SUCCESS = "Associado atualizado com sucesso!";
    private static final String UPDATE_ERROR = "Associado com menos de 3 meses desde da última atualização!";
    private static final String DELETE_SUCCESS = "Associado excluido com sucesso!";
    private static final String DELETE_ERROR = "Associado contém contratos ativos!";

    @Test
    void saveSuccess() {
        var request = buildSaveAssociateRequest();
        var associate = buildAssociate();
        var saveResponse = buildSaveAssociateResponse();

        when(associateMapper.saveRequestToAssociate(any(SaveAssociateRequest.class))).thenReturn(associate);
        when(associateRepository.save(any(Associate.class))).thenReturn(associate);
        when(associateMapper.associateToSaveResponse(any(Associate.class), anyList())).thenReturn(saveResponse);

        var response = associateService.save(request);

        assertNotNull(response.getId());
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getCpf(), response.getCpf());
    }

    @Test
    void saveShouldReturnSaveEntityException() {
        var request = buildSaveAssociateRequest();
        var associate = buildAssociate();

        when(associateMapper.saveRequestToAssociate(any(SaveAssociateRequest.class))).thenReturn(associate);
        when(associateRepository.save(any(Associate.class))).thenThrow(new RuntimeException());

        assertThrows(SaveEntityException.class, () -> associateService.save(request));
    }

    @Test
    void updateSuccess() {
        var request = buildUpdateAssociateRequest();
        var associate = buildAssociate();
        var updatedAssociate = buildAssociate();
        updatedAssociate.setProfession("otherProfessional");
        updatedAssociate.setSalary(BigDecimal.valueOf(15000));
        updatedAssociate.setLastPaycheck(LocalDate.now());

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.updateRequestToAssociate(any(UpdateAssociateRequest.class))).thenReturn(updatedAssociate);
        when(associateRepository.save(any(Associate.class))).thenReturn(updatedAssociate);

        var response = associateService.update(1L, request);

        assertTrue(response.getUpdated());
        assertEquals(UPDATE_SUCCESS, response.getMessage());
    }

    @Test
    void updateShouldReturnFindEntityException() {
        var request = buildUpdateAssociateRequest();

        when(associateRepository.findById(anyLong())).thenThrow(new RuntimeException());

        assertThrows(FindEntityException.class, () -> associateService.update(1L, request));
    }

    @Test
    void updateShouldReturnUpdateEntityException() {
        var request = buildUpdateAssociateRequest();
        var associate = buildAssociate();
        var updatedAssociate = buildAssociate();
        updatedAssociate.setProfession("otherProfessional");
        updatedAssociate.setSalary(BigDecimal.valueOf(15000));
        updatedAssociate.setLastPaycheck(LocalDate.now());

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));
        when(associateMapper.updateRequestToAssociate(any(UpdateAssociateRequest.class))).thenReturn(updatedAssociate);
        when(associateRepository.save(any(Associate.class))).thenThrow(new RuntimeException());

        assertThrows(UpdateEntityException.class, () -> associateService.update(1L, request));
    }

    @Test
    void updateShouldReturnError() {
        var request = buildUpdateAssociateRequest();
        var associate = buildAssociate();
        associate.setLastPaycheck(LocalDate.now().minusMonths(2));

        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));

        var response = associateService.update(1L, request);

        assertFalse(response.getUpdated());
        assertEquals(UPDATE_ERROR, response.getMessage());
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }
}