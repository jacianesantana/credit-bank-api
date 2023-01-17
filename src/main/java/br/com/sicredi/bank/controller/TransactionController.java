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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PatchMapping("/withdraw")
    @WithdrawTransactionStandard
    public ResponseEntity<TransactionResponse> withdraw(@Valid @RequestBody WithdrawTransactionRequest request) {
        return ResponseEntity.ok().body(transactionService.withdraw(request));
    }

    @PatchMapping("/deposit")
    @DepositTransactionStandard
    public ResponseEntity<TransactionResponse> deposit(@Valid @RequestBody DepositTransactionRequest request) {
        return ResponseEntity.ok().body(transactionService.deposit(request));
    }

    @PatchMapping("/transfer")
    @TransferTransactionStandard
    public ResponseEntity<TransactionResponse> transfer(@Valid @RequestBody TransferTransactionRequest request) {
        return ResponseEntity.ok().body(transactionService.transfer(request));
    }

}
