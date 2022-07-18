package com.antoniocmoura.pixbank.application.bankaccount.update;

public record UpdateBankAccountCommand(
        String id,
        String firstName,
        String lastName
) {
    public static UpdateBankAccountCommand with(
            final String anId,
            final String aFirstName,
            final String aLastName
    ) {
        return new UpdateBankAccountCommand(anId, aFirstName, aLastName);
    }
}
