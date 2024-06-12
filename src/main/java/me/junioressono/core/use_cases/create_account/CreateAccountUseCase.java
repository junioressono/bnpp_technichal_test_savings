package me.junioressono.core.use_cases.create_account;

import me.junioressono.core.domain.exceptions.InvalidAccountTypeException;
import me.junioressono.core.use_cases.UseCase;

public interface CreateAccountUseCase extends UseCase<CreateAccountInputDTO, CreateAccountOutputDTO> {
    CreateAccountOutputDTO handle(CreateAccountInputDTO createAccountInputDTO) throws Exception;
}
