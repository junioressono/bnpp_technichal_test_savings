package me.junioressono.core.use_cases.deposit_money;

import me.junioressono.core.domain.models.AccountType;

import java.math.BigDecimal;

public record DepositMoneyOutputDTO(
        BigDecimal amount
){
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BigDecimal amount;
        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }



        public DepositMoneyOutputDTO build() {
            return new DepositMoneyOutputDTO(amount);
        }
    }
}
