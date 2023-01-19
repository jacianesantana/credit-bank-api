package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.annotation.account.BalanceAccountStandard;
import br.com.sicredi.bank.controller.response.account.AccountStatementResponse;
import br.com.sicredi.bank.controller.response.account.BalanceAccountResponse;
import br.com.sicredi.bank.service.AccountService;
import br.com.sicredi.bank.service.AccountStatementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final AccountStatementService statementService;

    @GetMapping("/balance/{id}")
    @BalanceAccountStandard
    public ResponseEntity<BalanceAccountResponse> balance(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.findBalance(id));
    }

    @GetMapping("/statement/{id}")
    @BalanceAccountStandard
    public ResponseEntity<AccountStatementResponse> statement(@PathVariable Long id) {
        return ResponseEntity.ok(statementService.statement(id));
    }

}
