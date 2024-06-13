package me.junioressono.core.use_cases.display_balance;

import me.junioressono.core.domain.exceptions.AccountNotFoundException;
import me.junioressono.core.domain.models.Account;
import me.junioressono.core.domain.models.CheckingAccount;
import me.junioressono.core.domain.models.SavingAccount;
import me.junioressono.infra.repository.in_memory.AccountRepositoryInMemoryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class DisplayAccountBalanceUseCaseHandlerTest {

    AccountRepositoryInMemoryHandler accountRepository;
    DisplayAccountBalanceUseCaseHandler displayAccountBalanceUseCaseHandler;

    @BeforeEach
    public void setUp() {
        accountRepository = AccountRepositoryInMemoryHandler.getInstance();
        displayAccountBalanceUseCaseHandler = DisplayAccountBalanceUseCaseHandler.getInstance(accountRepository);
    }

    @Test
    public void should_successfully_retrieve_checking_account_balance_for_valid_account_id() {
        //Arrange
        String name = "Junior-Steve";
        BigDecimal initialBalance = BigDecimal.valueOf(10032.221);

        Account checkingAccount = accountRepository.create(
                new CheckingAccount(name, initialBalance)
        );

        DisplayAccountBalanceInputDTO displayCheckingAccountBalanceInputDTO =
                new DisplayAccountBalanceInputDTO(checkingAccount.getId());

        //Act
        DisplayAccountBalanceOutputDTO displayCheckingAccountBalanceOutputDTO =
                displayAccountBalanceUseCaseHandler.handle(displayCheckingAccountBalanceInputDTO);

        //Assert
        assertThat(displayCheckingAccountBalanceOutputDTO.balance()).isEqualByComparingTo(initialBalance);
    }

    @Test
    public void should_successfully_retrieve_saving_account_balance_for_valid_account_id() {
        //Arrange
        String name = "Junior-Steve";
        BigDecimal initialBalance = BigDecimal.valueOf(10032.221);

        Account savingAccount = accountRepository.create(
                new SavingAccount(name, initialBalance)
        );

        DisplayAccountBalanceInputDTO displaySavingAccountBalanceInputDTO =
                new DisplayAccountBalanceInputDTO(savingAccount.getId());

        //Act
        DisplayAccountBalanceOutputDTO displaySavingAccountBalanceOutputDTO =
                displayAccountBalanceUseCaseHandler.handle(displaySavingAccountBalanceInputDTO);

        //Assert
        assertThat(displaySavingAccountBalanceOutputDTO.balance()).isEqualByComparingTo(initialBalance);
    }

    @Test
    public void should_throw_exception_for_non_existent_account_id() {
        //Arrange
        DisplayAccountBalanceInputDTO displaySavingAccountBalanceInputDTO =
                new DisplayAccountBalanceInputDTO(10000);

        //Act & Assert
        assertThatThrownBy(() -> displayAccountBalanceUseCaseHandler.handle(displaySavingAccountBalanceInputDTO))
            .isInstanceOf(AccountNotFoundException.class);
    }
}