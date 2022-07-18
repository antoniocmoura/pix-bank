package com.antoniocmoura.pixbank.infrastructure.transaction.persistence;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyID;
import com.antoniocmoura.pixbank.domain.transaction.Transaction;
import com.antoniocmoura.pixbank.domain.transaction.TransactionID;
import com.antoniocmoura.pixbank.domain.transaction.TransactionOperation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "transaction")
public class TransactionJpaEntity {

    @Id
    private String id;

    @Column(name = "sender_transaction_id", nullable = true)
    private String senderTransactionId;

    @Column(name = "bank_account_id", nullable = false)
    private String bankAccountId;

    @Column(name = "pix_key_id", nullable = false)
    private String pixKeyId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "operation", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionOperation operation;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public TransactionJpaEntity(
            String id,
            String senderTransactionId,
            String bankAccountId,
            String pixKeyId,
            String description,
            TransactionOperation operation,
            Double amount,
            Instant createdAt
    ) {
        this.id = id;
        this.senderTransactionId = senderTransactionId;
        this.bankAccountId = bankAccountId;
        this.pixKeyId = pixKeyId;
        this.description = description;
        this.operation = operation;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public static TransactionJpaEntity from(final Transaction aTransaction) {
        final var aSenderTransactionID = Objects.isNull(aTransaction.getSenderTransactionID()) ? null : aTransaction.getSenderTransactionID().getValue();
        return new TransactionJpaEntity(
                aTransaction.getId().getValue(),
                aSenderTransactionID,
                aTransaction.getBankAccountID().getValue(),
                aTransaction.getPixKeyID().getValue(),
                aTransaction.getDescription(),
                aTransaction.getOperation(),
                aTransaction.getAmount(),
                aTransaction.getCreatedAt()
        );
    }

    public Transaction toAggregate() {
        final var aSenderTransactionID = Objects.isNull(getSenderTransactionId()) ? null : TransactionID.from(getSenderTransactionId());
        return Transaction.with(
                TransactionID.from(getId()),
                aSenderTransactionID,
                BankAccountID.from(getBankAccountId()),
                PixKeyID.from(getPixKeyId()),
                getDescription(),
                getOperation(),
                getAmount(),
                getCreatedAt()
        );
    }
}
