package me.junioressono.core.domain.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Your balance is insufficient to make this transaction.");
    }
}
