package com.antoniocmoura.pixbank.domain.validation.handler;

import com.antoniocmoura.pixbank.domain.exceptions.DomainException;
import com.antoniocmoura.pixbank.domain.validation.Error;
import com.antoniocmoura.pixbank.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(final ValidationHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public <T> T validate(final Validation<T> aValidation) {
        try {
            return aValidation.validate();
        } catch (final Exception ex) {
            throw DomainException.with(new Error(ex.getMessage()));
        }
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
