package com.antoniocmoura.pixbank.infrastructure.api.controllers;

import com.antoniocmoura.pixbank.application.bankaccount.create.CreateBankAccountCommand;
import com.antoniocmoura.pixbank.application.bankaccount.create.CreateBankAccountOutput;
import com.antoniocmoura.pixbank.application.bankaccount.create.CreateBankAccountUseCase;
import com.antoniocmoura.pixbank.application.bankaccount.delete.DeleteBankAccountUseCase;
import com.antoniocmoura.pixbank.application.bankaccount.retrieve.get.GetBankAccountByIdUseCase;
import com.antoniocmoura.pixbank.application.bankaccount.retrieve.list.ListBankAccountUseCase;
import com.antoniocmoura.pixbank.application.bankaccount.update.UpdateBankAccountCommand;
import com.antoniocmoura.pixbank.application.bankaccount.update.UpdateBankAccountOutput;
import com.antoniocmoura.pixbank.application.bankaccount.update.UpdateBankAccountUseCase;
import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.domain.pagination.SearchQuery;
import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import com.antoniocmoura.pixbank.infrastructure.api.BankAccountAPI;
import com.antoniocmoura.pixbank.infrastructure.bankaccount.models.BankAccountListResponse;
import com.antoniocmoura.pixbank.infrastructure.bankaccount.models.BankAccountResponse;
import com.antoniocmoura.pixbank.infrastructure.bankaccount.models.CreateBankAccountRequest;
import com.antoniocmoura.pixbank.infrastructure.bankaccount.models.UpdateBankAccountRequest;
import com.antoniocmoura.pixbank.infrastructure.bankaccount.presenters.BankAccountApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class BankAccountController implements BankAccountAPI {

    private final CreateBankAccountUseCase createBankAccountUseCase;
    private final GetBankAccountByIdUseCase getBankAccountByIdUseCase;
    private final UpdateBankAccountUseCase updateBankAccountUseCase;
    private final DeleteBankAccountUseCase deleteBankAccountUseCase;
    private final ListBankAccountUseCase listBankAccountUseCase;

    public BankAccountController(
            final CreateBankAccountUseCase createBankAccountUseCase,
            final GetBankAccountByIdUseCase getBankAccountByIdUseCase,
            final UpdateBankAccountUseCase updateBankAccountUseCase,
            final DeleteBankAccountUseCase deleteBankAccountUseCase,
            final ListBankAccountUseCase listBankAccountUseCase
    ) {
        this.createBankAccountUseCase = Objects.requireNonNull(createBankAccountUseCase);
        this.getBankAccountByIdUseCase = Objects.requireNonNull(getBankAccountByIdUseCase);
        this.updateBankAccountUseCase = Objects.requireNonNull(updateBankAccountUseCase);
        this.deleteBankAccountUseCase = Objects.requireNonNull(deleteBankAccountUseCase);
        this.listBankAccountUseCase = Objects.requireNonNull(listBankAccountUseCase);
    }

    @Override
    public ResponseEntity<?> createBankAccount(final CreateBankAccountRequest input) {

        final var aCommand = CreateBankAccountCommand.with(
                input.firstName(),
                input.lastName()
        );

       /* final Function<Notification, ResponseEntity<?>> onError =
                ResponseEntity.unprocessableEntity()::body;
        */

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateBankAccountOutput, ResponseEntity<?>> onSucces = output ->
                ResponseEntity.created(URI.create("/bank-account/" + output.id())).body(output);

        return this.createBankAccountUseCase.execute(aCommand)
                .fold(onError, onSucces);
    }

    @Override
    public Pagination<BankAccountListResponse> listBankAccounts(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
           return listBankAccountUseCase.execute(new SearchQuery(page, perPage,search, sort, direction))
                   .map(BankAccountApiPresenter::present);
    }

    @Override
    public BankAccountResponse getById(final String id) {
       /* return BankAccountApiPresenter.present
                .compose(this.getBankAccountByIdUseCase::execute)
                .apply(id);*/
        return BankAccountApiPresenter.present(this.getBankAccountByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateBankAccountRequest input) {
        final var aCommand = UpdateBankAccountCommand.with(
                id,
                input.firstName(),
                input.lastName()
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateBankAccountOutput, ResponseEntity<?>> onSucces =
                ResponseEntity::ok;

        return this.updateBankAccountUseCase.execute(aCommand)
                .fold(onError, onSucces);
    }

    @Override
    public void deleteById(final String anId) {
        this.deleteBankAccountUseCase.execute(anId);
    }
}
