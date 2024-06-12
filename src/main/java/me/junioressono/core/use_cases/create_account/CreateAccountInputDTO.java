package me.junioressono.core.use_cases.create_account;

import me.junioressono.core.domain.models.AccountType;

import java.math.BigDecimal;

public record CreateAccountInputDTO (
        String name,
        BigDecimal initialBalance,
        AccountType accountType
){}
