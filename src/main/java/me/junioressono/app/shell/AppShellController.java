package me.junioressono.app.shell;

import me.junioressono.core.use_cases.calculate_interest.CalculateAccountInterestUseCaseHandler;
import me.junioressono.core.use_cases.create_account.CreateAccountUseCase;
import me.junioressono.core.use_cases.deposit_money.DepositMoneyUseCaseHandler;
import me.junioressono.core.use_cases.display_balance.DisplayAccountBalanceUseCaseHandler;
import me.junioressono.core.use_cases.withdrawal_money.WithdrawalMoneyUseCaseHandler;

import java.util.HashMap;
import java.util.Map;

public class AppShellController {
    CreateAccountUseCase createAccountUseCase;
    DepositMoneyUseCaseHandler depositMoneyUseCaseHandler;
    WithdrawalMoneyUseCaseHandler withdrawalMoneyUseCaseHandler;
    DisplayAccountBalanceUseCaseHandler displayAccountBalanceUseCaseHandler;
    CalculateAccountInterestUseCaseHandler calculateAccountInterestUseCaseHandler;

    public AppShellController(
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
        var viewHandler = new ViewHandler();
        Map<String, OperationsHandler> menuOperations = new HashMap<>();

        menuOperations.put("1", OperationsHandler.CREATE_ACCOUNT);
        menuOperations.put("2", OperationsHandler.DEPOSIT_MONEY);
        menuOperations.put("3", OperationsHandler.WITHDRAW_MONEY);
        menuOperations.put("4", OperationsHandler.DISPLAY_BALANCE);
        menuOperations.put("5", OperationsHandler.CALCULATE_INTEREST);
        menuOperations.put("6", OperationsHandler.QUIT_APPLICATION);

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
        while (viewHandler.isRunning) {
            String operationNumber = viewHandler.readLine(menu);
            if (menuOperations.containsKey(operationNumber)) {
                try {
                    menuOperations
                            .get(operationNumber)
                            .handle(viewHandler, this);
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
}
