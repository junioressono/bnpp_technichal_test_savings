package me.junioressono.app.shell.controllers;

import me.junioressono.app.shell.AppShell;
import me.junioressono.app.shell.util.CustomInputReader;
import me.junioressono.app.shell.exceptions.InvalidAccountTypeException;
import me.junioressono.core.domain.models.AccountType;
import me.junioressono.core.use_cases.create_account.CreateAccountInputDTO;
import me.junioressono.core.use_cases.create_account.CreateAccountOutputDTO;

import java.math.BigDecimal;

public class CreateAccountController implements Controller {
    private final AppShell appShell;
    private final CustomInputReader customInputReader;

    public CreateAccountController(AppShell appShell, CustomInputReader customInputReader) {
        this.appShell = appShell;
        this.customInputReader = customInputReader;
    }

    @Override
    public void handle() {
        System.out.println("""
                    ==============================================
                    OPERATION  -  CREATE ACCOUNT
                    ----------------------------------------------
                    """);
        var name = customInputReader.readString("Enter your name:");
        var initialBalance = customInputReader.readString("Enter the initial interestAmount:");
        int accountType;

        accountType = customInputReader.readNumber("Choose the account type (1 for checking, 2 for savings):");
        if (accountType < 1 || accountType > AccountType.values().length)
            throw new InvalidAccountTypeException();

        CreateAccountInputDTO createAccountInputDTO = new CreateAccountInputDTO(
                name,
                new BigDecimal(initialBalance),
                AccountType.values()[accountType-1]
        );

        CreateAccountOutputDTO account = appShell.createAccountUseCase.handle(createAccountInputDTO);

        System.out.printf("""
                    \n
                    %s created successfully. Account identifier: %d
                    \n
                    """, AccountType.values()[accountType-1], account.id());
    }
}
