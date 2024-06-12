package me.junioressono.core.domain.exceptions;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException() {
        super("Your interestAmount is insufficient to make this transaction.");
    }
}
