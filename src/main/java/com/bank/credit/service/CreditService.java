package com.bank.credit.service;

import com.bank.credit.controller.request.credit.ConsignedOptionRequest;
import com.bank.credit.controller.request.credit.FinancingOptionRequest;
import com.bank.credit.controller.request.credit.PersonalLoanOptionRequest;
import com.bank.credit.controller.response.credit.ConsignedOptionResponse;
import com.bank.credit.controller.response.credit.FinancingOptionResponse;
import com.bank.credit.controller.response.credit.PersonalLoanOptionResponse;
import com.bank.credit.repository.ContractRepository;
import com.bank.credit.repository.ProductRepository;
import com.bank.credit.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final ContractRepository contractRepository;
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;

    public PersonalLoanOptionResponse personalLoanOptions(PersonalLoanOptionRequest request) {
        return new PersonalLoanOptionResponse();
    }

    public FinancingOptionResponse financingOptions(FinancingOptionRequest request) {
        return new FinancingOptionResponse();
    }

    public ConsignedOptionResponse consignedOptions(ConsignedOptionRequest request) {
        return new ConsignedOptionResponse();
    }
}
