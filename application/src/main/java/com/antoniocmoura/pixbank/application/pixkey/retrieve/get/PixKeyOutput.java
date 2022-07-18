package com.antoniocmoura.pixbank.application.pixkey.retrieve.get;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;
import com.antoniocmoura.pixbank.domain.pixkey.KeyKind;
import com.antoniocmoura.pixbank.domain.pixkey.PixKey;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyID;

import java.time.Instant;

public record PixKeyOutput(
        PixKeyID id,
        BankAccountID bankAccountID,
        KeyKind keyKind,
        boolean active,
        Instant createdAt,
        Instant updatedAt
) {
    public static PixKeyOutput from(final PixKey aPixKey) {
        return new PixKeyOutput(
                aPixKey.getId(),
                aPixKey.getBankAccountID(),
                aPixKey.getKeyKind(),
                aPixKey.isActive(),
                aPixKey.getCreatedAt(),
                aPixKey.getUpdatedAt()
        );
    }
}
