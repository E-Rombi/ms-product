package br.com.erombi.product.application.category.create;

import br.com.erombi.product.domain.validation.Error;
import io.vavr.control.Either;

import java.util.List;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    @Override
    public Either<List<Error>, CreateCategoryOutput> execute(final CreateCategoryCommand anCommand) {
        return null;
    }
}
