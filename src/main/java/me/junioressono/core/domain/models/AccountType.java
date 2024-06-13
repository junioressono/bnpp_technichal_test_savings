package me.junioressono.core.domain.models;

public enum AccountType {
    CHECKING_ACCOUNT("Checking Account"),
    SAVING_ACCOUNT("Saving Account");

    private final String description;
    AccountType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
