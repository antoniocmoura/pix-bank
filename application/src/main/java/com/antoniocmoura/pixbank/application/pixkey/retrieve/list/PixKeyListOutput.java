package com.antoniocmoura.pixbank.application.pixkey.retrieve.list;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;
import com.antoniocmoura.pixbank.domain.pixkey.KeyKind;
import com.antoniocmoura.pixbank.domain.pixkey.PixKey;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyID;

import java.time.Instant;

public record PixKeyListOutput(
        PixKeyID id,
        KeyKind keyKind,
        BankAccountID bankAccountID,
        boolean active,
        Instant createdAt,
        Instant updatedAt
) {
    public static PixKeyListOutput from (final PixKey aPixKey) {
        return new PixKeyListOutput(
                aPixKey.getId(),
                aPixKey.getKeyKind(),
                aPixKey.getBankAccountID(),
                aPixKey.isActive(),
                aPixKey.getCreatedAt(),
                aPixKey.getUpdatedAt()
        );
    }
}
