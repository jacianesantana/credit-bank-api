package br.com.sicredi.bank.service.credit;

import br.com.sicredi.bank.controller.request.credit.CreditHireRequest;
import br.com.sicredi.bank.controller.response.credit.CreditHireResponse;
import br.com.sicredi.bank.service.AssociateService;
import br.com.sicredi.bank.service.ContractService;
import br.com.sicredi.bank.service.ProductService;
import br.com.sicredi.bank.controller.request.credit.CreditOptionsRequest;
import br.com.sicredi.bank.controller.response.credit.CreditOptionsResponse;
import br.com.sicredi.bank.entity.ContractEntity;
import br.com.sicredi.bank.entity.enums.ProductType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditService {

    private final PersonalLoanService personalLoanService;
    private final ConsignedService consignedService;
    private final FinancingService financingService;
    private final ContractService contractService;
    private final AssociateService associateService;
    private final ProductService productService;

    public CreditOptionsResponse options(CreditOptionsRequest request) {
        var choice = request.getProductType();

        if (choice.equals(ProductType.FINANCIAMENTO)) {
            return financingService.options(request);
        } else if (choice.equals(ProductType.CONSIGNADO)) {
            return consignedService.options(request);
        }
        return personalLoanService.options(request);
    }

    public CreditHireResponse hire(CreditHireRequest request) {
        var associate = associateService.findById(request.getIdAssociate());
        var product = productService.findByType(request.getProductType());
        var contract = ContractEntity.builder()
                .idAssociate(request.getIdAssociate())
                .idProduct(product.getId())
                .paidOff(false)
                .value(request.getValue())
                .hireDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusMonths(request.getNumberOfInstallments() + 1))
                .installmentsPaid(0)
                .installmentsRemaining(request.getNumberOfInstallments())
                .build();
        var savedContract = contractService.sign(contract);
        return CreditHireResponse.builder()
                .idContract(savedContract.getId())
                .build();
    }
}
