package com.antoniocmoura.pixbank.infrastructure.bankaccount.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateBankAccountRequest(
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName
) {
}
