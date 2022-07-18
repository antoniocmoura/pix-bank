package com.antoniocmoura.pixbank.infrastructure.bankaccount.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountJpaEntity, String> {

    Page<BankAccountJpaEntity> findAll(Specification<BankAccountJpaEntity> whereClause, Pageable page);

}
