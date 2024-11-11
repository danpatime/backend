package com.example.api.service;

import com.example.api.domain.Account;
import com.example.api.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerAccount_shouldThrowException_whenEmailExists() {
        Account account = new Account();
        account.setEmail("test@example.com");

        when(accountRepository.existsByEmail(account.getEmail())).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> accountService.registerAccount(account));
    }

    @Test
    void registerAccount_shouldSave_Account_whenValid() {
        Account account = new Account();
        account.setEmail("valid@example.com");
        account.setPassword("validpassword");

        when(accountRepository.existsByEmail(account.getEmail())).thenReturn(false);
        when(accountRepository.save(account)).thenReturn(account);

        Account result = accountService.registerAccount(account);

        assertNotNull(result.getRegisteredDatetime());
        verify(accountRepository, times(1)).save(account);
    }
}
