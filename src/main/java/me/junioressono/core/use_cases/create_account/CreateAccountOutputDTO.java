package me.junioressono.core.use_cases.create_account;

import me.junioressono.core.domain.models.AccountType;

import java.math.BigDecimal;

public record CreateAccountOutputDTO(
        Integer id,
        String name,
        BigDecimal balance,
        AccountType accountType
){
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String name;
        private BigDecimal balance;
        private AccountType accountType;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder accountType(AccountType accountType) {
            this.accountType = accountType;
            return this;
        }


        public CreateAccountOutputDTO build() {
            return new CreateAccountOutputDTO(id, name, balance, accountType);
        }
    }
}
