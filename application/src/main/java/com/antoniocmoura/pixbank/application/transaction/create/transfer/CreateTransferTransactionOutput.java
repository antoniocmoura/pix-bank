package com.antoniocmoura.pixbank.application.transaction.create.transfer;

import com.antoniocmoura.pixbank.domain.transaction.TransactionID;
import com.antoniocmoura.pixbank.domain.transaction.TransactionTransfer;

public record CreateTransferTransactionOutput(
        String transactionId,
        String senderTransactionId
){
    public static CreateTransferTransactionOutput from(
            final TransactionID anTransactionId,
            final TransactionID anSenderTransactionId
    ) {
        return new CreateTransferTransactionOutput(
                anTransactionId.getValue(),
                anSenderTransactionId.getValue()
        );
    }

    public static CreateTransferTransactionOutput from(
            final TransactionTransfer transactionTransfer
            ) {
        return new CreateTransferTransactionOutput(
                transactionTransfer.transactionId(),
                transactionTransfer.senderTransactionId()
        );
    }
}
