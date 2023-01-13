package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

//    @PatchMapping("/withdraw")
//    public ResponseEntity<AccountWithdrawResponse> withdraw(@RequestBody AccountWithdrawRequest request) {
//        return ResponseEntity.ok().body(accountService.withdraw(request));
//    }
//
//    @PatchMapping("/transfer")
//    public ResponseEntity<AccountTransferResponse> transfer(@RequestBody AccountTransferRequest request) {
//        return ResponseEntity.ok().body(accountService.transfer(request));
//    }
//
//    @PatchMapping("/deposit")
//    public ResponseEntity<AccountDepositResponse> deposit(@RequestBody AccountDepositRequest request) {
//        return ResponseEntity.ok().body(accountService.deposit(request));
//    }

}
