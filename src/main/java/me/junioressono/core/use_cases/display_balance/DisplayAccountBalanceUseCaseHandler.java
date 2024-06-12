package me.junioressono.core.use_cases.display_balance;

import me.junioressono.core.domain.models.Account;
import me.junioressono.core.ports.secondary.AccountRepository;

public class DisplayAccountBalanceUseCaseHandler implements DisplayAccountBalanceUseCase {
    private static DisplayAccountBalanceUseCaseHandler INSTANCE;
    private final AccountRepository accountRepository;

    private DisplayAccountBalanceUseCaseHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public DisplayAccountBalanceOutputDTO handle(DisplayAccountBalanceInputDTO displayAccountBalanceInputDTO) throws Exception {

        Account account = accountRepository.getAccount(displayAccountBalanceInputDTO.accountId());

        return DisplayAccountBalanceOutputDTO.builder()
                .balance(account.getBalance())
                .build();
    }



    public static DisplayAccountBalanceUseCaseHandler getInstance(AccountRepository accountRepository) {
        if (INSTANCE == null) {
            INSTANCE = new DisplayAccountBalanceUseCaseHandler(accountRepository);
        }
        return INSTANCE;
    }
}
