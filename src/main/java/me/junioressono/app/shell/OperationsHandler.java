package me.junioressono.app.shell;

import me.junioressono.core.domain.exceptions.InvalidAccountTypeException;
import me.junioressono.core.domain.models.AccountType;
import me.junioressono.core.use_cases.calculate_interest.CalculateAccountInterestInputDTO;
import me.junioressono.core.use_cases.calculate_interest.CalculateAccountInterestOutputDTO;
import me.junioressono.core.use_cases.create_account.CreateAccountInputDTO;
import me.junioressono.core.use_cases.create_account.CreateAccountOutputDTO;
import me.junioressono.core.use_cases.deposit_money.DepositMoneyInputDTO;
import me.junioressono.core.use_cases.deposit_money.DepositMoneyOutputDTO;
import me.junioressono.core.use_cases.display_balance.DisplayAccountBalanceInputDTO;
import me.junioressono.core.use_cases.display_balance.DisplayAccountBalanceOutputDTO;
import me.junioressono.core.use_cases.withdrawal_money.WithdrawalMoneyInputDTO;
import me.junioressono.core.use_cases.withdrawal_money.WithdrawalMoneyOutputDTO;

import java.math.BigDecimal;

public enum OperationsHandler {
    CREATE_ACCOUNT {
        @Override
        public void handle(ViewHandler viewHandler, AppShellController shellController) throws Exception {
            System.out.println("""
                    ==============================================
                    OPERATION  -  CREATE ACCOUNT
                    ----------------------------------------------
                    """);
            var name = viewHandler.readLine("Enter your name:");
            var initialBalance = viewHandler.readLine("Enter the initial interestAmount:");
            int accountType;

            try {
                accountType = Integer.parseInt(
                        viewHandler.readLine("Choose the account type (1 for checking, 2 for savings):")
                );
            } catch (NumberFormatException e) {
                throw new InvalidAccountTypeException();
            }
            if (accountType < 1 || accountType > AccountType.values().length)
                throw new InvalidAccountTypeException();

            CreateAccountInputDTO createAccountInputDTO = new CreateAccountInputDTO(
                    name,
                    new BigDecimal(initialBalance),
                    AccountType.values()[accountType-1]
            );

            CreateAccountOutputDTO account = shellController.createAccountUseCase.handle(createAccountInputDTO);

            System.out.printf("""
                    \n
                    %s created successfully. Account identifier: %d
                    \n
                    """, AccountType.values()[accountType-1], account.id());
        }
    },
    DEPOSIT_MONEY {
        @Override
        public void handle(ViewHandler viewHandler, AppShellController shellController) throws Exception {
            System.out.println("""
                    ==============================================
                    OPERATION  -  DEPOSIT MONEY
                    ----------------------------------------------
                    """);
            var accountId = viewHandler.readNumber("Enter your account identifier:");
            var amount = viewHandler.readLine("Enter the interestAmount to deposit:");

            DepositMoneyInputDTO depositMoneyInputDTO = new DepositMoneyInputDTO(accountId, new BigDecimal(amount));
            DepositMoneyOutputDTO depositMoneyOutputDTO = shellController
                    .depositMoneyUseCaseHandler
                    .handle(depositMoneyInputDTO);

            System.out.printf("""
                    \n
                    %s euros have been deposited into your account.
                    \n
                    """, depositMoneyOutputDTO.amount());
        }
    },
    WITHDRAW_MONEY {
        @Override
        public void handle(ViewHandler viewHandler, AppShellController shellController) throws Exception {
            System.out.println("""
                    ==============================================
                    OPERATION  -  WITHDRAWAL MONEY
                    ----------------------------------------------
                    """);
            var withdrawalAccountId = viewHandler.readNumber("Enter your account identifier:");
            var withdrawalAmount = viewHandler.readLine("Enter the interestAmount to withdrawal:");

            WithdrawalMoneyInputDTO withdrawalMoneyInputDTO = new WithdrawalMoneyInputDTO(
                    withdrawalAccountId, new BigDecimal(withdrawalAmount)
            );

            WithdrawalMoneyOutputDTO withdrawalMoneyOutputDTO = shellController
                    .withdrawalMoneyUseCaseHandler
                    .handle(withdrawalMoneyInputDTO);

            System.out.printf("""
                    \n
                    %s euros have been withdrawn from your account.
                    \n
                    """, withdrawalMoneyOutputDTO.amount());
        }
    },
    DISPLAY_BALANCE {
        @Override
        public void handle(ViewHandler viewHandler, AppShellController shellController) throws Exception {
            System.out.println("""
                    ==============================================
                    OPERATION  -  DISPLAY ACCOUNT BALANCE
                    ----------------------------------------------
                    """);

            var AccountId = viewHandler.readNumber("Enter your account identifier:");
            DisplayAccountBalanceInputDTO displayAccountBalanceInputDTO = new DisplayAccountBalanceInputDTO(AccountId);
            DisplayAccountBalanceOutputDTO displayAccountBalanceOutputDTO =
                    shellController.displayAccountBalanceUseCaseHandler.handle(displayAccountBalanceInputDTO);

            System.out.printf("""
                    \n
                    Your current balance is %s euros.
                    \n
                    """, displayAccountBalanceOutputDTO.balance());
        }
    },
    CALCULATE_INTEREST {
        @Override
        public void handle(ViewHandler viewHandler, AppShellController shellController) throws Exception {
            System.out.println("""
                    ==============================================
                    OPERATION  -  CALCULATE INTEREST
                    ----------------------------------------------
                    """);

            var AccountId = viewHandler.readNumber("Enter your account identifier:");
            CalculateAccountInterestInputDTO accountInterestInputDTO = new CalculateAccountInterestInputDTO(AccountId);
            CalculateAccountInterestOutputDTO calculateAccountInterestOutputDTO = shellController
                    .calculateAccountInterestUseCaseHandler.handle(accountInterestInputDTO);

            System.out.printf("""
                    \n
                    Interest for this month is %s euros.
                    \n
                    """, calculateAccountInterestOutputDTO.interestAmount());
        }
    },
    QUIT_APPLICATION {
        @Override
        public void handle(ViewHandler viewHandler, AppShellController shellController) {
            System.out.println("""
                    You have decide to leave application.
                    
                    GOOD BYE !!!
                    """);
            System.exit(0);
        }
    };

    public abstract void handle(
            ViewHandler viewHandler, AppShellController shellController
    ) throws Exception;
}
