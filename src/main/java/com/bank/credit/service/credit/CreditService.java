package com.bank.credit.service.credit;

import com.bank.credit.controller.request.credit.CreditOptionsRequest;
import com.bank.credit.controller.response.credit.CreditOptionsResponse;
import com.bank.credit.model.enums.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final PersonalLoanService personalLoanService;
    private final ConsignedService consignedService;
    private final FinancingService financingService;

    public CreditOptionsResponse options(CreditOptionsRequest request) {
        var choice = request.getProductType();

        if (choice.equals(ProductType.FINANCING)) {
            return financingService.options(request);
        } else if (choice.equals(ProductType.CONSIGNED)) {
            return consignedService.options(request);
        }
        return personalLoanService.options(request);
    }
}
