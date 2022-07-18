package com.antoniocmoura.pixbank.application.pixkey.update;

import com.antoniocmoura.pixbank.domain.pixkey.PixKey;

public record UpdatePixKeyOutput(
        String id
) {
    public static UpdatePixKeyOutput from(final PixKey aPixKey
    ) {
        return new UpdatePixKeyOutput(
                aPixKey.getId().getValue()
        );
    }
}
