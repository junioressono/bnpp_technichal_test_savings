package me.junioressono.app.console.controllers;

import me.junioressono.app.console.AppConsole;
import me.junioressono.app.console.util.CustomInputReader;
import me.junioressono.core.use_cases.withdrawal_money.WithdrawalMoneyInputDTO;
import me.junioressono.core.use_cases.withdrawal_money.WithdrawalMoneyOutputDTO;

import java.math.BigDecimal;

public class WithdrawalMoneyController implements Controller {
    private final AppConsole appConsole;
    private final CustomInputReader customInputReader;

    public WithdrawalMoneyController(AppConsole appConsole, CustomInputReader customInputReader) {
        this.appConsole = appConsole;
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

        WithdrawalMoneyOutputDTO withdrawalMoneyOutputDTO = appConsole
                .withdrawalMoneyUseCaseHandler
                .handle(withdrawalMoneyInputDTO);

        System.out.printf("""
                    \n
                    %s euros have been withdrawn from your account.
                    \n
                    """, withdrawalMoneyOutputDTO.amount());
    }
}
