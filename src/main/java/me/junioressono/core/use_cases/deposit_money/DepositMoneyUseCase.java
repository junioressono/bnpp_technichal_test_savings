package me.junioressono.core.use_cases.deposit_money;

import me.junioressono.core.use_cases.UseCase;
import me.junioressono.core.use_cases.create_account.CreateAccountInputDTO;
import me.junioressono.core.use_cases.create_account.CreateAccountOutputDTO;

public interface DepositMoneyUseCase extends UseCase<DepositMoneyInputDTO, DepositMoneyOutputDTO> {
    DepositMoneyOutputDTO handle(DepositMoneyInputDTO depositMoneyInputDTO) throws Exception;
}
