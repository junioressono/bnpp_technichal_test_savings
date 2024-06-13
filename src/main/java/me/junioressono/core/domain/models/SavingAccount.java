package me.junioressono.core.domain.models;

import me.junioressono.core.domain.exceptions.InsufficientBalanceException;
import me.junioressono.core.domain.exceptions.MonthlyWithdrawalExceedingMaximumException;
import me.junioressono.core.domain.exceptions.InvalidWithdrawalAmountException;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class SavingAccount extends Account implements AccountWithInterest {

    public static final BigDecimal INTEREST_RATE = new BigDecimal("0.05");
    public static final BigDecimal MAX_WITHDRAWAL_PER_MONTH = new BigDecimal("1000");
    private LocalDate lastWithdrawalDate = LocalDate.now().minusMonths(1);
    private BigDecimal monthlyWithdrawalTotal = BigDecimal.ZERO;

    public SavingAccount(String holderName, BigDecimal initialBalance) {
        super(holderName, initialBalance);
    }


    @Override
    public void withdraw(BigDecimal amount) {
        if (amount.signum() <= 0)
            throw new InvalidWithdrawalAmountException();
        if (balance.compareTo(amount) < 0)
            throw new InsufficientBalanceException();


        LocalDate today = LocalDate.now();
        if (today.getMonthValue() != lastWithdrawalDate.getMonthValue()) {
            monthlyWithdrawalTotal = BigDecimal.ZERO;
            lastWithdrawalDate = today;
        }

        if (monthlyWithdrawalTotal.add(amount).compareTo(MAX_WITHDRAWAL_PER_MONTH) > 0) {
            throw new MonthlyWithdrawalExceedingMaximumException();
        }


        balance = balance.subtract(amount);
        monthlyWithdrawalTotal = monthlyWithdrawalTotal.add(amount);
    }


    public BigDecimal calculateCurrentMonthInterest() {
        var interestAmount = balance.multiply(INTEREST_RATE);
        if (interestAmount.compareTo(BigDecimal.ZERO) > 0)
            balance = balance.add(interestAmount);
        return interestAmount;
    }

}

