package com.antoniocmoura.pixbank.infrastructure.transaction.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateDepositTransactionRequest(
        @JsonProperty("pix_key") String pixKey,
        @JsonProperty("description") String description,
        @JsonProperty("amount") Double amount
) {
}
