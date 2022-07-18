package com.antoniocmoura.pixbank.application.transaction.create.deposit;

import com.antoniocmoura.pixbank.application.UseCase;
import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateDepositTransactionUseCase
        extends UseCase<CreateDepositTransactionCommand, Either<Notification, CreateDepositTransactionOutput>> {
}
