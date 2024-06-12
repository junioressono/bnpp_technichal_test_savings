package me.junioressono.core.domain.models;

import java.math.BigDecimal;
import java.util.UUID;

public sealed abstract class Account permits SavingAccount, CheckingAccount {
    protected final String id;
    protected String customerName;
    protected BigDecimal balance;

    public Account(String customerName, BigDecimal initialBalance) {
        if (initialBalance.signum() < 0)
            throw new IllegalArgumentException("Amount of deposit must be positive or zero");

        this.id = UUID.randomUUID().toString();
        this.customerName = customerName;
        this.balance = initialBalance;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void deposit(BigDecimal amount) {
        if (amount.signum() <= 0)
            throw new IllegalArgumentException("Amount of deposit must be positive and non-zero");
        balance = balance.add(amount);
    }

    public abstract void withdraw(BigDecimal amount) throws Exception;

    @Override
    public String toString() {
        return String.format("""
                Account Details: {
                    id = %s
                    name = %s
                    balance = %s
                }
                """,
                id, customerName, balance
                );
    }
}


