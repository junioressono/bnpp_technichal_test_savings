package me.junioressono.core.domain.models;

import me.junioressono.core.domain.exceptions.InvalidDepositAmountException;

import java.math.BigDecimal;

public sealed abstract class Account permits SavingAccount, CheckingAccount {
    protected Integer id = null;
    protected String customerName;
    protected BigDecimal balance;

    public Account(String customerName, BigDecimal initialBalance) {
        if (initialBalance.signum() < 0)
            throw new IllegalArgumentException("Amount of deposit must be positive or zero");

        //this.id = UUID.randomUUID().toString();
        this.customerName = customerName;
        this.balance = initialBalance;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        if (this.id == null)
            this.id = id;
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

    public void deposit(BigDecimal amount) throws InvalidDepositAmountException {
        if (amount.signum() <= 0)
            throw new InvalidDepositAmountException();
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


