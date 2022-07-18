package com.antoniocmoura.pixbank.application.pixkey.retrieve.get;

import com.antoniocmoura.pixbank.domain.exceptions.DomainException;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyGateway;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyID;
import com.antoniocmoura.pixbank.domain.validation.Error;

import java.util.function.Supplier;

public class DefaultGetPixKeyByIdUseCase extends GetPixKeyByIdUseCase {

    private final PixKeyGateway pixKeyGateway;

    public DefaultGetPixKeyByIdUseCase(final PixKeyGateway pixKeyGateway) {
        this.pixKeyGateway = pixKeyGateway;
    }

    @Override
    public PixKeyOutput execute(final String anID) {
        final var anPixKeyID = PixKeyID.from(anID);
        return this.pixKeyGateway.findById(anPixKeyID)
                .map(PixKeyOutput::from)
                .orElseThrow(notFound(anID));
    }

    private Supplier<DomainException> notFound(final String anID) {
        return () -> DomainException.with(
                new Error("Pix key with ID %S was not found".formatted(anID))
        );
    }
}
