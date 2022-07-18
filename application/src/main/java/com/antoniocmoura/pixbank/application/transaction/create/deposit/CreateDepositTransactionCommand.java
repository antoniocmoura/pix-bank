package com.antoniocmoura.pixbank.application.transaction.create.deposit;

public record CreateDepositTransactionCommand(
        String pixKeyId,
        String description,
        Double amount
) {
    public static CreateDepositTransactionCommand with(
            final String PixKeyId,
            final String description,
            final Double amount
    ) {
        return new CreateDepositTransactionCommand(
                PixKeyId,
                description,
                amount
        );
    }
}
