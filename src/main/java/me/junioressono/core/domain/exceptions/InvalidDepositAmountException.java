package me.junioressono.core.domain.exceptions;

public class InvalidDepositAmountException extends RuntimeException {
    public InvalidDepositAmountException() {
        super("The deposit interestAmount must be a positive number and not less than zero.");
    }
}
