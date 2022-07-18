package com.antoniocmoura.pixbank.infrastructure.transaction.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionJpaEntity, String > {
    Page<TransactionJpaEntity> findAll(Specification<TransactionJpaEntity> whereClause, Pageable page);
}
