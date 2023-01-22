package br.com.sicredi.bank.service.contract;

import br.com.sicredi.bank.model.request.contract.ContractRequest;
import br.com.sicredi.bank.model.response.contract.SaveContractResponse;
import br.com.sicredi.bank.model.entity.ContractEntity;
import br.com.sicredi.bank.exception.SaveEntityException;
import br.com.sicredi.bank.mapper.AssociateMapper;
import br.com.sicredi.bank.repository.ContractRepository;
import br.com.sicredi.bank.service.product.ProductService;
import br.com.sicredi.bank.service.associate.AssociateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class HireContractService {

    private final ProductService productService;
    private final ContractRepository contractRepository;
    private final AssociateService associateService;
    private final AssociateMapper associateMapper;

    public ResponseEntity<SaveContractResponse> hire(ContractRequest request) {
        var associateResponse = associateService.findById(request.getIdAssociate());
        var associate = associateMapper.findAssociateResponseToAssociate(associateResponse.getBody());
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

        try {
            var savedContract = contractRepository.save(contract);
            var response = SaveContractResponse.builder()
                    .id(savedContract.getId())
                    .paidOff(savedContract.getPaidOff())
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.info("Não foi possivel salvar o cotrato no banco de dados. Motivo: {}", e.getMessage());
            throw new SaveEntityException("Não foi possível salvar o contrato.");
        }
    }

}
