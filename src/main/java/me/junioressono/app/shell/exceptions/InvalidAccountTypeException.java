package me.junioressono.app.shell.exceptions;

public class InvalidAccountTypeException extends RuntimeException {
    public InvalidAccountTypeException() {
        super(
        """
        The account type provided is not valid
        Enter 1 or 2 for Checking or Saving account respectively
        """
        );
    }
}
