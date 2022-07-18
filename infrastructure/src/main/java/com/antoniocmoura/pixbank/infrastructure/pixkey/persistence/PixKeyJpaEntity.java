package com.antoniocmoura.pixbank.infrastructure.pixkey.persistence;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;
import com.antoniocmoura.pixbank.domain.pixkey.KeyKind;
import com.antoniocmoura.pixbank.domain.pixkey.PixKey;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "pix_key")
public class PixKeyJpaEntity {

    @Id
    private String id;

    @Column(name = "bank_account_id", nullable = false)
    private String bankAccountID;

    @Column(name = "key_kind", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private KeyKind keyKind;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public PixKeyJpaEntity(
            String id,
            String bankAccountID,
            KeyKind keyKind,
            boolean active,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.bankAccountID = bankAccountID;
        this.keyKind = keyKind;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PixKeyJpaEntity from(final PixKey aPixKey) {
        return new PixKeyJpaEntity(
                aPixKey.getId().getValue(),
                aPixKey.getBankAccountID().getValue(),
                aPixKey.getKeyKind(),
                aPixKey.isActive(),
                aPixKey.getCreatedAt(),
                aPixKey.getUpdatedAt()
        );
    }

    public PixKey toAggregate() {
        return PixKey.with(
                PixKeyID.from(getId()),
                BankAccountID.from(getBankAccountID()),
                getKeyKind(),
                isActive(),
                getCreatedAt(),
                getUpdatedAt()
        );
    }

}
