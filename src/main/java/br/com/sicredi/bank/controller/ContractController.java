package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.annotation.contract.ContractStandard;
import br.com.sicredi.bank.controller.request.contract.ContractRequest;
import br.com.sicredi.bank.controller.response.contract.ContractResponse;
import br.com.sicredi.bank.service.HireContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contract")
public class ContractController {

    private final HireContractService hireContractService;

    @PostMapping("/hire")
    @ContractStandard
    public ResponseEntity<ContractResponse> hire(@Valid @RequestBody ContractRequest request) {
        return ResponseEntity.ok().body(hireContractService.hire(request));
    }

}
