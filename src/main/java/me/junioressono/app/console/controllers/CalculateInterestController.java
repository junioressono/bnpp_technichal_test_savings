package me.junioressono.app.console.controllers;

import me.junioressono.app.console.AppConsole;
import me.junioressono.app.console.util.CustomInputReader;
import me.junioressono.core.use_cases.calculate_interest.CalculateAccountInterestInputDTO;
import me.junioressono.core.use_cases.calculate_interest.CalculateAccountInterestOutputDTO;

public class CalculateInterestController implements Controller {
    private final AppConsole appConsole;
    private final CustomInputReader customInputReader;

    public CalculateInterestController(AppConsole appConsole, CustomInputReader customInputReader) {
        this.appConsole = appConsole;
        this.customInputReader = customInputReader;
    }

    @Override
    public void handle() {
        System.out.println("""
                    ==============================================
                    OPERATION  -  CALCULATE INTEREST
                    ----------------------------------------------
                    """);

        var AccountId = customInputReader.readNumber("Enter your account identifier:");
        CalculateAccountInterestInputDTO accountInterestInputDTO = new CalculateAccountInterestInputDTO(AccountId);
        CalculateAccountInterestOutputDTO calculateAccountInterestOutputDTO = appConsole
                .calculateAccountInterestUseCaseHandler.handle(accountInterestInputDTO);

        System.out.printf("""
                    \n
                    Interest for this month is %s euros.
                    \n
                    """, calculateAccountInterestOutputDTO.interestAmount());
    }
}
