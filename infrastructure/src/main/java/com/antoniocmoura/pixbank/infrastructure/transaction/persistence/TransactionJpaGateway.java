package com.antoniocmoura.pixbank.infrastructure.transaction.persistence;

import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.domain.pagination.SearchQuery;
import com.antoniocmoura.pixbank.domain.transaction.Transaction;
import com.antoniocmoura.pixbank.domain.transaction.TransactionGateway;
import com.antoniocmoura.pixbank.domain.transaction.TransactionID;
import com.antoniocmoura.pixbank.domain.transaction.TransactionTransfer;
import com.antoniocmoura.pixbank.infrastructure.utils.SpecificationUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionJpaGateway implements TransactionGateway {

    private final TransactionRepository repository;

    public TransactionJpaGateway(final TransactionRepository transactionRepository) {
        this.repository = transactionRepository;
    }

    @Override
    public Transaction create(Transaction aTransaction) {
        return this.repository.save(TransactionJpaEntity.from(aTransaction)).toAggregate();
    }

    @Override
    public TransactionTransfer createTransfer(Transaction aDebitTransaction, Transaction aCreditTransaction) {
        return TransactionTransfer.from(
                create(aDebitTransaction),
                create(aCreditTransaction)
        );
    }

    @Override
    public String createDeposit(Transaction aCreditTransaction) {
        return create(aCreditTransaction).getId().getValue();
    }

    @Override
    public Pagination<Transaction> findAll(SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult =
                this.repository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(TransactionJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public Optional<Transaction> findById(TransactionID anID) {
        return this.repository.findById(anID.getValue()).map(TransactionJpaEntity::toAggregate);
    }

    @Override
    public Pagination<Transaction> findByAccountID(SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult =
                this.repository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(TransactionJpaEntity::toAggregate).toList()
        );
    }

    private Specification<TransactionJpaEntity> assembleSpecification(final String str) {
        final Specification<TransactionJpaEntity> bankAccountId = SpecificationUtils.equals("bankAccountId", str);
        return bankAccountId;
    }
}
