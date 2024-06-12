package me.junioressono.core.use_cases.withdrawal_money;

import me.junioressono.core.use_cases.UseCase;

public interface WithdrawalMoneyUseCase extends UseCase<WithdrawalMoneyInputDTO, WithdrawalMoneyOutputDTO> {
    WithdrawalMoneyOutputDTO handle(WithdrawalMoneyInputDTO withdrawalMoneyInputDTO) throws Exception;
}
