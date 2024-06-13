package me.junioressono.app.shell.controllers;

import me.junioressono.app.shell.AppShell;
import me.junioressono.app.shell.util.CustomInputReader;
import me.junioressono.core.use_cases.deposit_money.DepositMoneyInputDTO;
import me.junioressono.core.use_cases.deposit_money.DepositMoneyOutputDTO;

import java.math.BigDecimal;

public class DepositMoneyController implements Controller {
    private final AppShell appShell;
    private final CustomInputReader customInputReader;

    public DepositMoneyController(AppShell appShell, CustomInputReader customInputReader) {
        this.appShell = appShell;
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
        DepositMoneyOutputDTO depositMoneyOutputDTO = appShell
                .depositMoneyUseCaseHandler
                .handle(depositMoneyInputDTO);

        System.out.printf("""
                    \n
                    %s euros have been deposited into your account.
                    \n
                    """, depositMoneyOutputDTO.amount());
    }
}
