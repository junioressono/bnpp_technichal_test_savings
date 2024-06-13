package me.junioressono.app.console.controllers;

import me.junioressono.app.console.AppConsole;
import me.junioressono.app.console.util.CustomInputReader;
import me.junioressono.core.use_cases.display_balance.DisplayAccountBalanceInputDTO;
import me.junioressono.core.use_cases.display_balance.DisplayAccountBalanceOutputDTO;

public class DisplayBalanceController implements Controller {
    private final AppConsole appConsole;
    private final CustomInputReader customInputReader;

    public DisplayBalanceController(AppConsole appConsole, CustomInputReader customInputReader) {
        this.appConsole = appConsole;
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
                appConsole.displayAccountBalanceUseCaseHandler.handle(displayAccountBalanceInputDTO);

        System.out.printf("""
                    \n
                    Your current balance is %s euros.
                    \n
                    """, displayAccountBalanceOutputDTO.balance());
    }
}
