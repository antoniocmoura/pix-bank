package com.antoniocmoura.pixbank.application.transaction.create.deposit;

public record CreateDepositTransactionOutput(
        String transactionId
) {
    public static CreateDepositTransactionOutput from(String aTransactionId) {
        return new CreateDepositTransactionOutput(aTransactionId);
    }
}
