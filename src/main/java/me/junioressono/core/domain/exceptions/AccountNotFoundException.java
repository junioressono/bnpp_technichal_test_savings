package me.junioressono.core.domain.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(int accountId) {
        super(String.format("""
                The provided Account ID "%s" is not found.
                """, accountId));
    }
}
