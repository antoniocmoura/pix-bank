package com.antoniocmoura.pixbank.domain.exceptions;

import com.antoniocmoura.pixbank.domain.AggregateRoot;
import com.antoniocmoura.pixbank.domain.Identifier;
import com.antoniocmoura.pixbank.domain.validation.Error;

import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException {

    protected NotFoundException(final String aMessage, final List<Error> anErrors) {
        super(aMessage, anErrors);
    }

    public static NotFoundException with(
            final Class<? extends AggregateRoot<?>> anAggregate,
            final Identifier id
    ) {
        final var anError = "%s with ID %s was not found".formatted(
                anAggregate.getSimpleName(),
                id.getValue()
        );
        return new NotFoundException(anError, Collections.emptyList());
    }
}

