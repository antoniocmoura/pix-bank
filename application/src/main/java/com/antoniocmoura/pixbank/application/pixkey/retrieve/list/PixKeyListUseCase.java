package com.antoniocmoura.pixbank.application.pixkey.retrieve.list;

import com.antoniocmoura.pixbank.application.UseCase;
import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.domain.pagination.SearchQuery;

public abstract class PixKeyListUseCase
        extends UseCase<SearchQuery, Pagination<PixKeyListOutput>> {
}
