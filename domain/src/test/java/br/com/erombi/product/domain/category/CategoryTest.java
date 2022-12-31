package br.com.erombi.product.domain.category;

import br.com.erombi.product.domain.exceptions.DomainException;
import br.com.erombi.product.domain.validation.handler.ThrowsValidationsHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    public void givenAValidParam_whenCallNewCategory_thenInstantiateACategory() {
        final var expectedName = "Copos";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedIsActive);

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = null;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedIsActive);

        final var err = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationsHandler()));

        Assertions.assertEquals(expectedErrorCount, err.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, err.getErrors().get(0).message());
    }

}