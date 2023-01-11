package com.bank.credit.service;

import com.bank.credit.controller.request.account.AccountDepositRequest;
import com.bank.credit.controller.request.account.AccountTransferRequest;
import com.bank.credit.controller.request.account.AccountWithdrawRequest;
import com.bank.credit.controller.response.account.AccountDepositResponse;
import com.bank.credit.controller.response.account.AccountTransferResponse;
import com.bank.credit.controller.response.account.AccountWithdrawResponse;
import com.bank.credit.model.Account;
import com.bank.credit.model.Associate;
import com.bank.credit.model.enums.AccountType;
import com.bank.credit.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private static final Integer AGENCY = 1000;
    private static final Integer ACCOUNT_DIGITS = 8;

    private final AccountRepository accountRepository;

    public Account create(Associate associate, AccountType type) {
        log.info("Criando uma conta do tipo {} para o associado com o id {}", type, associate.getId());
        var account = Account.builder()
                .associate(associate)
                .type(type)
                .agency(AGENCY)
                .number(generateAccountNumber())
                .balance(BigDecimal.ZERO)
                .build();
        return accountRepository.save(account);
    }

    public AccountWithdrawResponse withdraw(AccountWithdrawRequest request) {
        return new AccountWithdrawResponse();
    }

    public AccountTransferResponse transfer(AccountTransferRequest request) {
        return new AccountTransferResponse();
    }

    public AccountDepositResponse deposit(AccountDepositRequest request) {
        return new AccountDepositResponse();
    }

    public static Integer generateAccountNumber() {
        var random = new Random();
        var builder = new StringBuilder();

        for (int i = 0; i < ACCOUNT_DIGITS; i++) {
            builder.append((random.nextInt(10)));
        }

        return Integer.parseInt(builder.toString());
    }
}
