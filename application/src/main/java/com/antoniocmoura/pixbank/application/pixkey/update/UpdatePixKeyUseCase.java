package com.antoniocmoura.pixbank.application.pixkey.update;

import com.antoniocmoura.pixbank.application.UseCase;
import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdatePixKeyUseCase
        extends UseCase<UpdatePixKeyCommand, Either<Notification, UpdatePixKeyOutput>> {
}
