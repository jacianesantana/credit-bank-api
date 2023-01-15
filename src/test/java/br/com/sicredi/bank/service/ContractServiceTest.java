package br.com.sicredi.bank.service;

import br.com.sicredi.bank.entity.AssociateEntity;
import br.com.sicredi.bank.entity.ContractEntity;
import br.com.sicredi.bank.entity.enums.ProductType;
import br.com.sicredi.bank.exception.SaveEntityException;
import br.com.sicredi.bank.repository.ContractRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.sicredi.bank.builder.AssociateBuilder.buildAssociate;
import static br.com.sicredi.bank.builder.ContractBuilder.buildContractEntity;
import static br.com.sicredi.bank.builder.ContractBuilder.buildContractRequest;
import static br.com.sicredi.bank.builder.ProductBuilder.buildProductEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContractServiceTest {

    @InjectMocks
    private ContractService contractService;

    @Mock
    private ContractRepository contractRepository;

    @Mock
    private ProductService productService;

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
    void hireSuccess() {
        var product = buildProductEntity();
        var contract = buildContractEntity();
        var request = buildContractRequest();

        when(productService.findByType(any(ProductType.class))).thenReturn(product);
        when(contractRepository.save(any(ContractEntity.class))).thenReturn(contract);

        var response = contractService.hire(request);

        assertNotNull(response.getIdContract());
    }

    @Test
    void hireShouldReturnEntityException() {
        var product = buildProductEntity();
        var request = buildContractRequest();

        when(productService.findByType(any(ProductType.class))).thenReturn(product);
        when(contractRepository.save(any(ContractEntity.class))).thenThrow(new RuntimeException());

        assertThrows(SaveEntityException.class, () -> contractService.hire(request));
    }

}