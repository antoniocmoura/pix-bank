package com.antoniocmoura.pixbank.infrastructure.transaction.models;

import com.antoniocmoura.pixbank.domain.transaction.TransactionOperation;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record TransactionListResponse(
        @JsonProperty("id") String id,
        @JsonProperty("bank_account_id") String bankAccountId,
        @JsonProperty("sender_transaction_id") String senderTransactionId,
        @JsonProperty("pix_key") String pixKey,
        @JsonProperty("description") String description,
        @JsonProperty("operation") TransactionOperation operation,
        @JsonProperty("amount") Double amount,
        @JsonProperty("created_at") Instant createdAt
) {
}
