package me.junioressono.app.shell.controllers;

import me.junioressono.app.shell.AppShell;
import me.junioressono.app.shell.util.CustomInputReader;
import me.junioressono.core.use_cases.withdrawal_money.WithdrawalMoneyInputDTO;
import me.junioressono.core.use_cases.withdrawal_money.WithdrawalMoneyOutputDTO;

import java.math.BigDecimal;

public class WithdrawalMoneyController implements Controller {
    private final AppShell appShell;
    private final CustomInputReader customInputReader;

    public WithdrawalMoneyController(AppShell appShell, CustomInputReader customInputReader) {
        this.appShell = appShell;
        this.customInputReader = customInputReader;
    }

    @Override
    public void handle() {
        System.out.println("""
                    ==============================================
                    OPERATION  -  WITHDRAWAL MONEY
                    ----------------------------------------------
                    """);
        var withdrawalAccountId = customInputReader.readNumber("Enter your account identifier:");
        var withdrawalAmount = customInputReader.readString("Enter the interestAmount to withdrawal:");

        WithdrawalMoneyInputDTO withdrawalMoneyInputDTO = new WithdrawalMoneyInputDTO(
                withdrawalAccountId, new BigDecimal(withdrawalAmount)
        );

        WithdrawalMoneyOutputDTO withdrawalMoneyOutputDTO = appShell
                .withdrawalMoneyUseCaseHandler
                .handle(withdrawalMoneyInputDTO);

        System.out.printf("""
                    \n
                    %s euros have been withdrawn from your account.
                    \n
                    """, withdrawalMoneyOutputDTO.amount());
    }
}
