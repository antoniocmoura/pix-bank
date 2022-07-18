package com.antoniocmoura.pixbank.application.transaction.create.transfer;

import com.antoniocmoura.pixbank.application.UseCase;
import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateTransferTransactionUseCase
        extends UseCase<CreateTransferTransactionCommand, Either<Notification, CreateTransferTransactionOutput>> {
}
