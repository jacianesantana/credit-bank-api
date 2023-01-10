package com.bank.credit.service;

import com.bank.credit.controller.request.account.AccountDepositRequest;
import com.bank.credit.controller.request.account.AccountTransferRequest;
import com.bank.credit.controller.request.account.AccountWithdrawRequest;
import com.bank.credit.controller.response.account.AccountDepositResponse;
import com.bank.credit.controller.response.account.AccountTransferResponse;
import com.bank.credit.controller.response.account.AccountWithdrawResponse;
import com.bank.credit.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountWithdrawResponse withdraw(AccountWithdrawRequest request) {
        return new AccountWithdrawResponse();
    }

    public AccountTransferResponse transfer(AccountTransferRequest request) {
        return new AccountTransferResponse();
    }

    public AccountDepositResponse deposit(AccountDepositRequest request) {
        return new AccountDepositResponse();
    }
}
