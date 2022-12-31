package br.com.erombi.product.domain.validation.handler;

import br.com.erombi.product.domain.exceptions.DomainException;
import br.com.erombi.product.domain.validation.Error;
import br.com.erombi.product.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationsHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error anError) {
        throw DomainException.with(List.of(anError));
    }

    @Override
    public ValidationHandler append(final ValidationHandler aHandler) {
        throw DomainException.with(aHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(final Validation aValidation) {
        try {
            aValidation.validate();
        } catch (final Exception ex) {
            throw DomainException.with(new Error(ex.getMessage()));
        }

        return this;
    }

    @Override
    public List<Error> getErrors() {
        return null;
    }
}
