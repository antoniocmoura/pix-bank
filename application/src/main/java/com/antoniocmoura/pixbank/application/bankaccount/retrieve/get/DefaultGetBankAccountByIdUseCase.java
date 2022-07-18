package com.antoniocmoura.pixbank.application.bankaccount.retrieve.get;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountGateway;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;
import com.antoniocmoura.pixbank.domain.exceptions.DomainException;
import com.antoniocmoura.pixbank.domain.validation.Error;

import java.util.function.Supplier;

public class DefaultGetBankAccountByIdUseCase extends GetBankAccountByIdUseCase {

    private final BankAccountGateway bankAccountGateway;

    public DefaultGetBankAccountByIdUseCase(final BankAccountGateway bankAccountGateway) {
        this.bankAccountGateway = bankAccountGateway;
    }

    @Override
    public BankAccountOutput execute(String anIn) {
        final var anBankAccountID = BankAccountID.from(anIn);

        return this.bankAccountGateway.findById(anBankAccountID)
                .map(BankAccountOutput::from)
                .orElseThrow(notFound(anBankAccountID));
    }

    private Supplier<DomainException> notFound(final BankAccountID anId) {
        return () -> DomainException.with(
                new Error("Bank Account with ID %S was not found".formatted(anId.getValue()))
        );
    }

}
