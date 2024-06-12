package me.junioressono.core.use_cases.calculate_interest;

import me.junioressono.core.domain.exceptions.InvalidAccountWithInterestException;
import me.junioressono.core.domain.models.AccountWithInterest;
import me.junioressono.core.ports.secondary.AccountRepository;

public class CalculateAccountInterestUseCaseHandler implements CalculateAccountUseCase {
    private static CalculateAccountInterestUseCaseHandler INSTANCE;
    private final AccountRepository accountRepository;

    private CalculateAccountInterestUseCaseHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public CalculateAccountInterestOutputDTO handle(
            CalculateAccountInterestInputDTO calculateAccountInterestInputDTO) throws InvalidAccountWithInterestException {
        AccountWithInterest account;
        try {
            account = (AccountWithInterest) accountRepository.getAccount(calculateAccountInterestInputDTO.accountId());
        } catch (ClassCastException e) {
            throw new InvalidAccountWithInterestException();
        }


        return CalculateAccountInterestOutputDTO.builder()
                .interestAmount(account.calculateCurrentMonthInterest())
                .build();
    }



    public static CalculateAccountInterestUseCaseHandler getInstance(AccountRepository accountRepository) {
        if (INSTANCE == null) {
            INSTANCE = new CalculateAccountInterestUseCaseHandler(accountRepository);
        }
        return INSTANCE;
    }
}
