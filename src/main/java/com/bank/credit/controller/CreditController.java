package com.bank.credit.controller;

import com.bank.credit.controller.request.credit.CreditHireRequest;
import com.bank.credit.controller.request.credit.CreditOptionsRequest;
import com.bank.credit.controller.response.credit.CreditHireResponse;
import com.bank.credit.controller.response.credit.CreditOptionsResponse;
import com.bank.credit.service.credit.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @PatchMapping("/options")
    public ResponseEntity<CreditOptionsResponse> options(@RequestBody CreditOptionsRequest request) {
        return ResponseEntity.ok().body(creditService.options(request));
    }

    @PatchMapping("/hire")
    public ResponseEntity<CreditHireResponse> hire(@RequestBody CreditHireRequest request) {
        return ResponseEntity.ok().body(creditService.hire(request));
    }
}
