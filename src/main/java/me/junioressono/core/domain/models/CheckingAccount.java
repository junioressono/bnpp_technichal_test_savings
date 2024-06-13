package me.junioressono.core.domain.models;

import me.junioressono.core.domain.exceptions.InsufficientBalanceException;
import me.junioressono.core.domain.exceptions.InvalidWithdrawalAmountException;

import java.math.BigDecimal;

public final class CheckingAccount extends Account{

    public CheckingAccount(String holderName, BigDecimal initialBalance) {
        super(holderName, initialBalance);
    }


    @Override
    public void withdraw(BigDecimal amount) {
        if (amount.signum() <= 0)
            throw new InvalidWithdrawalAmountException();
        if (balance.compareTo(amount) < 0)
            throw new InsufficientBalanceException();

        balance = balance.subtract(amount);
    }
}
