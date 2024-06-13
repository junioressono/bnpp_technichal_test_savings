package me.junioressono.app.console.controllers;

import me.junioressono.app.console.AppConsole;
import me.junioressono.app.console.util.CustomInputReader;
import me.junioressono.core.use_cases.deposit_money.DepositMoneyInputDTO;
import me.junioressono.core.use_cases.deposit_money.DepositMoneyOutputDTO;

import java.math.BigDecimal;

public class DepositMoneyController implements Controller {
    private final AppConsole appConsole;
    private final CustomInputReader customInputReader;

    public DepositMoneyController(AppConsole appConsole, CustomInputReader customInputReader) {
        this.appConsole = appConsole;
        this.customInputReader = customInputReader;
    }

    @Override
    public void handle() {
        System.out.println("""
                    ==============================================
                    OPERATION  -  DEPOSIT MONEY
                    ----------------------------------------------
                    """);
        var accountId = customInputReader.readNumber("Enter your account identifier:");
        var amount = customInputReader.readString("Enter the interestAmount to deposit:");

        DepositMoneyInputDTO depositMoneyInputDTO = new DepositMoneyInputDTO(accountId, new BigDecimal(amount));
        DepositMoneyOutputDTO depositMoneyOutputDTO = appConsole
                .depositMoneyUseCaseHandler
                .handle(depositMoneyInputDTO);

        System.out.printf("""
                    \n
                    %s euros have been deposited into your account.
                    \n
                    """, depositMoneyOutputDTO.amount());
    }
}
