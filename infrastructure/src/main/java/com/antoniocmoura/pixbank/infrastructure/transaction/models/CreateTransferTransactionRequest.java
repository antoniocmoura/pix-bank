package com.antoniocmoura.pixbank.infrastructure.transaction.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateTransferTransactionRequest(
        @JsonProperty("pix_key") String pixKey,
        @JsonProperty("sender_pix_key") String senderPixKey,
        @JsonProperty("description") String description,
        @JsonProperty("amount") Double amount
) {
}
