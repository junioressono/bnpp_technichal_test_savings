package me.junioressono.core.use_cases.deposit_money;

import me.junioressono.core.domain.exceptions.InvalidDepositAmountException;
import me.junioressono.core.domain.models.Account;
import me.junioressono.core.ports.secondary.AccountRepository;

public class DepositMoneyUseCaseHandler implements DepositMoneyUseCase {
    private static DepositMoneyUseCaseHandler INSTANCE;
    private final AccountRepository accountRepository;

    private DepositMoneyUseCaseHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public DepositMoneyOutputDTO handle(DepositMoneyInputDTO depositMoneyInputDTO) throws Exception {

        if (depositMoneyInputDTO.amount().signum() <= 0)
            throw new InvalidDepositAmountException();

        Account account = accountRepository.getAccount(depositMoneyInputDTO.accountId());
        account.deposit(depositMoneyInputDTO.amount());

        return DepositMoneyOutputDTO.builder()
                .amount(depositMoneyInputDTO.amount())
                .build();
    }



    public static DepositMoneyUseCaseHandler getInstance(AccountRepository accountRepository) {
        if (INSTANCE == null) {
            INSTANCE = new DepositMoneyUseCaseHandler(accountRepository);
        }
        return INSTANCE;
    }
}
