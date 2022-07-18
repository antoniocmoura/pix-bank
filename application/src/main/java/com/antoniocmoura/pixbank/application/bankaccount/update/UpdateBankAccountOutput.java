package com.antoniocmoura.pixbank.application.bankaccount.update;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccount;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;

import java.time.Instant;

public record UpdateBankAccountOutput(
        String id
) {
    public static UpdateBankAccountOutput from(final BankAccount aBankAccount) {
        return new UpdateBankAccountOutput(
                aBankAccount.getId().getValue()
        );
    }
}
