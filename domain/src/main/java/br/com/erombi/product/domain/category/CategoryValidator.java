package br.com.erombi.product.domain.category;

import br.com.erombi.product.domain.category.Category;
import br.com.erombi.product.domain.validation.Error;
import br.com.erombi.product.domain.validation.ValidationHandler;
import br.com.erombi.product.domain.validation.Validator;

public class CategoryValidator extends Validator {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int DESCRIPTION_MAX_LENGTH = 500;
    public static final int NAME_MIN_LENGTH = 3;
    public static final int DESCRIPTION_MIN_LENGTH = 10;
    private final Category category;

    public CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkDescriptionConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.category.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }

    private void checkDescriptionConstraints() {
        final var name = this.category.getDescription();
        if (name == null) {
            this.validationHandler().append(new Error("'description' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'description' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > DESCRIPTION_MAX_LENGTH || length < DESCRIPTION_MIN_LENGTH) {
            this.validationHandler().append(new Error("'description' must be between 10 and 500 characters"));
        }
    }
}
