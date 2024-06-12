package me.junioressono.core.domain.exceptions;

public class MonthlyWithdrawalExceedingMaximumException extends Exception {
    public MonthlyWithdrawalExceedingMaximumException() {
        super("Exceeded monthly withdrawal limit.");
    }
}
