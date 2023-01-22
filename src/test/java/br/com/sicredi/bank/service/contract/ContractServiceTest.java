package br.com.sicredi.bank.service.contract;

import br.com.sicredi.bank.model.response.contract.FindContractResponse;
import br.com.sicredi.bank.model.entity.AssociateEntity;
import br.com.sicredi.bank.model.entity.ContractEntity;
import br.com.sicredi.bank.exception.FindEntityException;
import br.com.sicredi.bank.mapper.ContractMapper;
import br.com.sicredi.bank.repository.ContractRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static br.com.sicredi.bank.builder.AssociateBuilder.buildAssociate;
import static br.com.sicredi.bank.builder.ContractBuilder.buildContractEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContractServiceTest {

    @InjectMocks
    private ContractService contractService;

    @Mock
    private ContractRepository contractRepository;

    @Mock
    private ContractMapper contractMapper;

    @Test
    void findContractsSuccess() {
        var associate = buildAssociate();

        when(contractRepository.findByAssociate(any(AssociateEntity.class))).thenReturn(List.of(new ContractEntity()));

        var response = contractService.findContracts(associate);

        assertFalse(response.isEmpty());
    }

    @Test
    void findContractsShouldReturnEmptyList() {
        var associate = buildAssociate();

        when(contractRepository.findByAssociate(any(AssociateEntity.class))).thenReturn(List.of());

        var response = contractService.findContracts(associate);

        assertTrue(response.isEmpty());
    }

    @Test
    void findByIdSuccess() {
        var contract = buildContractEntity();
        var findContractResponse = new FindContractResponse();

        when(contractRepository.findById(anyLong())).thenReturn(Optional.of(contract));
        when(contractMapper.contractToFindContractResponse(any(ContractEntity.class)))
                .thenReturn(findContractResponse);

        assertDoesNotThrow(() -> contractService.findById(contract.getId()));
    }

    @Test
    void findByIdThrowsFindEntityException() {
        var contract = buildContractEntity();

        when(contractRepository.findById(anyLong())).thenThrow(new NoSuchElementException());

        assertThrows(FindEntityException.class, () -> contractService.findById(contract.getId()));
    }
}