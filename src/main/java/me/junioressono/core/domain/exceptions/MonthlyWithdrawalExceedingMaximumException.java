package me.junioressono.core.domain.exceptions;

public class MonthlyWithdrawalExceedingMaximumException extends RuntimeException {
    public MonthlyWithdrawalExceedingMaximumException() {
        super("Exceeded monthly withdrawal limit.");
    }
}
