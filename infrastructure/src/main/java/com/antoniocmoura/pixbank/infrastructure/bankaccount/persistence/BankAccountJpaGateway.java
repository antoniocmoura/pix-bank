package com.antoniocmoura.pixbank.infrastructure.bankaccount.persistence;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccount;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountGateway;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountID;
import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.domain.pagination.SearchQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.antoniocmoura.pixbank.infrastructure.utils.SpecificationUtils.like;

@Service
public class BankAccountJpaGateway implements BankAccountGateway {

    private final BankAccountRepository repository;

    public BankAccountJpaGateway(final BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public BankAccount create(final BankAccount aBankAccount) {
        return this.save(aBankAccount);
    }

    @Override
    public void deleteById(final BankAccountID anId) {
        final String anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anId.getValue());
        }
    }

    @Override
    public Optional<BankAccount> findById(final BankAccountID anId) {
        return this.repository.findById(anId.getValue()).map(BankAccountJpaEntity::toAggregate);
    }

    @Override
    public BankAccount update(final BankAccount aBankAccount) {
        return this.save(aBankAccount);
    }

    @Override
    public Pagination<BankAccount> findAll(SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Direction.fromString(aQuery.direction()), aQuery.sort())
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
                pageResult.map(BankAccountJpaEntity::toAggregate).toList()
        );
    }

    private BankAccount save(final BankAccount aBankAccount) {
        return this.repository.save(BankAccountJpaEntity.from(aBankAccount)).toAggregate();
    }

    private Specification<BankAccountJpaEntity> assembleSpecification(final String str) {
        final Specification<BankAccountJpaEntity> firstNameLike = like("firstName", str);
        final Specification<BankAccountJpaEntity> lastNameLike = like("lastName", str);
        return firstNameLike.or(lastNameLike);
    }

}
