package com.antoniocmoura.pixbank.infrastructure.bankaccount.presenters;

import com.antoniocmoura.pixbank.application.bankaccount.retrieve.get.BankAccountOutput;
import com.antoniocmoura.pixbank.application.bankaccount.retrieve.list.BankAccountListOutput;
import com.antoniocmoura.pixbank.infrastructure.bankaccount.models.BankAccountResponse;
import com.antoniocmoura.pixbank.infrastructure.bankaccount.models.BankAccountListResponse;

public interface BankAccountApiPresenter {

    static BankAccountResponse present(final BankAccountOutput output) {
        return new BankAccountResponse(
                output.id().getValue(),
                output.firstName(),
                output.lastName(),
                output.balance(),
                output.createdAt(),
                output.updatedAt()
        );
    }

    static BankAccountListResponse present(final BankAccountListOutput output) {
        return new BankAccountListResponse(
                output.id().getValue(),
                output.firstName(),
                output.lastName(),
                output.balance(),
                output.createdAt(),
                output.updatedAt()
        );
    }

}
