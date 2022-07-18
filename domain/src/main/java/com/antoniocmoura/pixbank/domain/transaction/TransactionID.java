package com.antoniocmoura.pixbank.domain.transaction;

import com.antoniocmoura.pixbank.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class TransactionID extends Identifier {

    protected final String value;

    public TransactionID(
            final String value
    ) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static TransactionID unique() {
        return TransactionID.from(UUID.randomUUID());
    }

    public static TransactionID from(final String anId) {
        return new TransactionID(anId);
    }

    public static TransactionID from(final UUID anId) {
        return new TransactionID(anId.toString().toLowerCase());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TransactionID transactionID = (TransactionID) o;
        return getValue().equals(transactionID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

}
