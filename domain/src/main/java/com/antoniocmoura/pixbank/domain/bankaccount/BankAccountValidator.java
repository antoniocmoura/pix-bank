package com.antoniocmoura.pixbank.domain.bankaccount;

import com.antoniocmoura.pixbank.domain.validation.ValidationHandler;
import com.antoniocmoura.pixbank.domain.validation.Validator;
import com.antoniocmoura.pixbank.domain.validation.Error;

public class BankAccountValidator extends Validator {

    public static final int NAME_MAX_LENGTH = 60;
    public static final int NAME_MIN_LENGTH = 3;
    private final BankAccount bankAccount;

    protected BankAccountValidator(final BankAccount aBankAccount, final ValidationHandler aHandler) {
        super(aHandler);
        this.bankAccount = aBankAccount;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {

        final var firstName = this.bankAccount.getAccountHolder().getFirstName();
        if (firstName == null) {
            this.validationHandler().append(new Error("'first_name' should not be null"));
            return;
        }

        if (firstName.isBlank()) {
            this.validationHandler().append(new Error("'first_name' should not be empty"));
            return;
        }

        final var firstNameLength = firstName.trim().length();
        if (firstNameLength > NAME_MAX_LENGTH || firstNameLength < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error(
                    String.format("'first_name' must be between %1$s and %2$s", NAME_MIN_LENGTH, NAME_MAX_LENGTH)
            ));
            return;
        }

        final var lastName = this.bankAccount.getAccountHolder().getLastName();

        if (lastName == null) {
            this.validationHandler().append(new Error("'last_name' should not be null"));
            return;
        }

        if (lastName.isBlank()) {
            this.validationHandler().append(new Error("'last_name' should not be empty"));
            return;
        }

        final var lastNameLength = firstName.trim().length();
        if (lastNameLength > NAME_MAX_LENGTH || lastNameLength < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error(
                    String.format("'last_name' must be between %1$s and %2$s", NAME_MIN_LENGTH, NAME_MAX_LENGTH)
            ));
            return;
        }
    }
}
