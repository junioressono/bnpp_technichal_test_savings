package me.junioressono.core.domain.models;

import java.math.BigDecimal;

public interface AccountWithInterest {
    BigDecimal calculateCurrentMonthInterest();
}
