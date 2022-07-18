package com.antoniocmoura.pixbank.application.pixkey.update;

import com.antoniocmoura.pixbank.domain.exceptions.DomainException;
import com.antoniocmoura.pixbank.domain.pixkey.PixKey;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyGateway;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyID;
import com.antoniocmoura.pixbank.domain.validation.Error;
import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.function.Supplier;

public class DefaultUpdatePixKeyUseCase extends UpdatePixKeyUseCase {

    private final PixKeyGateway pixKeyGateway;

    public DefaultUpdatePixKeyUseCase(final PixKeyGateway pixKeyGateway) {
        this.pixKeyGateway = pixKeyGateway;
    }

    @Override
    public  Either<Notification, UpdatePixKeyOutput> execute(UpdatePixKeyCommand aCommand) {
        final var aPixKeyID = PixKeyID.from(aCommand.pixKey());
        final var aPixKey = this.pixKeyGateway.findById(aPixKeyID)
                .orElseThrow(notFound(aPixKeyID.getValue()));
        final var notification = Notification.create();
        notification.validate(() -> aPixKey.updateActive(aCommand.active()));
        return notification.hasError() ? API.Left(notification) : update(aPixKey);
    }

    private Either<Notification, UpdatePixKeyOutput> update(final PixKey aPixKey) {
        return API.Try(() -> this.pixKeyGateway.update(aPixKey))
                .toEither()
                .bimap(Notification::create, UpdatePixKeyOutput::from);
    }

    private Supplier<DomainException> notFound(final String aKeyValue) {
        return () -> DomainException.with(
                new Error("Pix Key with ID %S was not found".formatted(aKeyValue))
        );
    }
}
