package me.junioressono.core.use_cases.calculate_interest;

import java.math.BigDecimal;

public record CalculateAccountInterestOutputDTO(
        BigDecimal interestAmount
){
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BigDecimal interestAmount;
        public Builder interestAmount(BigDecimal interestAmount) {
            this.interestAmount = interestAmount;
            return this;
        }

        public CalculateAccountInterestOutputDTO build() {
            return new CalculateAccountInterestOutputDTO(interestAmount);
        }
    }
}
