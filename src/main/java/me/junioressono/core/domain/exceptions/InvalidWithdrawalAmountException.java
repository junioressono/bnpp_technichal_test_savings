package me.junioressono.core.domain.exceptions;

public class InvalidWithdrawalAmountException extends RuntimeException {
    public InvalidWithdrawalAmountException() {
        super("Amount to withdraw must be positive and non-zero");
    }
}
