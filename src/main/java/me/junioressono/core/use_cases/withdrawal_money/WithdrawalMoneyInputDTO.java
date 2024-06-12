package me.junioressono.core.use_cases.withdrawal_money;

import java.math.BigDecimal;

public record WithdrawalMoneyInputDTO(
        int accountId,
        BigDecimal amount
){}
