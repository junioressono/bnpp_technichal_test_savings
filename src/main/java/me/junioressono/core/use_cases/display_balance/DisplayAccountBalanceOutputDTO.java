package me.junioressono.core.use_cases.display_balance;

import java.math.BigDecimal;

public record DisplayAccountBalanceOutputDTO(
        BigDecimal balance
){
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BigDecimal balance;
        public Builder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }


        public DisplayAccountBalanceOutputDTO build() {
            return new DisplayAccountBalanceOutputDTO(balance);
        }
    }
}
