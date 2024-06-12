package me.junioressono.core.use_cases.deposit_money;

import java.math.BigDecimal;

public record DepositMoneyInputDTO(
        int accountId,
        BigDecimal amount
){}
