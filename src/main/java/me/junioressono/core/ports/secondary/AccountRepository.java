package me.junioressono.core.ports.secondary;

import me.junioressono.core.domain.models.Account;

import java.util.Map;

public interface AccountRepository {
    public Account create(Account account);
    public Account update(Account account);
    public Account getAccount(Integer accountId);
    public Map<Integer, Account> getAccounts();
}
