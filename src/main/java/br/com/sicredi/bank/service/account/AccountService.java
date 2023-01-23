package br.com.sicredi.bank.service.account;

import br.com.sicredi.bank.exception.FindEntityException;
import br.com.sicredi.bank.mapper.AccountMapper;
import br.com.sicredi.bank.model.entity.AccountEntity;
import br.com.sicredi.bank.model.entity.AssociateEntity;
import br.com.sicredi.bank.model.enums.AccountType;
import br.com.sicredi.bank.model.response.account.BalanceAccountResponse;
import br.com.sicredi.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

import static br.com.sicredi.bank.utils.Message.ACCOUNT_FIND_ERROR;

@Service
@RequiredArgsConstructor
public class AccountService {

    public static final Integer ACCOUNT_AGENCY = 1000;
    public static final Integer ACCOUNT_DIGITS = 8;

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountEntity create(AssociateEntity associateEntity, AccountType type) {
        var account = AccountEntity.builder()
                .associate(associateEntity)
                .type(type)
                .agency(ACCOUNT_AGENCY)
                .number(generateAccountNumber())
                .balance(BigDecimal.ZERO)
                .build();

        return accountRepository.save(account);
    }

    public AccountEntity findById(Long id) {
        try {
            return accountRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new FindEntityException(ACCOUNT_FIND_ERROR);
        }
    }

    public AccountEntity findByAgencyAndNumber(Integer agency, Integer number) {
        try {
            return accountRepository.findByAgencyAndNumber(agency, number).orElseThrow();
        } catch (Exception e) {
            throw new FindEntityException(ACCOUNT_FIND_ERROR);
        }
    }

    public ResponseEntity<BalanceAccountResponse> findBalance(Long id) {
        var account = findById(id);

        return ResponseEntity.ok(accountMapper.accountToBalanceAccountResponse(account));
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
