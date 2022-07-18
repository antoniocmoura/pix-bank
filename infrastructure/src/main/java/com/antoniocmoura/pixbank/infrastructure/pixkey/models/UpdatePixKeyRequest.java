package com.antoniocmoura.pixbank.infrastructure.pixkey.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdatePixKeyRequest(
        @JsonProperty("pix_key") String pixKey
) {
}
