package com.antoniocmoura.pixbank.infrastructure.pixkey.persistence;

import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.domain.pagination.SearchQuery;
import com.antoniocmoura.pixbank.domain.pixkey.PixKey;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyGateway;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.antoniocmoura.pixbank.infrastructure.utils.SpecificationUtils.like;

@Service
public class PixKeyJpaGateway implements PixKeyGateway {

    private final PixKeyRepository repository;

    public PixKeyJpaGateway(final PixKeyRepository repository) {
        this.repository = repository;
    }

    @Override
    public PixKey create(final PixKey aPixKey) {
        return this.repository.save(PixKeyJpaEntity.from(aPixKey)).toAggregate();
    }

    @Override
    public PixKey update(final PixKey aPixKey) {
        return this.repository.save(PixKeyJpaEntity.from(aPixKey)).toAggregate();
    }

    @Override
    public Optional<PixKey> findById(PixKeyID anId) {
        return this.repository.findById(anId.getValue()).map(PixKeyJpaEntity::toAggregate);
    }

    @Override
    public Pagination<PixKey> findAll(SearchQuery aQuery) {

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
                pageResult.map(PixKeyJpaEntity::toAggregate).toList()
        );
    }

    private Specification<PixKeyJpaEntity> assembleSpecification(final String str) {
        final Specification<PixKeyJpaEntity> keyValue = like("id", str);
        return keyValue;
    }

}
