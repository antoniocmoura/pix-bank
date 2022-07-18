package com.antoniocmoura.pixbank.application.bankaccount.create;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccount;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountGateway;
import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

public class DefaultCreateBankAccountUseCase extends CreateBankAccountUseCase {

    private final BankAccountGateway bankAccountGateway;

    public DefaultCreateBankAccountUseCase(BankAccountGateway bankAccountGateway) {
        this.bankAccountGateway = Objects.requireNonNull(bankAccountGateway);
    }

    @Override
    public Either<Notification, CreateBankAccountOutput> execute(final CreateBankAccountCommand aCommand) {
        final var aFirstName = aCommand.firstName();
        final var aLastName = aCommand.lastName();

        final var aBankAccount = BankAccount.newBankAccount(aFirstName, aLastName);

        final var notification = Notification.create();
        aBankAccount.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(aBankAccount);
    }

    private Either<Notification, CreateBankAccountOutput> create(final BankAccount aBankAccount) {
        /* modo mais verboso */
        /*try {
            return API.Right(CreateBankAccountOutput.from(this.bankAccountGateway.create(aBankAccount)));
        } catch (Throwable t) {
            return API.Left(Notification.create(t));
        }*/

        return API.Try(() -> this.bankAccountGateway.create(aBankAccount))
                .toEither()
                .bimap(Notification::create, CreateBankAccountOutput::from);
    }
}
