package com.antoniocmoura.pixbank.application.bankaccount.update;

import com.antoniocmoura.pixbank.application.UseCase;
import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateBankAccountUseCase
        extends UseCase<UpdateBankAccountCommand, Either<Notification, UpdateBankAccountOutput>> {
}
