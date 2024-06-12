package me.junioressono.core.domain.models;

import me.junioressono.core.domain.exceptions.InsufficientBalanceException;
import me.junioressono.core.domain.exceptions.MonthlyWithdrawalExceedingMaximumException;
import me.junioressono.core.domain.exceptions.WithdrawalInvalidAmountException;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class SavingAccount extends Account {


    private static final BigDecimal INTEREST_RATE = new BigDecimal("0.05");
    private static final BigDecimal MAX_WITHDRAWAL_PER_MONTH = new BigDecimal("1000");
    private LocalDate lastWithdrawalDate = LocalDate.now().minusMonths(1);
    private BigDecimal monthlyWithdrawalTotal = BigDecimal.ZERO;

    public SavingAccount(String holderName, BigDecimal initialBalance) {
        super(holderName, initialBalance);
    }


    @Override
    public void withdraw(BigDecimal amount) throws Exception {
        if (amount.signum() <= 0)
            throw new WithdrawalInvalidAmountException();
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

    public void addMonthlyInterest() {
        balance = balance.add(balance.multiply(INTEREST_RATE));
    }

}

