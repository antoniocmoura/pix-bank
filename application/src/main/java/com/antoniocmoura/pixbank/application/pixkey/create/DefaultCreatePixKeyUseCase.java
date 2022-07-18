package com.antoniocmoura.pixbank.application.pixkey.create;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountGateway;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;
import com.antoniocmoura.pixbank.domain.pixkey.PixKey;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyGateway;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyID;
import com.antoniocmoura.pixbank.domain.validation.Error;
import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

public class DefaultCreatePixKeyUseCase extends CreatePixKeyUseCase {

    private final PixKeyGateway pixKeyGateway;
    private final BankAccountGateway bankAccountGateway;

    public DefaultCreatePixKeyUseCase(
            final PixKeyGateway pixKeyGateway,
            final BankAccountGateway bankAccountGateway
    ) {
        this.pixKeyGateway = pixKeyGateway;
        this.bankAccountGateway = bankAccountGateway;
    }

    @Override
    public Either<Notification, CreatePixKeyOutput> execute(final CreatePixKeyCommand aCommand) {
        final var aPixKeyID = PixKeyID.from(aCommand.id());
        final var aBankAccountID = BankAccountID.from(aCommand.bankAccountID());
        final var aKeyKind = aCommand.keyKind();

        final var aPixKey = PixKey.newPixKey(aPixKeyID, aBankAccountID, aKeyKind);
        final var notification = Notification.create();

        final var aPixKeyExists = pixKeyGateway.findById(aPixKey.getId());
        if (!aPixKeyExists.isEmpty()) {
            notification.append(new Error("'pix_key' already exists"));
        }

        final var aBankAccountExists = bankAccountGateway.findById(aBankAccountID);
        if (aBankAccountExists.isEmpty()) {
            notification.append(new Error("'bank_account_id' not exists"));
        }

        aPixKey.validate(notification);
        return notification.hasError() ? API.Left(notification) : create(aPixKey);
    }

    public Either<Notification, CreatePixKeyOutput> create(final PixKey aPixKey) {
        return API.Try(() -> this.pixKeyGateway.create(aPixKey))
                .toEither()
                .bimap(Notification::create, CreatePixKeyOutput::from);
    }
}
