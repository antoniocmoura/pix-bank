package com.antoniocmoura.pixbank.domain.bankaccount;

import com.antoniocmoura.pixbank.domain.ValueObject;

import java.util.Objects;

public class BankAccountHolder extends ValueObject {

    private String firstName;
    private String lastName;

    private BankAccountHolder(final String aFirstName, final String aLastName) {
        Objects.requireNonNull(aFirstName);
        Objects.requireNonNull(aLastName);
        this.firstName = aFirstName;
        this.lastName = aLastName;
    }

    protected static BankAccountHolder newAccountHolder(final String aFirstName, final String aLastName) {
        return new BankAccountHolder(aFirstName, aLastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
