package me.junioressono.infra.repository.in_memory;

import me.junioressono.core.domain.exceptions.InvalidDepositAmountException;
import me.junioressono.core.domain.models.Account;
import me.junioressono.core.domain.models.CheckingAccount;
import me.junioressono.core.domain.models.SavingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryInMemoryHandlerTest {
    AccountRepositoryInMemoryHandler repository;

    @BeforeEach
    public void setUp() {
        repository = new AccountRepositoryInMemoryHandler();
    }

    @Test
    public void should_create_account_assigns_unique_id() {
        Account account = new SavingAccount("John Doe", new BigDecimal("1000.00"));
        Account createdAccount = repository.create(account);
        assertNotNull(createdAccount.getId());
        assertEquals(account, repository.getAccount(createdAccount.getId()));
    }

    @Test
    public void should_update_account_updates_details() throws InvalidDepositAmountException {
        Account account = new SavingAccount("John Doe", new BigDecimal("1000.00"));
        repository.create(account);
        account.deposit(new BigDecimal("500.00"));
        repository.update(account);
        assertEquals(new BigDecimal("1500.00"), repository.getAccount(account.getId()).getBalance());
    }

    @Test
    public void should_get_account_by_id_returns_correct_details() {
        Account account = new SavingAccount("Jane Doe", new BigDecimal("2000.00"));
        Account createdAccount = repository.create(account);
        Account retrievedAccount = repository.getAccount(createdAccount.getId());
        assertEquals(createdAccount, retrievedAccount);
    }

    @Test
    public void test_create_account_with_invalid_details_throws_exception() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount("Invalid User", new BigDecimal("-100.00")));
    }

    @Test
    public void should_update_non_existent_account_handles_gracefully() {
        Account nonExistentAccount = new SavingAccount("Ghost User", new BigDecimal("1000.00"));
        nonExistentAccount.setId(999);
        assertNull(repository.getAccount(999));
    }

    @Test
    public void should_get_account_with_non_existent_id_returns_null() {
        AccountRepositoryInMemoryHandler repository = AccountRepositoryInMemoryHandler.getInstance();
        assertNull(repository.getAccount(999));
    }

    @Test
    public void should_returns_all_accounts_when_multiple_accounts_exist() {
        repository.create(new SavingAccount("John Doe", new BigDecimal("1000")));
        repository.create(new CheckingAccount("Jane Doe", new BigDecimal("2000")));

        Map<Integer, Account> accounts = repository.getAccounts();
        assertEquals(2, accounts.size());
    }
}