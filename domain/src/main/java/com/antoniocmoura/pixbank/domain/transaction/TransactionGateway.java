package com.antoniocmoura.pixbank.domain.transaction;

import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.domain.pagination.SearchQuery;

import java.util.Optional;

public interface TransactionGateway {

    Transaction create(Transaction aTransaction);

    TransactionTransfer createTransfer(Transaction aDebitTransaction, Transaction aCreditTransaction);

    String createDeposit(Transaction aCreditTransaction);

    Pagination<Transaction> findAll(SearchQuery aQuery);

    Optional<Transaction> findById(TransactionID anID);

    Pagination<Transaction> findByAccountID(SearchQuery aQuery);

}
