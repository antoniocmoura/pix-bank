package com.antoniocmoura.pixbank.application.transaction.create.transfer;

public record CreateTransferTransactionCommand(
        String pixKey,
        String senderPixKey,
        String description,
        Double amount
) {
    public static CreateTransferTransactionCommand with(
            final String PixKey,
            final String senderPixKey,
            final String description,
            final Double amount
    ) {
        return new CreateTransferTransactionCommand(
                PixKey,
                senderPixKey,
                description,
                amount
        );
    }
}
