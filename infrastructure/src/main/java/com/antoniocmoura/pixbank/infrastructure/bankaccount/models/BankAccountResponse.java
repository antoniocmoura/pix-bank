package com.antoniocmoura.pixbank.infrastructure.bankaccount.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record BankAccountResponse(
        @JsonProperty("id") String id,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
        @JsonProperty("balance") Double balance,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt
) {
}