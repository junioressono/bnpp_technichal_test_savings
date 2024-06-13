package me.junioressono;

import me.junioressono.app.shell.AppShellController;
import me.junioressono.core.ports.secondary.AccountRepository;
import me.junioressono.core.use_cases.calculate_interest.CalculateAccountInterestUseCaseHandler;
import me.junioressono.core.use_cases.create_account.CreateAccountUseCase;
import me.junioressono.core.use_cases.create_account.CreateAccountUseCaseHandler;
import me.junioressono.core.use_cases.deposit_money.DepositMoneyUseCaseHandler;
import me.junioressono.core.use_cases.display_balance.DisplayAccountBalanceUseCaseHandler;
import me.junioressono.core.use_cases.withdrawal_money.WithdrawalMoneyUseCaseHandler;
import me.junioressono.infra.repository.in_memory.AccountRepositoryInMemoryHandler;


public class Main {
    public static void main(String[] args) {

        AccountRepository accountRepository = AccountRepositoryInMemoryHandler.getInstance();

        CreateAccountUseCase createAccountUseCase =
                CreateAccountUseCaseHandler.getInstance(accountRepository);

        var depositMoneyUseCaseHandler = DepositMoneyUseCaseHandler.getInstance(accountRepository);

        var withdrawalMoneyUseCaseHandler = WithdrawalMoneyUseCaseHandler.getInstance(accountRepository);

        var displayAccountBalanceUseCaseHandler = DisplayAccountBalanceUseCaseHandler.getInstance(accountRepository);

        var calculateAccountInterestUseCaseHandler = CalculateAccountInterestUseCaseHandler.getInstance(accountRepository);

        AppShellController app = new AppShellController(
                createAccountUseCase,
                depositMoneyUseCaseHandler,
                withdrawalMoneyUseCaseHandler,
                displayAccountBalanceUseCaseHandler,
                calculateAccountInterestUseCaseHandler
        );

        app.run();

    }
}