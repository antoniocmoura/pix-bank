package com.antoniocmoura.pixbank.infrastructure.transaction.presenters;

import com.antoniocmoura.pixbank.application.transaction.retrieve.get.TransactionOutput;
import com.antoniocmoura.pixbank.application.transaction.retrieve.list.TransactionListOutput;
import com.antoniocmoura.pixbank.infrastructure.transaction.models.TransactionListResponse;
import com.antoniocmoura.pixbank.infrastructure.transaction.models.TransactionResponse;

import java.util.Objects;

public interface TransactionApiPresenter {

    static TransactionResponse present(final TransactionOutput output) {
        final var senderTransactionId = Objects.isNull(output.senderTransactionID()) ? null : output.senderTransactionID().getValue();
        return new TransactionResponse(
                output.id().getValue(),
                output.bankAccountID().getValue(),
                senderTransactionId,
                output.pixKeyID().getValue(),
                output.description(),
                output.operation(),
                output.amount(),
                output.createdAt()
        );
    }

    static TransactionListResponse present(final TransactionListOutput output) {
        final var senderTransactionId = Objects.isNull(output.senderTransactionID()) ? null : output.senderTransactionID().getValue();
        return new TransactionListResponse(
                output.id().getValue(),
                output.bankAccountID().getValue(),
                senderTransactionId,
                output.pixKeyID().getValue(),
                output.description(),
                output.operation(),
                output.amount(),
                output.createdAt()
        );
    }

}
