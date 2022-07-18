package com.antoniocmoura.pixbank.domain.bankaccount;

import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.domain.pagination.SearchQuery;

import java.util.Optional;

public interface BankAccountGateway {

    BankAccount create(BankAccount aBankAccount);

    void deleteById(BankAccountID anId);

    Optional<BankAccount> findById(BankAccountID anId);

    BankAccount update(BankAccount aBankAccount);

    Pagination<BankAccount> findAll(SearchQuery aQuery);

}
