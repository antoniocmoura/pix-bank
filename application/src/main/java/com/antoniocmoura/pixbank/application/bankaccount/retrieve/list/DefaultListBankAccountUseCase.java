package com.antoniocmoura.pixbank.application.bankaccount.retrieve.list;

import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountGateway;
import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.domain.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListBankAccountUseCase extends ListBankAccountUseCase {

    private final BankAccountGateway bankAccountGateway;

    public DefaultListBankAccountUseCase(final BankAccountGateway bankAccountGateway) {
        this.bankAccountGateway = Objects.requireNonNull(bankAccountGateway);
    }

    @Override
    public Pagination<BankAccountListOutput> execute(final SearchQuery aQuery) {
        return this.bankAccountGateway.findAll(aQuery)
                .map(BankAccountListOutput::from);
    }
}
