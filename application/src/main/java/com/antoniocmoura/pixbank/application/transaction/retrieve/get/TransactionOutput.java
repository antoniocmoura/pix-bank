package com.antoniocmoura.pixbank.application.transaction.retrieve.get;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyID;
import com.antoniocmoura.pixbank.domain.transaction.Transaction;
import com.antoniocmoura.pixbank.domain.transaction.TransactionID;
import com.antoniocmoura.pixbank.domain.transaction.TransactionOperation;

import java.time.Instant;
import java.util.Objects;

public record TransactionOutput(
        TransactionID id,
        BankAccountID bankAccountID,
        TransactionID senderTransactionID,
        PixKeyID pixKeyID,
        String description,
        TransactionOperation operation,
        Double amount,
        Instant createdAt
) {
    public static TransactionOutput from (final Transaction aTransaction ) {
        final var aSenderTransactionID = Objects.isNull(aTransaction.getSenderTransactionID()) ? null : aTransaction.getSenderTransactionID();
        return new TransactionOutput(
                aTransaction.getId(),
                aTransaction.getBankAccountID(),
                aSenderTransactionID,
                aTransaction.getPixKeyID(),
                aTransaction.getDescription(),
                aTransaction.getOperation(),
                aTransaction.getAmount(),
                aTransaction.getCreatedAt()
        );
    }
}
