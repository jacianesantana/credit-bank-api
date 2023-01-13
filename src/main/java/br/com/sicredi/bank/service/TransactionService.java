package br.com.sicredi.bank.service;

import br.com.sicredi.bank.controller.request.account.AccountTransferRequest;
import br.com.sicredi.bank.controller.request.account.AccountWithdrawRequest;
import br.com.sicredi.bank.controller.response.account.AccountWithdrawResponse;
import br.com.sicredi.bank.controller.response.account.AccountDepositResponse;
import br.com.sicredi.bank.controller.response.account.AccountTransferResponse;
import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.enums.TransactionType;
import br.com.sicredi.bank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public AccountDepositResponse deposit(AccountEntity request, TransactionType type, BigDecimal value) {


        return new AccountDepositResponse();
    }

    public AccountWithdrawResponse withdraw(AccountWithdrawRequest request) {
        return new AccountWithdrawResponse();
    }

    public AccountTransferResponse transfer(AccountTransferRequest request) {
        return new AccountTransferResponse();
    }

}
