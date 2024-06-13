package me.junioressono.core.domain.exceptions;

public class InvalidAccountWithInterestException extends RuntimeException {
    public InvalidAccountWithInterestException() {
        super(
        """
        The account type provided does not support monthly interest.
        """
        );
    }
}
