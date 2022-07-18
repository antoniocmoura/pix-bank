package com.antoniocmoura.pixbank.application.transaction.retrieve.list;

import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.domain.pagination.SearchQuery;
import com.antoniocmoura.pixbank.domain.transaction.TransactionGateway;

public class DefaultTransactionListUseCase extends  TransactionListUseCase{

    private final TransactionGateway transactionGateway;

    public DefaultTransactionListUseCase(final TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    @Override
    public Pagination<TransactionListOutput> execute(SearchQuery aQuery) {
        return this.transactionGateway.findAll(aQuery).map(TransactionListOutput::from);
    }
}
