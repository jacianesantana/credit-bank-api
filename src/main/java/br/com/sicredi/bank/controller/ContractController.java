package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.annotation.contract.FindContractStandard;
import br.com.sicredi.bank.annotation.contract.HireContractStandard;
import br.com.sicredi.bank.model.request.contract.ContractRequest;
import br.com.sicredi.bank.model.response.contract.FindContractResponse;
import br.com.sicredi.bank.model.response.contract.SaveContractResponse;
import br.com.sicredi.bank.service.contract.ContractService;
import br.com.sicredi.bank.service.contract.SignContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contract")
public class ContractController {

    private final SignContractService signContractService;
    private final ContractService contractService;

    @PostMapping("/sign")
    @HireContractStandard
    public ResponseEntity<SaveContractResponse> sign(@Valid @RequestBody ContractRequest request) {
        return signContractService.sign(request);
    }

    @GetMapping("/find/{id}")
    @FindContractStandard
    public ResponseEntity<FindContractResponse> findById(@PathVariable Long id) {
        return contractService.findById(id);
    }

}
