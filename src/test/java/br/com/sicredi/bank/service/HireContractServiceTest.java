package br.com.sicredi.bank.service;

import br.com.sicredi.bank.controller.response.associate.FindAssociateResponse;
import br.com.sicredi.bank.controller.response.associate.SaveAssociateResponse;
import br.com.sicredi.bank.entity.ContractEntity;
import br.com.sicredi.bank.entity.enums.ProductType;
import br.com.sicredi.bank.exception.SaveEntityException;
import br.com.sicredi.bank.mapper.AssociateMapper;
import br.com.sicredi.bank.repository.ContractRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.sicredi.bank.builder.AssociateBuilder.*;
import static br.com.sicredi.bank.builder.ContractBuilder.buildContractEntity;
import static br.com.sicredi.bank.builder.ContractBuilder.buildContractRequest;
import static br.com.sicredi.bank.builder.ProductBuilder.buildProductEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HireContractServiceTest {

    @InjectMocks
    private HireContractService hireContractService;

    @Mock
    private ContractRepository contractRepository;

    @Mock
    private ProductService productService;

    @Mock
    private AssociateService associateService;

    @Mock
    private AssociateMapper associateMapper;

    @Test
    void hireSuccess() {
        var associate = buildAssociate();
        var findAssociateResponse = buildFindAssociateResponse();
        var product = buildProductEntity();
        var request = buildContractRequest();
        var contract = buildContractEntity();

        when(associateService.findById(anyLong())).thenReturn(findAssociateResponse);
        when(associateMapper.findAssociateResponseToAssociate(any(FindAssociateResponse.class))).thenReturn(associate);
        when(productService.findByType(any(ProductType.class))).thenReturn(product);
        when(contractRepository.save(any(ContractEntity.class))).thenReturn(contract);

        var response = hireContractService.hire(request);

        assertNotNull(response.getId());
        assertFalse(response.getPaidOff());
    }

    @Test
    void hireShouldReturnEntityException() {
        var associate = buildAssociate();
        var findAssociateResponse = buildFindAssociateResponse();
        var product = buildProductEntity();
        var request = buildContractRequest();

        when(associateService.findById(anyLong())).thenReturn(findAssociateResponse);
        when(associateMapper.findAssociateResponseToAssociate(any(FindAssociateResponse.class))).thenReturn(associate);
        when(productService.findByType(any(ProductType.class))).thenReturn(product);
        when(contractRepository.save(any(ContractEntity.class))).thenThrow(new RuntimeException());

        assertThrows(SaveEntityException.class, () -> hireContractService.hire(request));
    }

}