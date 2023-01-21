package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.annotation.transaction.DepositTransactionStandard;
import br.com.sicredi.bank.annotation.transaction.TransferTransactionStandard;
import br.com.sicredi.bank.annotation.transaction.WithdrawTransactionStandard;
import br.com.sicredi.bank.model.request.transaction.DepositTransactionRequest;
import br.com.sicredi.bank.model.request.transaction.TransferTransactionRequest;
import br.com.sicredi.bank.model.request.transaction.WithdrawTransactionRequest;
import br.com.sicredi.bank.model.response.transaction.TransactionResponse;
import br.com.sicredi.bank.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/withdraw")
    @WithdrawTransactionStandard
    public ResponseEntity<TransactionResponse> withdraw(@Valid @RequestBody WithdrawTransactionRequest request) {
        return transactionService.withdraw(request);
    }

    @PostMapping("/deposit")
    @DepositTransactionStandard
    public ResponseEntity<TransactionResponse> deposit(@Valid @RequestBody DepositTransactionRequest request) {
        return transactionService.deposit(request);
    }

    @PostMapping("/transfer")
    @TransferTransactionStandard
    public ResponseEntity<TransactionResponse> transfer(@Valid @RequestBody TransferTransactionRequest request) {
        return transactionService.transfer(request);
    }

}
