package me.junioressono.infra.repository.in_memory;

import me.junioressono.core.domain.models.Account;
import me.junioressono.core.ports.secondary.AccountRepository;

import java.util.HashMap;
import java.util.Map;

public class AccountRepositoryInMemoryHandler implements AccountRepository {
    private static AccountRepositoryInMemoryHandler INSTANCE;
    public static Integer ID_CURSOR = 0;
    private final Map<Integer, Account> accounts = new HashMap<>();

    @Override
    public Account create(Account account) {
        account.setId(++ID_CURSOR);
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public Account update(Account account) {
        Account foundAccount = accounts.get(account.getId());
        if (foundAccount != null) {
            accounts.put(account.getId(), account);
            return account;
        }
        return null;
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
