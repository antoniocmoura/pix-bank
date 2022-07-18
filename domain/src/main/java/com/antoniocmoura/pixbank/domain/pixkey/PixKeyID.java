package com.antoniocmoura.pixbank.domain.pixkey;

import com.antoniocmoura.pixbank.domain.Identifier;

import java.util.Objects;

public class PixKeyID extends Identifier {

    protected final String value;

    public PixKeyID(
            final String value
    ) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static PixKeyID from(final String anId) {
        return new PixKeyID(anId);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PixKeyID pixKeyID = (PixKeyID) o;
        return getValue().equals(pixKeyID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
