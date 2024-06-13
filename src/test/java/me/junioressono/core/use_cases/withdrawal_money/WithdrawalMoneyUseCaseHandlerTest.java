package me.junioressono.core.use_cases.withdrawal_money;

import me.junioressono.core.domain.exceptions.AccountNotFoundException;
import me.junioressono.core.domain.exceptions.InsufficientBalanceException;
import me.junioressono.core.domain.exceptions.InvalidWithdrawalAmountException;
import me.junioressono.core.domain.exceptions.MonthlyWithdrawalExceedingMaximumException;
import me.junioressono.core.domain.models.Account;
import me.junioressono.core.domain.models.CheckingAccount;
import me.junioressono.core.domain.models.SavingAccount;
import me.junioressono.infra.repository.in_memory.AccountRepositoryInMemoryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WithdrawalMoneyUseCaseHandlerTest {
    AccountRepositoryInMemoryHandler accountRepository;
    WithdrawalMoneyUseCaseHandler withdrawalMoneyUseCaseHandler;

    @BeforeEach
    public void setUp() {
        accountRepository = AccountRepositoryInMemoryHandler.getInstance();
        withdrawalMoneyUseCaseHandler = WithdrawalMoneyUseCaseHandler.getInstance(accountRepository);
    }

    @Test
    public void should_successful_withdrawal_from_checking_account() {
        //Arrange
        String name = "Junior-Steve";
        BigDecimal initialBalance = BigDecimal.valueOf(1000.00);
        BigDecimal withdrawAmount = BigDecimal.valueOf(500.00);

        Account checkingAccount = accountRepository.create(
                new CheckingAccount(name, initialBalance)
        );

        WithdrawalMoneyInputDTO withdrawalMoneyInputDTO =
                new WithdrawalMoneyInputDTO(checkingAccount.getId(), withdrawAmount);

        //Act
        WithdrawalMoneyOutputDTO withdrawalMoneyOutputDTO =
                withdrawalMoneyUseCaseHandler.handle(withdrawalMoneyInputDTO);

        //Assert
        assertThat(withdrawalMoneyOutputDTO.amount()).isEqualByComparingTo(withdrawAmount);
        assertThat(checkingAccount.getBalance()).isEqualByComparingTo(initialBalance.subtract(withdrawAmount));
    }

    @Test
    public void should_successful_withdrawal_from_saving_account() {
        //Arrange
        String name = "Junior-Steve";
        BigDecimal initialBalance = BigDecimal.valueOf(1000.00);
        BigDecimal withdrawAmount = BigDecimal.valueOf(500.00);

        Account savingAccount = accountRepository.create(
                new SavingAccount(name, initialBalance)
        );

        WithdrawalMoneyInputDTO withdrawalMoneyInputDTO =
                new WithdrawalMoneyInputDTO(savingAccount.getId(), withdrawAmount);

        //Act
        WithdrawalMoneyOutputDTO withdrawalMoneyOutputDTO =
                withdrawalMoneyUseCaseHandler.handle(withdrawalMoneyInputDTO);

        //Assert
        assertThat(withdrawalMoneyOutputDTO.amount()).isEqualByComparingTo(withdrawAmount);
        assertThat(savingAccount.getBalance()).isEqualByComparingTo(initialBalance.subtract(withdrawAmount));
    }


    @Test
    public void should_throw_exception_for_zero_withdraw_amount() {
        //Arrange
        BigDecimal initialAmount = BigDecimal.valueOf(100.00);
        BigDecimal withdrawalAmount = BigDecimal.ZERO;
        Account account = accountRepository.create(
                new SavingAccount("Junior-Steve", initialAmount)
        );
        WithdrawalMoneyInputDTO withdrawalMoneyInputDTO = new WithdrawalMoneyInputDTO(account.getId(), withdrawalAmount);

        //Act & Assert
        assertThatThrownBy(() -> withdrawalMoneyUseCaseHandler.handle(withdrawalMoneyInputDTO))
                .isInstanceOf(InvalidWithdrawalAmountException.class);
    }

    @Test
    public void should_throw_exception_for_negative_withdraw_amount() {
        //Arrange
        BigDecimal initialAmount = BigDecimal.valueOf(100.00);
        BigDecimal withdrawalAmount = BigDecimal.valueOf(-123222.343);
        Account account = accountRepository.create(
                new SavingAccount("Junior-Steve", initialAmount)
        );
        WithdrawalMoneyInputDTO withdrawalMoneyInputDTO = new WithdrawalMoneyInputDTO(account.getId(), withdrawalAmount);

        //Act & Assert
        assertThatThrownBy(() -> withdrawalMoneyUseCaseHandler.handle(withdrawalMoneyInputDTO))
                .isInstanceOf(InvalidWithdrawalAmountException.class);
    }

    @Test
    public void should_throw_exception_for_non_existent_account_id() {
        //Arrange
        WithdrawalMoneyInputDTO withdrawalMoneyInputDTO =
                new WithdrawalMoneyInputDTO(1000, BigDecimal.valueOf(2342));

        //Act & Assert
        assertThatThrownBy(() -> withdrawalMoneyUseCaseHandler.handle(withdrawalMoneyInputDTO))
                .isInstanceOf(AccountNotFoundException.class);
    }

    @Test
    public void should_throw_exception_for_insufficient_balance_from_checking_account() {
        //Arrange
        BigDecimal initialAmount = BigDecimal.valueOf(100.00);
        BigDecimal withdrawalAmount = BigDecimal.valueOf(500.00);
        Account account = accountRepository.create(
                new CheckingAccount("Junior-Steve", initialAmount)
        );
        WithdrawalMoneyInputDTO withdrawalMoneyInputDTO = new WithdrawalMoneyInputDTO(account.getId(), withdrawalAmount);

        //Act & Assert
        assertThatThrownBy(() -> withdrawalMoneyUseCaseHandler.handle(withdrawalMoneyInputDTO))
                .isInstanceOf(InsufficientBalanceException.class);
    }

    @Test
    public void should_throw_exception_for_insufficient_balance_from_saving_account() {
        //Arrange
        BigDecimal initialAmount = BigDecimal.valueOf(100.00);
        BigDecimal withdrawalAmount = BigDecimal.valueOf(500.00);
        Account account = accountRepository.create(
                new SavingAccount("Junior-Steve", initialAmount)
        );
        WithdrawalMoneyInputDTO withdrawalMoneyInputDTO = new WithdrawalMoneyInputDTO(account.getId(), withdrawalAmount);

        //Act & Assert
        assertThatThrownBy(() -> withdrawalMoneyUseCaseHandler.handle(withdrawalMoneyInputDTO))
                .isInstanceOf(InsufficientBalanceException.class);
    }

    @Test
    public void should_throw_exception_monthly_withdrawal_exceeding_maximum_amount_from_saving_account() {
        //Arrange
        BigDecimal initialAmount = BigDecimal.valueOf(100000.00);
        BigDecimal withdrawalAmount = SavingAccount.MAX_WITHDRAWAL_PER_MONTH.add(BigDecimal.valueOf(1000));
        Account account = accountRepository.create(
                new SavingAccount("Junior-Steve", initialAmount)
        );
        WithdrawalMoneyInputDTO withdrawalMoneyInputDTO = new WithdrawalMoneyInputDTO(account.getId(), withdrawalAmount);

        //Act & Assert
        assertThatThrownBy(() -> withdrawalMoneyUseCaseHandler.handle(withdrawalMoneyInputDTO))
                .isInstanceOf(MonthlyWithdrawalExceedingMaximumException.class);

    }

}