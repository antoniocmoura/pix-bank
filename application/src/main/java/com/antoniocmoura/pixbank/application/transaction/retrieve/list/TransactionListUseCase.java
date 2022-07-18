package com.antoniocmoura.pixbank.application.transaction.retrieve.list;

import com.antoniocmoura.pixbank.application.UseCase;
import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.domain.pagination.SearchQuery;

public abstract class TransactionListUseCase
        extends UseCase<SearchQuery, Pagination<TransactionListOutput>> {
}
