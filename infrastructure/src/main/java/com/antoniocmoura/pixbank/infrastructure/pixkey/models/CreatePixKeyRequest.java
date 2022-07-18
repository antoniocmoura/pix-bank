package com.antoniocmoura.pixbank.infrastructure.pixkey.models;

import com.antoniocmoura.pixbank.domain.pixkey.KeyKind;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreatePixKeyRequest(
        @JsonProperty("bank_account_id") String bankAccountId,
        @JsonProperty("pix_key") String pix_key,
        @JsonProperty("key_kind") KeyKind key_kind
) {
}
