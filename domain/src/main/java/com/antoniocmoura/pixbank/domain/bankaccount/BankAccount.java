package com.antoniocmoura.pixbank.domain.bankaccount;

import com.antoniocmoura.pixbank.domain.AggregateRoot;
import com.antoniocmoura.pixbank.domain.validation.ValidationHandler;

import java.time.Instant;

public class BankAccount extends AggregateRoot<BankAccountID> {
    private BankAccountHolder accountHolder;
    private Double balance;
    private Instant createdAt;
    private Instant updatedAt;

    private BankAccount(
            final BankAccountID anID,
            final BankAccountHolder aAccountHolder,
            final Double aBalance,
            final Instant aCreationDate,
            final Instant aUpdateDate
    ) {
        super(anID);
        this.accountHolder = aAccountHolder;
        this.balance = aBalance;
        this.createdAt = aCreationDate;
        this.updatedAt = aUpdateDate;
    }

    public static BankAccount newBankAccount(final String aFirstName, final String aLastName) {
        final var id = BankAccountID.unique();
        final var now = Instant.now();
        final BankAccountHolder aAccountHolder = BankAccountHolder.newAccountHolder(aFirstName, aLastName);
        return new BankAccount(id, aAccountHolder, 0.00, now, now);
    }

    public static BankAccount with(final BankAccount aBankAccount) {
        return new BankAccount(
                aBankAccount.getId(),
                aBankAccount.getAccountHolder(),
                aBankAccount.getBalance(),
                aBankAccount.getCreatedAt(),
                aBankAccount.getUpdatedAt()
        );
    }

    public static BankAccount with(
            final BankAccountID anId,
            final String aFirstName,
            final String aLastName,
            final Double aBalance,
            final Instant aCreatedAt,
            final Instant aUpdatedAt
    ) {
        final var accountHolder = BankAccountHolder.newAccountHolder(aFirstName, aLastName);
        return new BankAccount(
                anId,
                accountHolder,
                aBalance,
                aCreatedAt,
                aUpdatedAt
        );
    }

    public BankAccount update(
            final String aFirstName,
            final String aLastName
    ) {
        this.accountHolder = BankAccountHolder.newAccountHolder(aFirstName, aLastName);
        this.updatedAt = Instant.now();
        return this;
    }

    public BankAccount updateBalance(
            final Double value
    ) {
        this.balance += value;
        return this;
    }

    @Override
    public BankAccountID getId() {
        return id;
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new BankAccountValidator(this, handler).validate();
    }

    public BankAccountHolder getAccountHolder() {
        return accountHolder;
    }

    public Double getBalance() {
        return balance;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

}
