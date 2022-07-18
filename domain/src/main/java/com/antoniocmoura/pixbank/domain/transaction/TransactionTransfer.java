package com.antoniocmoura.pixbank.domain.transaction;

public record TransactionTransfer(
        String transactionId,
        String senderTransactionId
) {
    public static  TransactionTransfer from (
            final Transaction aTransaction,
            final Transaction aSenderTransaction
    ){
        return new  TransactionTransfer(
                aTransaction.getId().getValue(),
                aSenderTransaction.getId().getValue()
        );
    }
}
