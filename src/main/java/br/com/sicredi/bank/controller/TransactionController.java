package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.annotation.transaction.DepositTransactionStandard;
import br.com.sicredi.bank.annotation.transaction.TransferTransactionStandard;
import br.com.sicredi.bank.annotation.transaction.WithdrawTransactionStandard;
import br.com.sicredi.bank.controller.request.transaction.DepositTransactionRequest;
import br.com.sicredi.bank.controller.request.transaction.TransferTransactionRequest;
import br.com.sicredi.bank.controller.request.transaction.WithdrawTransactionRequest;
import br.com.sicredi.bank.controller.response.transaction.TransactionResponse;
import br.com.sicredi.bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/withdraw")
    @WithdrawTransactionStandard
    public ResponseEntity<TransactionResponse> withdraw(@Valid @RequestBody WithdrawTransactionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.withdraw(request));
    }

    @PostMapping("/deposit")
    @DepositTransactionStandard
    public ResponseEntity<TransactionResponse> deposit(@Valid @RequestBody DepositTransactionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.deposit(request));
    }

    @PostMapping("/transfer")
    @TransferTransactionStandard
    public ResponseEntity<TransactionResponse> transfer(@Valid @RequestBody TransferTransactionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.transfer(request));
    }

}
