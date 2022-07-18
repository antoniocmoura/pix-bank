package com.antoniocmoura.pixbank.application.bankaccount.create;

import com.antoniocmoura.pixbank.application.UseCase;
import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateBankAccountUseCase
        extends UseCase<CreateBankAccountCommand, Either<Notification, CreateBankAccountOutput>> {
}
