package br.com.erombi.product.application.category.create;

import br.com.erombi.product.application.UseCase;
import br.com.erombi.product.domain.validation.Error;
import io.vavr.control.Either;

import java.util.List;


public abstract class CreateCategoryUseCase
        extends UseCase<CreateCategoryCommand, Either<List<Error>, CreateCategoryOutput>> {
}
