package me.junioressono;

import me.junioressono.app.console.AppConsole;
import me.junioressono.core.ports.secondary.AccountRepository;
import me.junioressono.core.use_cases.calculate_interest.CalculateAccountInterestUseCaseHandler;
import me.junioressono.core.use_cases.create_account.CreateAccountUseCaseHandler;
import me.junioressono.core.use_cases.deposit_money.DepositMoneyUseCaseHandler;
import me.junioressono.core.use_cases.display_balance.DisplayAccountBalanceUseCaseHandler;
import me.junioressono.core.use_cases.withdrawal_money.WithdrawalMoneyUseCaseHandler;
import me.junioressono.infra.repository.in_memory.AccountRepositoryInMemoryHandler;


public class Main {
    public static void main(String[] args) {
        //Repository instance
        AccountRepository accountRepository = AccountRepositoryInMemoryHandler.getInstance();

        //Use Cases instances
        var createAccountUseCaseHandler = CreateAccountUseCaseHandler.getInstance(accountRepository);
        var depositMoneyUseCaseHandler = DepositMoneyUseCaseHandler.getInstance(accountRepository);
        var withdrawalMoneyUseCaseHandler = WithdrawalMoneyUseCaseHandler.getInstance(accountRepository);
        var displayAccountBalanceUseCaseHandler = DisplayAccountBalanceUseCaseHandler.getInstance(accountRepository);
        var calculateAccountInterestUseCaseHandler = CalculateAccountInterestUseCaseHandler.getInstance(accountRepository);

        //Creation and running of the console application
        AppConsole app = new AppConsole(
                createAccountUseCaseHandler,
                depositMoneyUseCaseHandler,
                withdrawalMoneyUseCaseHandler,
                displayAccountBalanceUseCaseHandler,
                calculateAccountInterestUseCaseHandler
        );

    }
}