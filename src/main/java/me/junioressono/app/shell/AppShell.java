package me.junioressono.app.shell;

import me.junioressono.app.shell.controllers.*;
import me.junioressono.app.shell.util.CustomInputReader;
import me.junioressono.core.use_cases.calculate_interest.CalculateAccountInterestUseCaseHandler;
import me.junioressono.core.use_cases.create_account.CreateAccountUseCase;
import me.junioressono.core.use_cases.deposit_money.DepositMoneyUseCaseHandler;
import me.junioressono.core.use_cases.display_balance.DisplayAccountBalanceUseCaseHandler;
import me.junioressono.core.use_cases.withdrawal_money.WithdrawalMoneyUseCaseHandler;

import java.util.HashMap;
import java.util.Map;

public class AppShell {
    public CreateAccountUseCase createAccountUseCase;
    public DepositMoneyUseCaseHandler depositMoneyUseCaseHandler;
    public WithdrawalMoneyUseCaseHandler withdrawalMoneyUseCaseHandler;
    public DisplayAccountBalanceUseCaseHandler displayAccountBalanceUseCaseHandler;
    public CalculateAccountInterestUseCaseHandler calculateAccountInterestUseCaseHandler;

    public boolean isRunning = true;

    public AppShell(
            CreateAccountUseCase createAccountUseCase,
            DepositMoneyUseCaseHandler depositMoneyUseCaseHandler,
            WithdrawalMoneyUseCaseHandler withdrawalMoneyUseCaseHandler,
            DisplayAccountBalanceUseCaseHandler displayAccountBalanceUseCaseHandler,
            CalculateAccountInterestUseCaseHandler calculateAccountInterestUseCaseHandler
    ) {
        this.createAccountUseCase = createAccountUseCase;
        this.depositMoneyUseCaseHandler = depositMoneyUseCaseHandler;
        this.withdrawalMoneyUseCaseHandler = withdrawalMoneyUseCaseHandler;
        this.displayAccountBalanceUseCaseHandler = displayAccountBalanceUseCaseHandler;
        this.calculateAccountInterestUseCaseHandler = calculateAccountInterestUseCaseHandler;
    }

    public void run() {
        var viewHandler = new CustomInputReader();
        var menuOperationsController = initializeMenuOperationsController(viewHandler);

        String menu = """
                ==============================================
                MENU - Account operations :
                ----------------------------------------------
                1. Create an account
                2. Deposit money
                3. Withdraw money
                4. Display balance
                5. Calculate interest for a savings account
                6. Quit
                ==============================================
                
                Enter your operation number [1-6] :
                """;
        while (isRunning) {
            int operationNumber = viewHandler.readNumber(menu);
            if (menuOperationsController.containsKey(operationNumber)) {
                try {
                    menuOperationsController.get(operationNumber).handle();
                } catch (Exception e) {
                    System.out.printf("""
                                \n
                                AN ERROR OCCURRED WHILE EXECUTING OPERATION NUMBER %s!
                                Message: %s
                                Try again ...
                                \n
                                """, operationNumber, e.getMessage());
                }
            }
            else
                System.out.printf("""
                                \n
                                ERROR!
                                "%s" you have entered is not a valid operation number
                                Try again ...
                                \n
                                """, operationNumber);
        }
    }

    private Map<Integer, Controller> initializeMenuOperationsController(CustomInputReader customInputReader) {
        Map<Integer, Controller> menuOperationsController = new HashMap<>();
        menuOperationsController.put(1, new CreateAccountController(this, customInputReader));
        menuOperationsController.put(2, new DepositMoneyController(this, customInputReader));
        menuOperationsController.put(3, new WithdrawalMoneyController(this, customInputReader));
        menuOperationsController.put(4, new DisplayBalanceController(this, customInputReader));
        menuOperationsController.put(5, new CalculateInterestController(this, customInputReader));
        menuOperationsController.put(6, new LeaveApplicationController(this));
        return menuOperationsController;
    }
}
