package com.antoniocmoura.pixbank.application.pixkey.create;

import com.antoniocmoura.pixbank.application.UseCase;
import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreatePixKeyUseCase
    extends UseCase<CreatePixKeyCommand, Either<Notification, CreatePixKeyOutput>> {
}
