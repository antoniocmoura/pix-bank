package com.antoniocmoura.pixbank.application.pixkey.create;

import com.antoniocmoura.pixbank.domain.pixkey.PixKey;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyID;

public record CreatePixKeyOutput(
        String id
) {
    public static CreatePixKeyOutput from(final PixKeyID anId) {
        return new CreatePixKeyOutput(
                anId.getValue()
        );
    }

    public static CreatePixKeyOutput from(final PixKey aPixKey) {
        return new CreatePixKeyOutput(
                aPixKey.getId().getValue()
        );
    }
}
