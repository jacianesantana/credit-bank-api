package com.bank.credit.controller;

import com.bank.credit.controller.request.credit.ConsignedOptionRequest;
import com.bank.credit.controller.request.credit.FinancingOptionRequest;
import com.bank.credit.controller.request.credit.PersonalLoanOptionRequest;
import com.bank.credit.controller.response.credit.ConsignedOptionResponse;
import com.bank.credit.controller.response.credit.FinancingOptionResponse;
import com.bank.credit.controller.response.credit.PersonalLoanOptionResponse;
import com.bank.credit.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @GetMapping("/personalLoan")
    public ResponseEntity<PersonalLoanOptionResponse> personalLoanOptions(@RequestBody PersonalLoanOptionRequest request) {
        return ResponseEntity.ok().body(creditService.personalLoanOptions(request));
    }

    @GetMapping("/financing")
    public ResponseEntity<FinancingOptionResponse> financingOptions(@RequestBody FinancingOptionRequest request){
        return ResponseEntity.ok().body(creditService.financingOptions(request));
    }

    @GetMapping("/consigned")
    public ResponseEntity<ConsignedOptionResponse> consignedOptions(@RequestBody ConsignedOptionRequest request) {
        return ResponseEntity.ok().body(creditService.consignedOptions(request));
    }
}
