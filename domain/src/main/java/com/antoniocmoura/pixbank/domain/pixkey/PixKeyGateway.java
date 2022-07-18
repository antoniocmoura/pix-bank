package com.antoniocmoura.pixbank.domain.pixkey;

import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.domain.pagination.SearchQuery;

import java.util.Optional;

public interface PixKeyGateway {

    PixKey create(PixKey aPixKey);

    PixKey update(PixKey aPixKey);

    Optional<PixKey> findById(PixKeyID anID);

    Pagination<PixKey> findAll(SearchQuery aQuery);

}
