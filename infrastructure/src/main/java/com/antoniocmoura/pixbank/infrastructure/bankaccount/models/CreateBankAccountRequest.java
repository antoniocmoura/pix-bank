package com.antoniocmoura.pixbank.infrastructure.bankaccount.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateBankAccountRequest(
       @JsonProperty("first_name") String firstName,
       @JsonProperty("last_name") String lastName
) {
}
