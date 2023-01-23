package br.com.sicredi.bank.service.contract;

import br.com.sicredi.bank.mapper.ContractMapper;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static br.com.sicredi.bank.utils.Message.CONTRACT_SAVE_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignContractService {

    private final ProductService productService;
    private final ContractRepository contractRepository;
    private final AssociateService associateService;
    private final AssociateMapper associateMapper;
    private final ContractMapper contractMapper;

    public ResponseEntity<SaveContractResponse> sign(ContractRequest request) {
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
            var response = contractMapper.contractToSaveContractResponse(savedContract);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.info("Não foi possivel salvar o cotrato no banco de dados. Motivo: {}", e.getMessage());
            throw new SaveEntityException(CONTRACT_SAVE_ERROR);
        }
    }

}
