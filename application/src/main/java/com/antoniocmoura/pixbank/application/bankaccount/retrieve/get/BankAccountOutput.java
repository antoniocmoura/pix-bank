package com.antoniocmoura.pixbank.application.bankaccount.retrieve.get;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccount;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;

import java.time.Instant;

public record BankAccountOutput(
        BankAccountID id,
        String firstName,
        String lastName,
        Double balance,
        Instant createdAt,
        Instant updatedAt
) {

    public static BankAccountOutput from(final BankAccount aBankAccount) {
        return new BankAccountOutput(
                aBankAccount.getId(),
                aBankAccount.getAccountHolder().getFirstName(),
                aBankAccount.getAccountHolder().getLastName(),
                aBankAccount.getBalance(),
                aBankAccount.getCreatedAt(),
                aBankAccount.getUpdatedAt()
        );
    }

}
