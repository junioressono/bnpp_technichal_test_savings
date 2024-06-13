package me.junioressono.core.use_cases;

public interface UseCase<UseCaseCommand, UseCaseResult> {
    UseCaseResult handle(UseCaseCommand command);
}