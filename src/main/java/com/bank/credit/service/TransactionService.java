package com.bank.credit.service;

import com.bank.credit.controller.request.account.AccountDepositRequest;
import com.bank.credit.controller.request.account.AccountTransferRequest;
import com.bank.credit.controller.request.account.AccountWithdrawRequest;
import com.bank.credit.controller.response.account.AccountDepositResponse;
import com.bank.credit.controller.response.account.AccountTransferResponse;
import com.bank.credit.controller.response.account.AccountWithdrawResponse;
import com.bank.credit.model.Account;
import com.bank.credit.model.enums.TransactionType;
import com.bank.credit.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public AccountDepositResponse deposit(Account request, TransactionType type, BigDecimal value) {


        return new AccountDepositResponse();
    }

    public AccountWithdrawResponse withdraw(AccountWithdrawRequest request) {
        return new AccountWithdrawResponse();
    }

    public AccountTransferResponse transfer(AccountTransferRequest request) {
        return new AccountTransferResponse();
    }

}
