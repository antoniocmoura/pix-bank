package com.antoniocmoura.pixbank.application.transaction.create.transfer;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountGateway;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyGateway;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyID;
import com.antoniocmoura.pixbank.domain.transaction.Transaction;
import com.antoniocmoura.pixbank.domain.transaction.TransactionGateway;
import com.antoniocmoura.pixbank.domain.transaction.TransactionOperation;
import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

public class DefaultCreateTransferTransactionUseCase extends CreateTransferTransactionUseCase {

    private final TransactionGateway transactionGateway;
    private final BankAccountGateway bankAccountGateway;
    private final PixKeyGateway pixKeyGateway;

    public DefaultCreateTransferTransactionUseCase(
            final TransactionGateway transactionGateway,
            final BankAccountGateway bankAccountGateway,
            final PixKeyGateway pixKeyGateway
    ) {
        this.transactionGateway = transactionGateway;
        this.bankAccountGateway = bankAccountGateway;
        this.pixKeyGateway = pixKeyGateway;
    }

    @Override
    public Either<Notification, CreateTransferTransactionOutput> execute(final CreateTransferTransactionCommand aCommand) {
        final var aPixKeyID = PixKeyID.from(aCommand.pixKey());
        final var aSenderPixKeyID = PixKeyID.from(aCommand.senderPixKey());
        final var notification = Notification.create();
        final var aPixKey = pixKeyGateway.findById(aPixKeyID).get();
        final var aSenderPixKey = pixKeyGateway.findById(aSenderPixKeyID).get();

        final var aDebitTransaction = Transaction.newTransaction(
                null,
                aPixKey.getBankAccountID(),
                aSenderPixKeyID,
                aCommand.description(),
                TransactionOperation.DEBIT,
                aCommand.amount()
        );
        aDebitTransaction.validate(null, bankAccountGateway, pixKeyGateway, notification);
        if (!notification.hasError()) {
            final var aCreditTransaction = Transaction.newTransaction(
                    aDebitTransaction.getId(),
                    aSenderPixKey.getBankAccountID(),
                    aPixKeyID,
                    aCommand.description(),
                    TransactionOperation.CREDIT,
                    aCommand.amount()
            );
            aCreditTransaction.validate(aDebitTransaction, bankAccountGateway, pixKeyGateway, notification);

            if (!notification.hasError()) {

                final var aBankAccount = bankAccountGateway.findById(aPixKey.getBankAccountID()).get();
                aBankAccount.updateBalance(aCommand.amount());
                bankAccountGateway.update(aBankAccount);

                final var aSenderBankAccount = bankAccountGateway.findById(aSenderPixKey.getBankAccountID()).get();
                aSenderBankAccount.updateBalance(aCommand.amount() * -1);
                bankAccountGateway.update(aSenderBankAccount);
            }
            return notification.hasError() ? API.Left(notification) : create(aDebitTransaction, aCreditTransaction);
        } else {
            return API.Left(notification);
        }
    }

    public Either<Notification, CreateTransferTransactionOutput> create(
            final Transaction aDebitTransaction,
            final Transaction aCreditTransaction
    ) {
        return API.Try(() -> this.transactionGateway.createTransfer(aDebitTransaction, aCreditTransaction))
                .toEither()
                .bimap(Notification::create, CreateTransferTransactionOutput::from);
    }
}
