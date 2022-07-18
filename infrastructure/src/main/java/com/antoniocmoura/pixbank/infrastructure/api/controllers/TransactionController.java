package com.antoniocmoura.pixbank.infrastructure.api.controllers;

import com.antoniocmoura.pixbank.application.transaction.create.deposit.CreateDepositTransactionCommand;
import com.antoniocmoura.pixbank.application.transaction.create.deposit.CreateDepositTransactionOutput;
import com.antoniocmoura.pixbank.application.transaction.create.deposit.CreateDepositTransactionUseCase;
import com.antoniocmoura.pixbank.application.transaction.create.transfer.CreateTransferTransactionCommand;
import com.antoniocmoura.pixbank.application.transaction.create.transfer.CreateTransferTransactionOutput;
import com.antoniocmoura.pixbank.application.transaction.create.transfer.CreateTransferTransactionUseCase;
import com.antoniocmoura.pixbank.application.transaction.retrieve.get.GetTransactionByIdUseCase;
import com.antoniocmoura.pixbank.application.transaction.retrieve.list.TransactionListByBankAccountIdUseCase;
import com.antoniocmoura.pixbank.application.transaction.retrieve.list.TransactionListUseCase;
import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.domain.pagination.SearchQuery;
import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import com.antoniocmoura.pixbank.infrastructure.api.TransactionAPI;
import com.antoniocmoura.pixbank.infrastructure.transaction.models.CreateDepositTransactionRequest;
import com.antoniocmoura.pixbank.infrastructure.transaction.models.CreateTransferTransactionRequest;
import com.antoniocmoura.pixbank.infrastructure.transaction.models.TransactionListResponse;
import com.antoniocmoura.pixbank.infrastructure.transaction.models.TransactionResponse;
import com.antoniocmoura.pixbank.infrastructure.transaction.presenters.TransactionApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.function.Function;

@RestController
public class TransactionController implements TransactionAPI {

    private final CreateTransferTransactionUseCase createTransferTransactionUseCase;
    private final CreateDepositTransactionUseCase createDepositTransactionUseCase;
    private final TransactionListUseCase transactionListUseCase;

    private final GetTransactionByIdUseCase getTransactionByIdUseCase;
    private final TransactionListByBankAccountIdUseCase transactionListByBankAccountIdUseCase;

    public TransactionController(
            final CreateTransferTransactionUseCase createTransferTransactionUseCase,
            final CreateDepositTransactionUseCase createDepositTransactionUseCase,
            final TransactionListUseCase transactionListUseCase,
            final GetTransactionByIdUseCase getTransactionByIdUseCase,
            final TransactionListByBankAccountIdUseCase transactionListByBankAccountIdUseCase
    ) {
        this.createTransferTransactionUseCase = createTransferTransactionUseCase;
        this.createDepositTransactionUseCase = createDepositTransactionUseCase;
        this.transactionListUseCase = transactionListUseCase;
        this.getTransactionByIdUseCase = getTransactionByIdUseCase;
        this.transactionListByBankAccountIdUseCase = transactionListByBankAccountIdUseCase;
    }

    @Override
    public ResponseEntity<?> createTransferTransaction(CreateTransferTransactionRequest input) {

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateTransferTransactionOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/transaction/transfer/" + output.transactionId())).body(output);

        final var aCommand = CreateTransferTransactionCommand.with(
                input.pixKey(),
                input.senderPixKey(),
                input.description(),
                input.amount()
        );

        return this.createTransferTransactionUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<?> createDepositTransaction(CreateDepositTransactionRequest input) {
        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateDepositTransactionOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/transaction/transfer/" + output.transactionId())).body(output);

        final var aCommand = CreateDepositTransactionCommand.with(
                input.pixKey(),
                input.description(),
                input.amount()
        );

        return this.createDepositTransactionUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public Pagination<TransactionListResponse> listTransactions(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return transactionListUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(TransactionApiPresenter::present);
    }

    @Override
    public TransactionResponse getById(String id) {
        return TransactionApiPresenter.present(getTransactionByIdUseCase.execute(id));
    }

    @Override
    public Pagination<TransactionListResponse> getByBankAccountId(
            String search,
            int page, int perPage,
            String sort,
            String direction) {
        return transactionListByBankAccountIdUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(TransactionApiPresenter::present);
    }


}
