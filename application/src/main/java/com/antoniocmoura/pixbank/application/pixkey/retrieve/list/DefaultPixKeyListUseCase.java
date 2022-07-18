package com.antoniocmoura.pixbank.application.pixkey.retrieve.list;

import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.domain.pagination.SearchQuery;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyGateway;

public class DefaultPixKeyListUseCase extends PixKeyListUseCase {

   private final PixKeyGateway pixKeyGateway;

    public DefaultPixKeyListUseCase(final PixKeyGateway pixKeyGateway) {
        this.pixKeyGateway = pixKeyGateway;
    }

    @Override
    public Pagination<PixKeyListOutput> execute(SearchQuery aQuery) {
        return this.pixKeyGateway.findAll(aQuery)
                .map(PixKeyListOutput::from);
    }
}
