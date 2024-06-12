package me.junioressono.core.domain.exceptions;

public class InvalidInitialBalanceException extends Exception {
    public InvalidInitialBalanceException() {
        super("The initial interestAmount must be a positive number and not less than zero.");
    }
}
