package me.junioressono.core.use_cases.withdrawal_money;

import java.math.BigDecimal;

public record WithdrawalMoneyOutputDTO(
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



        public WithdrawalMoneyOutputDTO build() {
            return new WithdrawalMoneyOutputDTO(amount);
        }
    }
}
