package com.antoniocmoura.pixbank.application.bankaccount.retrieve.list;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccount;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;

import java.time.Instant;

public record BankAccountListOutput(
        BankAccountID id,
        String firstName,
        String lastName,
        Double balance,
        Instant createdAt,
        Instant updatedAt
) {
    public static  BankAccountListOutput from(final BankAccount aBankAccount) {
        return new BankAccountListOutput(
                aBankAccount.getId(),
                aBankAccount.getAccountHolder().getFirstName(),
                aBankAccount.getAccountHolder().getLastName(),
                aBankAccount.getBalance(),
                aBankAccount.getCreatedAt(),
                aBankAccount.getUpdatedAt()
        );
    }
}
