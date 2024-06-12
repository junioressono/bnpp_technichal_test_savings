package me.junioressono.core.use_cases.calculate_interest;

import me.junioressono.core.use_cases.UseCase;

public interface CalculateAccountUseCase extends UseCase<CalculateAccountInterestInputDTO, CalculateAccountInterestOutputDTO> {
    CalculateAccountInterestOutputDTO handle(CalculateAccountInterestInputDTO calculateAccountInterestInputDTO) throws Exception;
}
