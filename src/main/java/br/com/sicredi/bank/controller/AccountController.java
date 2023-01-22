package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.annotation.account.AccountBalanceStandard;
import br.com.sicredi.bank.annotation.account.AccountStatementStandard;
import br.com.sicredi.bank.model.response.account.StatementAccountResponse;
import br.com.sicredi.bank.model.response.account.BalanceAccountResponse;
import br.com.sicredi.bank.service.account.AccountService;
import br.com.sicredi.bank.service.account.AccountStatementService;
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
    @AccountBalanceStandard
    public ResponseEntity<BalanceAccountResponse> balance(@PathVariable Long id) {
        return accountService.findBalance(id);
    }

    @GetMapping("/statement/{id}")
    @AccountStatementStandard
    public ResponseEntity<StatementAccountResponse> statement(@PathVariable Long id) {
        return statementService.statement(id);
    }
}
