package com.antoniocmoura.pixbank.application.bankaccount.update;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccount;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountGateway;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;
import com.antoniocmoura.pixbank.domain.exceptions.DomainException;
import com.antoniocmoura.pixbank.domain.validation.Error;
import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateBankAccountUseCase extends UpdateBankAccountUseCase {

    private final BankAccountGateway bankAccountGateway;

    public DefaultUpdateBankAccountUseCase(final BankAccountGateway bankAccountGateway) {
        this.bankAccountGateway = Objects.requireNonNull(bankAccountGateway);
    }

    @Override
    public Either<Notification, UpdateBankAccountOutput> execute(final UpdateBankAccountCommand aCommand) {
        final var anId = BankAccountID.from(aCommand.id());
        final var aFirstName = aCommand.firstName();
        final var aLastName = aCommand.lastName();

        final var aBankAccount = this.bankAccountGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();

        aBankAccount
                .update(aFirstName, aLastName)
                .validate(notification);

        return notification.hasError() ? API.Left(notification) : update(aBankAccount);
    }

    private Either<Notification, UpdateBankAccountOutput> update(final BankAccount aBankAccount) {
        return API.Try(() -> this.bankAccountGateway.update(aBankAccount))
                .toEither()
                .bimap(Notification::create, UpdateBankAccountOutput::from);
    }

    private Supplier<DomainException> notFound(final BankAccountID anId) {
        return () -> DomainException.with(
                new Error("Bank Account with ID %S was not found".formatted(anId.getValue()))
        );
    }


}
