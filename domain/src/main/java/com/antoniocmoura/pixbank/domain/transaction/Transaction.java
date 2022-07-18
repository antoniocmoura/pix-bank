package com.antoniocmoura.pixbank.domain.transaction;

import com.antoniocmoura.pixbank.domain.AggregateRoot;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountGateway;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyGateway;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyID;
import com.antoniocmoura.pixbank.domain.validation.ValidationHandler;

import java.time.Instant;

public class Transaction extends AggregateRoot<TransactionID> {

    private TransactionID senderTransactionID;
    private BankAccountID bankAccountID;
    private PixKeyID pixKeyID;
    private String description;
    private TransactionOperation operation;
    private Double amount;
    private Instant createdAt;

    protected Transaction(
            final TransactionID transactionID,
            final TransactionID senderTransactionID,
            final BankAccountID bankAccountID,
            final PixKeyID pixKeyID,
            final String description,
            final TransactionOperation operation,
            final Double amount,
            final Instant createdAt
    ) {
        super(transactionID);
        this.senderTransactionID = senderTransactionID;
        this.bankAccountID = bankAccountID;
        this.pixKeyID = pixKeyID;
        this.description = description;
        this.operation = operation;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public static Transaction newTransaction(
            final TransactionID aSenderTransactionID,
            final BankAccountID aBankAccountID,
            final PixKeyID aPixKeyID,
            final String aDescription,
            final TransactionOperation aOperation,
            final Double aAmount
    ) {
        return new Transaction(
                TransactionID.unique(),
                aSenderTransactionID,
                aBankAccountID,
                aPixKeyID,
                aDescription,
                aOperation,
                aAmount,
                Instant.now()
        );
    }

    public static Transaction with(
            final TransactionID anID,
            final TransactionID aSenderTransactionID,
            final BankAccountID aBankAccountID,
            final PixKeyID aPixKeyID,
            final String aDescription,
            final TransactionOperation aOperation,
            final Double aAmount,
            final Instant createdAt
    ) {
        return new Transaction(
                anID,
                aSenderTransactionID,
                aBankAccountID,
                aPixKeyID,
                aDescription,
                aOperation,
                aAmount,
                createdAt
        );
    }

    public static Transaction with(
            final Transaction aTransaction
    ) {
        return new Transaction(
                aTransaction.id,
                aTransaction.senderTransactionID,
                aTransaction.bankAccountID,
                aTransaction.pixKeyID,
                aTransaction.description,
                aTransaction.operation,
                aTransaction.amount,
                aTransaction.createdAt
        );
    }

    public TransactionID getSenderTransactionID() {
        return senderTransactionID;
    }

    public BankAccountID getBankAccountID() {
        return bankAccountID;
    }

    public PixKeyID getPixKeyID() {
        return pixKeyID;
    }

    public String getDescription() {
        return description;
    }

    public TransactionOperation getOperation() {
        return operation;
    }

    public Double getAmount() {
        return amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new TransactionValidator(this, handler);
    }

    public void validate(Transaction senderTransaction, BankAccountGateway bankAccountGateway, PixKeyGateway pixKeyGateway, ValidationHandler handler) {
        new TransactionValidator(this, senderTransaction, bankAccountGateway, pixKeyGateway, handler).validate();
    }
}
