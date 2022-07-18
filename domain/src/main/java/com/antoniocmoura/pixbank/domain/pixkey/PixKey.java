package com.antoniocmoura.pixbank.domain.pixkey;

import com.antoniocmoura.pixbank.domain.AggregateRoot;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;
import com.antoniocmoura.pixbank.domain.validation.ValidationHandler;

import java.time.Instant;

public class PixKey extends AggregateRoot<PixKeyID> {
    private BankAccountID bankAccountID;
    private KeyKind keyKind;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;

    protected PixKey(
            final PixKeyID pixKeyID,
            final BankAccountID bankAccountID,
            final KeyKind keyKind,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(pixKeyID);
        this.keyKind = keyKind;
        this.bankAccountID = bankAccountID;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PixKey newPixKey(
            final PixKeyID aPixKeyID,
            final BankAccountID aBankAccountID,
            final KeyKind aPixKeyKind
    ) {
        final var now = Instant.now();
        return new PixKey(aPixKeyID, aBankAccountID, aPixKeyKind, true, now, now);
    }

    public static PixKey with(final PixKey aPixKey) {
        return new PixKey(
                aPixKey.getId(),
                aPixKey.getBankAccountID(),
                aPixKey.getKeyKind(),
                aPixKey.isActive(),
                aPixKey.getCreatedAt(),
                aPixKey.getUpdatedAt()
        );
    }

    public PixKey updateActive(final boolean active) {
        this.active = active;
        this.updatedAt = Instant.now();
        return this;
    }

    public static PixKey with(
            final PixKeyID anId,
            final BankAccountID bankAccountID,
            final KeyKind keyKind,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt) {
        return new PixKey(
                anId,
                bankAccountID,
                keyKind,
                active,
                createdAt,
                updatedAt
        );
    }

    public KeyKind getKeyKind() {
        return keyKind;
    }

    public BankAccountID getBankAccountID() {
        return bankAccountID;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new PìxKeyValidator(this, handler).validate();
    }
}
