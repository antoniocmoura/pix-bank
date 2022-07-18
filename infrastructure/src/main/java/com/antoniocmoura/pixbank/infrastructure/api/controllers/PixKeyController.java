package com.antoniocmoura.pixbank.infrastructure.api.controllers;

import com.antoniocmoura.pixbank.application.pixkey.create.CreatePixKeyCommand;
import com.antoniocmoura.pixbank.application.pixkey.create.CreatePixKeyOutput;
import com.antoniocmoura.pixbank.application.pixkey.create.CreatePixKeyUseCase;
import com.antoniocmoura.pixbank.application.pixkey.retrieve.get.GetPixKeyByIdUseCase;
import com.antoniocmoura.pixbank.application.pixkey.retrieve.list.PixKeyListUseCase;
import com.antoniocmoura.pixbank.application.pixkey.update.UpdatePixKeyCommand;
import com.antoniocmoura.pixbank.application.pixkey.update.UpdatePixKeyOutput;
import com.antoniocmoura.pixbank.application.pixkey.update.UpdatePixKeyUseCase;
import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.domain.pagination.SearchQuery;
import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import com.antoniocmoura.pixbank.infrastructure.api.PixKeyAPI;
import com.antoniocmoura.pixbank.infrastructure.pixkey.models.CreatePixKeyRequest;
import com.antoniocmoura.pixbank.infrastructure.pixkey.models.PixKeyListResponse;
import com.antoniocmoura.pixbank.infrastructure.pixkey.models.PixKeyResponse;
import com.antoniocmoura.pixbank.infrastructure.pixkey.presenters.PixKeyApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.function.Function;

@RestController
public class PixKeyController implements PixKeyAPI {

    private final CreatePixKeyUseCase createPixKeyUseCase;
    private final GetPixKeyByIdUseCase getPixKeyByIdUseCase;
    private final PixKeyListUseCase pixKeyListUseCase;
    private final UpdatePixKeyUseCase updatePixKeyUseCase;

    public PixKeyController(
            final CreatePixKeyUseCase createPixKeyUseCase,
            final GetPixKeyByIdUseCase getPixKeyByIdUseCase,
            final PixKeyListUseCase pixKeyListUseCase,
            final UpdatePixKeyUseCase updatePixKeyUseCase
    ) {
        this.createPixKeyUseCase = createPixKeyUseCase;
        this.getPixKeyByIdUseCase = getPixKeyByIdUseCase;
        this.pixKeyListUseCase = pixKeyListUseCase;
        this.updatePixKeyUseCase = updatePixKeyUseCase;
    }

    @Override
    public ResponseEntity<?> createPixKey(final CreatePixKeyRequest input) {

        final var aCommand = CreatePixKeyCommand.with(
                input.pix_key(),
                input.bankAccountId(),
                input.key_kind()
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreatePixKeyOutput, ResponseEntity<?>> onSucces = output ->
                ResponseEntity.created(URI.create("/pix-key/" + output.id())).body(output);

        return this.createPixKeyUseCase.execute(aCommand)
                .fold(onError, onSucces);
    }

    @Override
    public ResponseEntity<?> deactivateById(final String aPixKeyID) {
        return updatePixKey(aPixKeyID, false);
    }

    @Override
    public ResponseEntity<?> activateById(String aPixKeyID) {
        return updatePixKey(aPixKeyID, true);
    }

    private ResponseEntity<?> updatePixKey(String id, boolean active) {
        final var aCommand = UpdatePixKeyCommand.with(id, active);

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdatePixKeyOutput, ResponseEntity<?>> onSucces =
                ResponseEntity::ok;

        return this.updatePixKeyUseCase.execute(aCommand)
                .fold(onError, onSucces);
    }

    @Override
    public Pagination<PixKeyListResponse> listPixKeys(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return pixKeyListUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(PixKeyApiPresenter::present);
    }

    @Override
    public PixKeyResponse getById(String id) {
        return PixKeyApiPresenter.present(getPixKeyByIdUseCase.execute(id));
    }
}
