package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.controller.request.contract.ContractRequest;
import br.com.sicredi.bank.controller.response.contract.ContractResponse;
import br.com.sicredi.bank.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contract")
public class ContractController {

    private final ContractService contractService;

    @PatchMapping("/hire")
    public ResponseEntity<ContractResponse> hire(@Valid @RequestBody ContractRequest request) {
        return ResponseEntity.ok().body(contractService.hire(request));
    }

}
