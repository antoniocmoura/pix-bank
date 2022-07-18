package com.antoniocmoura.pixbank.application.bankaccount.retrieve.list;


import com.antoniocmoura.pixbank.application.UseCase;
import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.domain.pagination.SearchQuery;

public abstract class ListBankAccountUseCase
        extends UseCase<SearchQuery, Pagination<BankAccountListOutput>> {
}
