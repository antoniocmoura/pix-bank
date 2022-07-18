package com.antoniocmoura.pixbank.application.transaction.retrieve.get;

import com.antoniocmoura.pixbank.domain.exceptions.DomainException;
import com.antoniocmoura.pixbank.domain.transaction.TransactionGateway;
import com.antoniocmoura.pixbank.domain.transaction.TransactionID;
import com.antoniocmoura.pixbank.domain.validation.Error;

import java.util.function.Supplier;

public class DefaultGetTransactionByIdUseCase extends GetTransactionByIdUseCase {

    private final TransactionGateway transactionGateway;

    public DefaultGetTransactionByIdUseCase(final TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    @Override
    public TransactionOutput execute(String anID) {
        final var anTransactionID = TransactionID.from(anID);
        return this.transactionGateway.findById(anTransactionID)
                .map(TransactionOutput::from)
                .orElseThrow(notFound(anID));
    }

    private Supplier<DomainException> notFound(final String anID) {
        return () -> DomainException.with(
                new Error("Transaction with ID %S was not found".formatted(anID))
        );
    }
}
