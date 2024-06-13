package me.junioressono.core.use_cases.create_account;

import me.junioressono.core.domain.exceptions.InvalidInitialBalanceException;
import me.junioressono.core.domain.models.Account;
import me.junioressono.core.domain.models.AccountType;
import me.junioressono.core.domain.models.CheckingAccount;
import me.junioressono.core.domain.models.SavingAccount;
import me.junioressono.core.ports.secondary.AccountRepository;

public class CreateAccountUseCaseHandler implements CreateAccountUseCase {
    private static CreateAccountUseCaseHandler INSTANCE;
    private final AccountRepository accountRepository;

    private CreateAccountUseCaseHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public CreateAccountOutputDTO handle(CreateAccountInputDTO createAccountInputDTO) {

        if (createAccountInputDTO.initialBalance().signum() <= 0)
            throw new InvalidInitialBalanceException();

        Account accountCreated = accountRepository.create(
                getAccountToCreate(createAccountInputDTO)
        );

        return CreateAccountOutputDTO.builder()
                .id(accountCreated.getId())
                .name(accountCreated.getCustomerName())
                .balance(accountCreated.getBalance())
                .accountType(createAccountInputDTO.accountType())
                .build();
    }

    private Account getAccountToCreate(CreateAccountInputDTO createAccountInputDTO) {
        return switch(createAccountInputDTO.accountType()) {
            case AccountType.CHECKING_ACCOUNT -> new CheckingAccount(
                    createAccountInputDTO.name(), createAccountInputDTO.initialBalance()
            );
            case AccountType.SAVING_ACCOUNT -> new SavingAccount(
                    createAccountInputDTO.name(), createAccountInputDTO.initialBalance()
            );
        };
    }



    public static CreateAccountUseCaseHandler getInstance(AccountRepository accountRepository) {
        if (INSTANCE == null) {
            INSTANCE = new CreateAccountUseCaseHandler(accountRepository);
        }
        return INSTANCE;
    }
}
