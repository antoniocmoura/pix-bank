package com.antoniocmoura.pixbank.application.pixkey.update;

public record UpdatePixKeyCommand(
        String pixKey,
        boolean active
) {
    public static UpdatePixKeyCommand with(final String aPixKey, final boolean active) {
        return new UpdatePixKeyCommand(aPixKey, active);
    }
}
