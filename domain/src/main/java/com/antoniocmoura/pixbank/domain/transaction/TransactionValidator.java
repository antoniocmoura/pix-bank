package com.antoniocmoura.pixbank.domain.transaction;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountGateway;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyGateway;
import com.antoniocmoura.pixbank.domain.validation.Error;
import com.antoniocmoura.pixbank.domain.validation.ValidationHandler;
import com.antoniocmoura.pixbank.domain.validation.Validator;

import java.util.Objects;

public class TransactionValidator extends Validator {
    private final Transaction transaction;
    private Transaction senderTransaction;
    private BankAccountGateway bankAccountGateway;
    private PixKeyGateway pixKeyGateway;

    protected TransactionValidator(
            final Transaction transaction,
            final ValidationHandler aHandler
    ) {
        super(aHandler);
        this.transaction = transaction;
    }

    public TransactionValidator(
            final Transaction transaction,
            final Transaction senderTransaction,
            final BankAccountGateway bankAccountGateway,
            final PixKeyGateway pixKeyGateway,
            final ValidationHandler aHandler
    ) {
        super(aHandler);
        this.senderTransaction = senderTransaction;
        this.bankAccountGateway = bankAccountGateway;
        this.pixKeyGateway = pixKeyGateway;
        this.transaction = transaction;
    }

    @Override
    public void validate() {

        final var aPixKeyID = this.transaction.getPixKeyID();
        if (aPixKeyID == null) {
            this.validationHandler().append(new Error("'pix_key' should not be null"));
            return;
        }

        if (aPixKeyID.getValue().isBlank()) {
            this.validationHandler().append(new Error("'pix_key' should not be empty"));
            return;
        }

        final var aDescription = this.transaction.getDescription();
        if (aDescription == null) {
            this.validationHandler().append(new Error("'description' should not be null"));
            return;
        }

        if (aDescription.isBlank()) {
            this.validationHandler().append(new Error("'description' should not be empty"));
            return;
        }

        final var aAmount = this.transaction.getAmount();
        if (aAmount == null) {
            this.validationHandler().append(new Error("'amount' should not be null"));
            return;
        }

        if (aAmount <= 0) {
            this.validationHandler().append(new Error("'amount' should be greater than zero"));
            return;
        }

        final var aPixKey = pixKeyGateway.findById(aPixKeyID);
        if (aPixKey.isEmpty()) {
            this.validationHandler().append(new Error("'pix_key' with ID %S not exists".formatted(aPixKeyID.getValue())));
            return;
        }

        if (!aPixKey.get().isActive()) {
            this.validationHandler().append(new Error("'pix_key' with ID %S is disabled".formatted(aPixKeyID.getValue())));
            return;
        }

        if (!Objects.isNull(senderTransaction)) {

            final var aSenderPixKey = pixKeyGateway.findById(senderTransaction.getPixKeyID());
            if (aSenderPixKey.isEmpty()) {
                this.validationHandler().append(new Error("'sender_pix_key' with ID %S not exists".formatted(aPixKeyID.getValue())));
                return;
            }

            final var aBankAccountID = aPixKey.get().getBankAccountID();
            final var aSenderBankAccountID = aSenderPixKey.get().getBankAccountID();
            if (aBankAccountID.equals(aSenderBankAccountID)) {
                this.validationHandler().append(new Error("transactions between pix keys from the same bank account are not allowed"));
                return;
            }

        }

        final var operation = this.transaction.getOperation();
        if (operation == TransactionOperation.DEBIT) {
            final var aBankAccountID = aPixKey.get().getBankAccountID();
            final var aBankAccount = bankAccountGateway.findById(aBankAccountID).get();

            if (aBankAccount.getBalance() < aAmount) {
                this.validationHandler().append(new Error("There is not enough balance to complete the transaction"));
                return;
            }
        }


    }
}
