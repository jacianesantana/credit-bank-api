package br.com.sicredi.bank.service;

import br.com.sicredi.bank.controller.request.contract.ContractRequest;
import br.com.sicredi.bank.controller.response.contract.ContractResponse;
import br.com.sicredi.bank.entity.ContractEntity;
import br.com.sicredi.bank.mapper.AssociateMapper;
import br.com.sicredi.bank.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final AssociateService associateService;
    private final AssociateMapper associateMapper;
    private final ProductService productService;

    public List<ContractEntity> findContracts(Long idAssociate) {
        log.info("Buscando contratos para o associado com id: {}", idAssociate);
        return contractRepository.findByIdAssociate(idAssociate);
    }

    public ContractResponse hire(ContractRequest request) {
        var findAssociate = associateService.findById(request.getIdAssociate());
        var associate = associateMapper.findAssociateResponseToAssociate(findAssociate);
        var product = productService.findByType(request.getProductType());
        var contract = ContractEntity.builder()
                .associate(associate)
                .product(product)
                .paidOff(false)
                .value(request.getValue())
                .hireDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusMonths(request.getNumberOfInstallments() + 1))
                .installmentsPaid(0)
                .installmentsRemaining(request.getNumberOfInstallments())
                .firstPaymentDate(LocalDate.now().plusMonths(1))
                .build();

        var savedContract = contractRepository.save(contract);

        return ContractResponse.builder()
                .idContract(savedContract.getId())
                .build();
    }

}
