package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.annotation.contract.FindContractStandard;
import br.com.sicredi.bank.annotation.contract.HireContractStandard;
import br.com.sicredi.bank.controller.request.contract.ContractRequest;
import br.com.sicredi.bank.controller.response.contract.FindContractResponse;
import br.com.sicredi.bank.controller.response.contract.SaveContractResponse;
import br.com.sicredi.bank.service.ContractService;
import br.com.sicredi.bank.service.HireContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contract")
public class ContractController {

    private final HireContractService hireContractService;
    private final ContractService contractService;

    @PostMapping("/hire")
    @HireContractStandard
    public ResponseEntity<SaveContractResponse> hire(@Valid @RequestBody ContractRequest request) {
        return ResponseEntity.ok().body(hireContractService.hire(request));
    }

    @GetMapping("/find/{id}")
    @FindContractStandard
    public ResponseEntity<FindContractResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contractService.findById(id));
    }

}
