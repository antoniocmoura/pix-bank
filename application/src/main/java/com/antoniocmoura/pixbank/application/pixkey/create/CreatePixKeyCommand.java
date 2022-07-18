package com.antoniocmoura.pixbank.application.pixkey.create;

import com.antoniocmoura.pixbank.domain.pixkey.KeyKind;

public record CreatePixKeyCommand(
        String id,
        String bankAccountID,
        KeyKind keyKind
) {
    public static CreatePixKeyCommand with(
            final String anID,
            final String aBankAccountID,
            final KeyKind aKeyKind
    ) {
        return new CreatePixKeyCommand(anID, aBankAccountID, aKeyKind);
    }
}
