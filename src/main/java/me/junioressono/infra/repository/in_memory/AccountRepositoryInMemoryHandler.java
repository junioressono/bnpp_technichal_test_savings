package me.junioressono.infra.repository.in_memory;

import me.junioressono.core.domain.models.Account;
import me.junioressono.core.ports.secondary.AccountRepository;

import java.util.HashMap;
import java.util.Map;

public class AccountRepositoryInMemoryHandler implements AccountRepository {
    private static AccountRepositoryInMemoryHandler INSTANCE;
    public static Integer accountNumber = 100;
    private final Map<Integer, Account> accounts = new HashMap<>();

    @Override
    public Account create(Account account) {
        account.setId(++accountNumber);
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public Account update(Account account) {
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public Account getAccount(Integer accountId) {
        return accounts.get(accountId);
    }

    @Override
    public Map<Integer, Account> getAccounts() {
        return accounts;
    }

    public static AccountRepositoryInMemoryHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountRepositoryInMemoryHandler();
        }
        return INSTANCE;
    }
}
