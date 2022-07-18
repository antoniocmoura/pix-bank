package com.antoniocmoura.pixbank.infrastructure.pixkey.models;

import com.antoniocmoura.pixbank.domain.pixkey.KeyKind;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record PixKeyListResponse(
        @JsonProperty("id") String id,
        @JsonProperty("bank_account_id") String bankAccountId,
        @JsonProperty("key_kind") KeyKind key_kind,
        @JsonProperty("is_active") Boolean active,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("deleted_at") Instant deletedAt
) {
}
