package com.antoniocmoura.pixbank.domain.bankaccount;

import com.antoniocmoura.pixbank.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class BankAccountID extends Identifier {

    protected final String value;

    public BankAccountID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static BankAccountID unique() {
        return BankAccountID.from(UUID.randomUUID());
    }

    public static BankAccountID from(final String anId) {
        return new BankAccountID(anId);
    }

    public static BankAccountID from(final UUID anId) {
        return new BankAccountID(anId.toString().toLowerCase());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BankAccountID accountID = (BankAccountID) o;
        return getValue().equals(accountID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
