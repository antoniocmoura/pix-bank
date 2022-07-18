package com.antoniocmoura.pixbank.application.bankaccount.create;

public record CreateBankAccountCommand(
        String firstName,
        String lastName
) {

    public static CreateBankAccountCommand with(
            final String aFirstName,
            final String aLastName
    ) {
        return new CreateBankAccountCommand(aFirstName, aLastName);
    }

}
