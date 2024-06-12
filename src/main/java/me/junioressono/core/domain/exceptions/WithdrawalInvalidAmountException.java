package me.junioressono.core.domain.exceptions;

public class WithdrawalInvalidAmountException extends Exception {
    public WithdrawalInvalidAmountException() {
        super("Amount to withdraw must be positive and non-zero");
    }
}
