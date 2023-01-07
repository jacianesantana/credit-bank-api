package com.bank.credit.controller;

import com.bank.credit.controller.request.account.AccountDepositRequest;
import com.bank.credit.controller.request.account.AccountTransferRequest;
import com.bank.credit.controller.request.account.AccountWithdrawRequest;
import com.bank.credit.controller.response.account.AccountDepositResponse;
import com.bank.credit.controller.response.account.AccountTransferResponse;
import com.bank.credit.controller.response.account.AccountWithdrawResponse;
import com.bank.credit.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PatchMapping("/withdraw")
    public ResponseEntity<AccountWithdrawResponse> withdraw(@RequestBody AccountWithdrawRequest request) {
        return ResponseEntity.ok().body(accountService.withdraw(request));
    }

    @PatchMapping("/transfer")
    public ResponseEntity<AccountTransferResponse> transfer(@RequestBody AccountTransferRequest request) {
        return ResponseEntity.ok().body(accountService.transfer(request));
    }

    @PatchMapping("/deposit")
    public ResponseEntity<AccountDepositResponse> deposit(@RequestBody AccountDepositRequest request) {
        return ResponseEntity.ok().body(accountService.deposit(request));
    }
}
