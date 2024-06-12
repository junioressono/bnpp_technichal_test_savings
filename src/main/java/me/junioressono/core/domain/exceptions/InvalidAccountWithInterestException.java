package me.junioressono.core.domain.exceptions;

public class InvalidAccountWithInterestException extends Exception {
    public InvalidAccountWithInterestException() {
        super(
        """
        The account type provided does not support monthly interest.
        """
        );
    }
}
