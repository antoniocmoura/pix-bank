package com.antoniocmoura.pixbank.application.bankaccount.delete;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountGateway;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;

import java.util.Objects;

public class DefaultDeleteBankAccountUseCase extends DeleteBankAccountUseCase {

    private final BankAccountGateway bankAccountGateway;

    public DefaultDeleteBankAccountUseCase(final BankAccountGateway bankAccountGateway) {
        this.bankAccountGateway = Objects.requireNonNull(bankAccountGateway);
    }

    @Override
    public void execute(String anIN) {
        this.bankAccountGateway.deleteById(BankAccountID.from(anIN));
    }
}
