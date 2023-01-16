package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.annotation.account.BalanceAccountStandard;
import br.com.sicredi.bank.controller.response.account.BalanceAccountResponse;
import br.com.sicredi.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/balance")
    @BalanceAccountStandard
    public ResponseEntity<BalanceAccountResponse> balance(@RequestParam Long id) {
        return ResponseEntity.ok(accountService.findBalance(id));
    }

/*    @GetMapping("/statement")
    @BalanceAccountStandard
    public ResponseEntity<BalanceAccountResponse> statement(@RequestParam Long id) {
        return ResponseEntity.ok(accountService.findBalance(id));
    }*/

}
