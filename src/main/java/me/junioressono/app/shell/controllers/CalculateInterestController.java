package me.junioressono.app.shell.controllers;

import me.junioressono.app.shell.AppShell;
import me.junioressono.app.shell.util.CustomInputReader;
import me.junioressono.core.use_cases.calculate_interest.CalculateAccountInterestInputDTO;
import me.junioressono.core.use_cases.calculate_interest.CalculateAccountInterestOutputDTO;

public class CalculateInterestController implements Controller {
    private final AppShell appShell;
    private final CustomInputReader customInputReader;

    public CalculateInterestController(AppShell appShell, CustomInputReader customInputReader) {
        this.appShell = appShell;
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
        CalculateAccountInterestOutputDTO calculateAccountInterestOutputDTO = appShell
                .calculateAccountInterestUseCaseHandler.handle(accountInterestInputDTO);

        System.out.printf("""
                    \n
                    Interest for this month is %s euros.
                    \n
                    """, calculateAccountInterestOutputDTO.interestAmount());
    }
}
