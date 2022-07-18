package com.antoniocmoura.pixbank.infrastructure.bankaccount.persistence;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccount;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bank_account")
public class BankAccountJpaEntity {

    @Id
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public BankAccountJpaEntity (
            final String id,
            final String firstName,
            final String lastName,
            final Double balance,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static BankAccountJpaEntity from(final BankAccount aBankAccount) {
        return new BankAccountJpaEntity(
                aBankAccount.getId().getValue(),
                aBankAccount.getAccountHolder().getFirstName(),
                aBankAccount.getAccountHolder().getLastName(),
                aBankAccount.getBalance(),
                aBankAccount.getCreatedAt(),
                aBankAccount.getUpdatedAt()
        );
    }

    public BankAccount toAggregate() {
       return BankAccount.with(
               BankAccountID.from(getId()),
               getFirstName(),
               getLastName(),
               getBalance(),
               getCreatedAt(),
               getUpdatedAt()
       );
    }

}
