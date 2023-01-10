package com.bank.credit.service.credit;

import com.bank.credit.controller.request.credit.CreditHireRequest;
import com.bank.credit.controller.request.credit.CreditOptionsRequest;
import com.bank.credit.controller.response.credit.CreditHireResponse;
import com.bank.credit.controller.response.credit.CreditOptionsResponse;
import com.bank.credit.model.Contract;
import com.bank.credit.model.enums.ProductType;
import com.bank.credit.service.AssociateService;
import com.bank.credit.service.ContractService;
import com.bank.credit.service.ProductService;
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
        var associate = associateService.findById(request.getAssociateId());
        var product = productService.findByType(request.getProductType());
        var contract = Contract.builder()
                .associate(associate)
                .product(product)
                .paidOff(false)
                .value(request.getValue())
                .hireDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusMonths(request.getNumberOfInstallments() + 1))
                .installmentsPaid(0)
                .installmentsRemaining(request.getNumberOfInstallments())
                .build();
        var savedContract = contractService.sign(contract);
        return CreditHireResponse.builder()
                .contractId(savedContract.getId())
                .build();
    }
}
