package com.antoniocmoura.pixbank.application.transaction.create.deposit;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountGateway;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyGateway;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyID;
import com.antoniocmoura.pixbank.domain.transaction.Transaction;
import com.antoniocmoura.pixbank.domain.transaction.TransactionGateway;
import com.antoniocmoura.pixbank.domain.transaction.TransactionOperation;
import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

public class DefaultCreateDepositTransactionUseCase extends CreateDepositTransactionUseCase {

    private final TransactionGateway transactionGateway;
    private final BankAccountGateway bankAccountGateway;
    private final PixKeyGateway pixKeyGateway;

    public DefaultCreateDepositTransactionUseCase(
            final TransactionGateway transactionGateway,
            final BankAccountGateway bankAccountGateway,
            final PixKeyGateway pixKeyGateway) {
        this.transactionGateway = transactionGateway;
        this.bankAccountGateway = bankAccountGateway;
        this.pixKeyGateway = pixKeyGateway;
    }

    @Override
    public Either<Notification, CreateDepositTransactionOutput> execute(final CreateDepositTransactionCommand aCommand) {
        final var aPixKeyID = PixKeyID.from(aCommand.pixKeyId());
        final var notification = Notification.create();
        final var aPixKey = pixKeyGateway.findById(aPixKeyID).get();
        final var aCreditTransaction = Transaction.newTransaction(
                null,
                aPixKey.getBankAccountID(),
                aPixKeyID,
                aCommand.description(),
                TransactionOperation.CREDIT,
                aCommand.amount()
        );
        aCreditTransaction.validate(null, bankAccountGateway, pixKeyGateway, notification);
        if (!notification.hasError()) {
            final var aBankAccount = bankAccountGateway.findById(aPixKey.getBankAccountID()).get();
            aBankAccount.updateBalance(aCommand.amount());
            bankAccountGateway.update(aBankAccount);
        }
        return notification.hasError() ? API.Left(notification) : create(aCreditTransaction);
    }

    public Either<Notification, CreateDepositTransactionOutput> create(
            final Transaction aCreditTransaction
    ) {
        return API.Try(() -> this.transactionGateway.createDeposit(aCreditTransaction))
                .toEither()
                .bimap(Notification::create, CreateDepositTransactionOutput::from);
    }
}
