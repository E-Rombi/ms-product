package br.com.erombi.product.domain.category;

import br.com.erombi.product.domain.category.Category;
import br.com.erombi.product.domain.validation.Error;
import br.com.erombi.product.domain.validation.ValidationHandler;
import br.com.erombi.product.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        if (this.category.getName() == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
        }
    }
}
