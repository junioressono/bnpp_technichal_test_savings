package me.junioressono.core.use_cases.withdrawal_money;

import me.junioressono.core.domain.exceptions.InvalidDepositAmountException;
import me.junioressono.core.domain.models.Account;
import me.junioressono.core.ports.secondary.AccountRepository;

public class WithdrawalMoneyUseCaseHandler implements WithdrawalMoneyUseCase {
    private static WithdrawalMoneyUseCaseHandler INSTANCE;
    private final AccountRepository accountRepository;

    private WithdrawalMoneyUseCaseHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public WithdrawalMoneyOutputDTO handle(WithdrawalMoneyInputDTO withdrawalMoneyInputDTO) throws Exception {

        if (withdrawalMoneyInputDTO.amount().signum() <= 0)
            throw new InvalidDepositAmountException();

        Account account = accountRepository.getAccount(withdrawalMoneyInputDTO.accountId());
        account.withdraw(withdrawalMoneyInputDTO.amount());

        return WithdrawalMoneyOutputDTO.builder()
                .amount(withdrawalMoneyInputDTO.amount())
                .build();
    }



    public static WithdrawalMoneyUseCaseHandler getInstance(AccountRepository accountRepository) {
        if (INSTANCE == null) {
            INSTANCE = new WithdrawalMoneyUseCaseHandler(accountRepository);
        }
        return INSTANCE;
    }
}
