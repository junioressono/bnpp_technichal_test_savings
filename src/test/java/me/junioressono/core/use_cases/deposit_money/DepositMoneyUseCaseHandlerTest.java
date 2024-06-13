package me.junioressono.core.use_cases.deposit_money;

import me.junioressono.core.domain.exceptions.InvalidDepositAmountException;
import me.junioressono.core.domain.models.Account;
import me.junioressono.core.domain.models.SavingAccount;
import me.junioressono.infra.repository.in_memory.AccountRepositoryInMemoryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class DepositMoneyUseCaseHandlerTest {

    AccountRepositoryInMemoryHandler accountRepository;
    DepositMoneyUseCaseHandler depositMoneyUseCaseHandler;

    @BeforeEach
    public void setUp() {
        accountRepository = AccountRepositoryInMemoryHandler.getInstance();
        depositMoneyUseCaseHandler = DepositMoneyUseCaseHandler.getInstance(accountRepository);
    }


    @Test
    public void should_successfully_deposit_positive_amount() throws Exception {
        //Arrange
        BigDecimal initialAmount = new BigDecimal("100.00");
        BigDecimal depositAmount = new BigDecimal("300.00");
        Account account = accountRepository.create(
                new SavingAccount("Junior-Steve", initialAmount)
        );
        DepositMoneyInputDTO depositMoneyInputDTO = new DepositMoneyInputDTO(account.getId(), depositAmount);

        //Act
        DepositMoneyOutputDTO depositMoneyOutputDTO = depositMoneyUseCaseHandler.handle(depositMoneyInputDTO);

        //Assert
        assertThat(account.getBalance()).isEqualTo(initialAmount.add(depositAmount));
        assertThat(depositMoneyOutputDTO.amount()).isEqualTo(depositAmount);
    }

    @Test
    public void should_throw_exception_for_zero_deposit_amount() {
        //Arrange
        BigDecimal initialAmount = BigDecimal.valueOf(100.00);
        BigDecimal depositAmount = BigDecimal.ZERO;
        Account account = accountRepository.create(
                new SavingAccount("Junior-Steve", initialAmount)
        );
        DepositMoneyInputDTO depositMoneyInputDTO = new DepositMoneyInputDTO(account.getId(), depositAmount);

        //Act & Assert
        assertThatThrownBy(() -> depositMoneyUseCaseHandler.handle(depositMoneyInputDTO))
                .isInstanceOf(InvalidDepositAmountException.class);
    }

    @Test
    public void should_throw_exception_for_negative_deposit_amount() {
        //Arrange
        BigDecimal initialAmount = BigDecimal.valueOf(100.00);
        BigDecimal depositAmount = BigDecimal.valueOf(-300000.345);
        Account account = accountRepository.create(
                new SavingAccount("Junior-Steve", initialAmount)
        );
        DepositMoneyInputDTO depositMoneyInputDTO = new DepositMoneyInputDTO(account.getId(), depositAmount);

        //Act & Assert
        assertThatThrownBy(() -> depositMoneyUseCaseHandler.handle(depositMoneyInputDTO))
                .isInstanceOf(InvalidDepositAmountException.class);
    }
}