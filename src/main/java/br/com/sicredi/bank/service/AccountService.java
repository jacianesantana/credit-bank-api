package br.com.sicredi.bank.service;

import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.AssociateEntity;
import br.com.sicredi.bank.entity.enums.AccountType;
import br.com.sicredi.bank.repository.AccountRepository;
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

    public AccountEntity create(AssociateEntity associateEntity, AccountType type) {
        log.info("Criando uma conta do tipo {} para o associado com o id {}", type, associateEntity.getId());
        var account = AccountEntity.builder()
                .associate(associateEntity)
                .type(type)
                .agency(AGENCY)
                .number(generateAccountNumber())
                .balance(BigDecimal.ZERO)
                .build();
        return accountRepository.save(account);
    }

    public AccountEntity findById(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    public AccountEntity findByAgencyAndNumber(Integer agency, Integer number) {
        return accountRepository.findByAgencyAndNumber(agency, number).orElseThrow();
    }

    public void save(AccountEntity account) {
        accountRepository.save(account);
    }

    private Integer generateAccountNumber() {
        var random = new Random();
        var builder = new StringBuilder();

        for (int i = 0; i < ACCOUNT_DIGITS; i++) {
            builder.append((random.nextInt(10)));
        }

        return Integer.parseInt(builder.toString());
    }

}
