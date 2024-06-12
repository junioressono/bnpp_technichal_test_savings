package me.junioressono.core.domain.models;

import me.junioressono.core.domain.exceptions.WithdrawalInvalidAmountException;

import java.math.BigDecimal;

public final class CheckingAccount extends Account{

    public CheckingAccount(String holderName, BigDecimal initialBalance) {
        super(holderName, initialBalance);
    }


    @Override
    public void withdraw(BigDecimal amount) throws Exception {
        if (amount.signum() <= 0)
            throw new WithdrawalInvalidAmountException();
        balance = balance.subtract(amount);
    }
}
