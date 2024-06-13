package me.junioressono.core.use_cases.create_account;

import me.junioressono.core.domain.exceptions.InvalidInitialBalanceException;
import me.junioressono.core.domain.models.AccountType;
import me.junioressono.infra.repository.in_memory.AccountRepositoryInMemoryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CreateAccountUseCaseHandlerTest {

    AccountRepositoryInMemoryHandler accountRepository;
    CreateAccountUseCaseHandler createAccountUseCaseHandler;

    @BeforeEach
    public void setUp() {
        accountRepository = AccountRepositoryInMemoryHandler.getInstance();
        createAccountUseCaseHandler = CreateAccountUseCaseHandler.getInstance(accountRepository);
    }


    @Test
    public void should_create_checking_account_with_valid_initial_balance() {
        //Arrange
        String name = "Junior-Steve ESSONO";
        BigDecimal initialBalance = BigDecimal.valueOf(10032.221);
        AccountType accountType = AccountType.CHECKING_ACCOUNT;

        int accountsSize = accountRepository.getAccounts().size();
        int currentIdCursorValue = AccountRepositoryInMemoryHandler.ID_CURSOR;

        CreateAccountInputDTO inputDTO = new CreateAccountInputDTO(
                name, initialBalance, accountType
        );

        //Act
        CreateAccountOutputDTO outputDTO = createAccountUseCaseHandler.handle(inputDTO);

        //Assert
        assertThat(outputDTO).isNotNull();
        assertThat(outputDTO.name()).isEqualTo(name);
        assertThat(outputDTO.balance()).isEqualTo(initialBalance);
        assertThat(outputDTO.accountType()).isEqualTo(accountType);
        assertThat(outputDTO.id()).isEqualTo(currentIdCursorValue + 1);
        assertThat(accountRepository.getAccounts().size()).isEqualTo(accountsSize + 1);
    }

    @Test
    public void should_throw_exception_for_invalid_account_type() {
        //Arrange
        CreateAccountInputDTO inputDTO = new CreateAccountInputDTO(
                "Junior-Steve ESSONO", BigDecimal.ZERO, AccountType.CHECKING_ACCOUNT
        );

        //Act & Assert
        assertThatThrownBy(() -> createAccountUseCaseHandler.handle(inputDTO))
                .isInstanceOf(InvalidInitialBalanceException.class);
    }

    @Test
    public void should_throw_exception_for_zero_initial_balance() {
        //Arrange
        CreateAccountInputDTO inputDTO = new CreateAccountInputDTO(
                "Junior-Steve ESSONO", BigDecimal.ZERO, AccountType.CHECKING_ACCOUNT
        );

        //Act & Assert
        assertThatThrownBy(() -> createAccountUseCaseHandler.handle(inputDTO))
                .isInstanceOf(InvalidInitialBalanceException.class);
    }

    @Test
    public void should_throw_exception_for_negative_initial_balance() {
        //Arrange
        CreateAccountInputDTO inputDTO = new CreateAccountInputDTO(
                "Junior-Steve ESSONO", BigDecimal.valueOf(-2334.342), AccountType.CHECKING_ACCOUNT
        );

        //Act & Assert
        assertThatThrownBy(() -> createAccountUseCaseHandler.handle(inputDTO))
                .isInstanceOf(InvalidInitialBalanceException.class);
    }
}