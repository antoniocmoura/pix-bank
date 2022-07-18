package com.antoniocmoura.pixbank.infrastructure.pixkey.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixKeyRepository extends JpaRepository<PixKeyJpaEntity, String> {
    Page<PixKeyJpaEntity> findAll(Specification<PixKeyJpaEntity> whereClause, Pageable page);
}
