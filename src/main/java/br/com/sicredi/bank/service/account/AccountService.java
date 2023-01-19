package br.com.sicredi.bank.service.account;

import br.com.sicredi.bank.controller.response.account.BalanceAccountResponse;
import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.entity.AssociateEntity;
import br.com.sicredi.bank.entity.enums.AccountType;
import br.com.sicredi.bank.exception.FindEntityException;
import br.com.sicredi.bank.mapper.AccountMapper;
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
    private final AccountMapper accountMapper;

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
        try {
            return accountRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new FindEntityException("Conta não encontrada!");
        }
    }

    public AccountEntity findByAgencyAndNumber(Integer agency, Integer number) {
        try {
            return accountRepository.findByAgencyAndNumber(agency, number).orElseThrow();
        } catch (Exception e) {
            throw new FindEntityException("Conta não encontrada!");
        }
    }

    public BalanceAccountResponse findBalance(Long id) {
        var account = findById(id);

        return accountMapper.accountToBalanceAccountResponse(account);
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
