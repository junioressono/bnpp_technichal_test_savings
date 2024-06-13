package me.junioressono.app.shell.controllers;

import me.junioressono.app.shell.AppShell;
import me.junioressono.app.shell.util.CustomInputReader;
import me.junioressono.core.use_cases.display_balance.DisplayAccountBalanceInputDTO;
import me.junioressono.core.use_cases.display_balance.DisplayAccountBalanceOutputDTO;

public class DisplayBalanceController implements Controller {
    private final AppShell appShell;
    private final CustomInputReader customInputReader;

    public DisplayBalanceController(AppShell appShell, CustomInputReader customInputReader) {
        this.appShell = appShell;
        this.customInputReader = customInputReader;
    }

    @Override
    public void handle() {
        System.out.println("""
                    ==============================================
                    OPERATION  -  DISPLAY ACCOUNT BALANCE
                    ----------------------------------------------
                    """);

        var AccountId = customInputReader.readNumber("Enter your account identifier:");
        DisplayAccountBalanceInputDTO displayAccountBalanceInputDTO = new DisplayAccountBalanceInputDTO(AccountId);
        DisplayAccountBalanceOutputDTO displayAccountBalanceOutputDTO =
                appShell.displayAccountBalanceUseCaseHandler.handle(displayAccountBalanceInputDTO);

        System.out.printf("""
                    \n
                    Your current balance is %s euros.
                    \n
                    """, displayAccountBalanceOutputDTO.balance());
    }
}
