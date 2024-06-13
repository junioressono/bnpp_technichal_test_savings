package me.junioressono.core.use_cases.calculate_interest;

import me.junioressono.core.domain.exceptions.InvalidAccountWithInterestException;
import me.junioressono.core.domain.models.Account;
import me.junioressono.core.domain.models.CheckingAccount;
import me.junioressono.core.domain.models.SavingAccount;
import me.junioressono.infra.repository.in_memory.AccountRepositoryInMemoryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculateAccountInterestUseCaseHandlerTest {

    AccountRepositoryInMemoryHandler accountRepository;
    CalculateAccountInterestUseCaseHandler handler;

    @BeforeEach
    public void setUp() {
        accountRepository = AccountRepositoryInMemoryHandler.getInstance();
        handler = CalculateAccountInterestUseCaseHandler.getInstance(accountRepository);

    }


    @Test
    public void should_successfully_calculates_interest_for_valid_account() throws InvalidAccountWithInterestException {
        Account account = new SavingAccount("John Doe", new BigDecimal("1000"));
        Account createdAccount = accountRepository.create(account);
        CalculateAccountInterestInputDTO inputDTO = new CalculateAccountInterestInputDTO(createdAccount.getId());
        CalculateAccountInterestOutputDTO outputDTO = handler.handle(inputDTO);
        assertEquals(new BigDecimal("50.00"), outputDTO.interestAmount());
    }

    @Test
    public void should_throws_invalid_account_with_interest_exception_for_non_interest_account() {
        Account account = new CheckingAccount("John Doe", new BigDecimal("3000.00"));
        Account createAccount = accountRepository.create(account);
        CalculateAccountInterestInputDTO inputDTO = new CalculateAccountInterestInputDTO(createAccount.getId());
        assertThrows(InvalidAccountWithInterestException.class, () -> handler.handle(inputDTO));
    }
}