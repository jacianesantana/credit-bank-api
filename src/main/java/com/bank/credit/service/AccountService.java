package com.bank.credit.service;

import com.bank.credit.controller.request.account.AccountDepositRequest;
import com.bank.credit.controller.request.account.AccountTransferRequest;
import com.bank.credit.controller.request.account.AccountWithdrawRequest;
import com.bank.credit.controller.response.account.AccountDepositResponse;
import com.bank.credit.controller.response.account.AccountTransferResponse;
import com.bank.credit.controller.response.account.AccountWithdrawResponse;
import com.bank.credit.model.Account;
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

    private static final Integer AGENCY_DIGITS = 3;
    private static final Integer ACCOUNT_DIGITS = 6;

    private final AccountRepository accountRepository;

    public Account create(Long associateId, AccountType type) {
        log.info("Criando uma conta do tipo {} para o associado com o id {}", type, associateId);
        var account = Account.builder()
                .idAssociate(associateId)
                .type(type)
                .agency(generateRandomNumber(AGENCY_DIGITS))
                .number(generateRandomNumber(ACCOUNT_DIGITS))
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

    public static String generateRandomNumber(Integer count) {
        var random = new Random();
        var builder = new StringBuilder(count);

        for (int i = 0; i < count; i++) {
            builder.append((random.nextInt(10)));
        }

        return builder.toString();
    }
}
