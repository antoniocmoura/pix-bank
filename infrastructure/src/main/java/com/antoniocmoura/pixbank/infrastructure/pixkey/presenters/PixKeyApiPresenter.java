package com.antoniocmoura.pixbank.infrastructure.pixkey.presenters;

import com.antoniocmoura.pixbank.application.pixkey.retrieve.get.PixKeyOutput;
import com.antoniocmoura.pixbank.application.pixkey.retrieve.list.PixKeyListOutput;
import com.antoniocmoura.pixbank.infrastructure.pixkey.models.PixKeyListResponse;
import com.antoniocmoura.pixbank.infrastructure.pixkey.models.PixKeyResponse;

public interface PixKeyApiPresenter {

    static PixKeyResponse present(final PixKeyOutput output) {
        return new PixKeyResponse(
                output.id().getValue(),
                output.bankAccountID().getValue(),
                output.keyKind(),
                output.active(),
                output.createdAt(),
                output.updatedAt()
        );
    }

    static PixKeyListResponse present(final PixKeyListOutput output) {
        return new PixKeyListResponse(
                output.id().getValue(),
                output.bankAccountID().getValue(),
                output.keyKind(),
                output.active(),
                output.createdAt(),
                output.updatedAt()
        );
    }

}
