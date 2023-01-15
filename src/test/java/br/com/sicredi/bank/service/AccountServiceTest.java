package br.com.sicredi.bank.service;

import br.com.sicredi.bank.builder.AccountBuilder;
import br.com.sicredi.bank.builder.AssociateBuilder;
import br.com.sicredi.bank.entity.AccountEntity;
import br.com.sicredi.bank.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void createSuccess() {
        var account = AccountBuilder.buildAccount();
        var associate = AssociateBuilder.buildAssociate();

        when(accountRepository.save(any(AccountEntity.class))).thenReturn(account);

        var response = accountService.create(associate, account.getType());

        assertNotNull(response.getId());
        assertEquals(associate.getId(), response.getAssociate().getId());
    }

    @Test
    void findByIdSuccess() {
        var account = AccountBuilder.buildAccount();

        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        var response = accountService.findById(account.getId());

        assertEquals(account.getId(), response.getId());
        assertEquals(account.getAgency(), response.getAgency());
        assertEquals(account.getNumber(), response.getNumber());
    }

    @Test
    void findByAgencyAndNumberSuccess() {
        var account = AccountBuilder.buildAccount();

        when(accountRepository.findByAgencyAndNumber(anyInt(), anyInt())).thenReturn(Optional.of(account));

        var response = accountService.findByAgencyAndNumber(account.getAgency(), account.getNumber());

        assertEquals(account.getId(), response.getId());
        assertEquals(account.getAgency(), response.getAgency());
        assertEquals(account.getNumber(), response.getNumber());
    }

    @Test
    void saveSuccess() {
        var account = AccountBuilder.buildAccount();

        when(accountRepository.save(any(AccountEntity.class))).thenReturn(account);

        assertDoesNotThrow(() -> accountService.save(account));
    }

}