package br.com.erombi.product.domain.category;

import br.com.erombi.product.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    public void givenAValidParam_whenCallNewCategory_thenInstantiateACategory() {
        final var expectedName = "Copos";
        final var expectedDescription = "Copo descartável";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = null;
        final String expectedDescription =  "Description not empty";
        final var expectedErrorMessage = "'name' should not be null or blank";
        final var expectedErrorCount = 1;
        final var expectedIsActive = true;

        final var err = Assertions.assertThrows(DomainException.class, () ->
                Category.newCategory(expectedName, expectedDescription, expectedIsActive)
        );

        Assertions.assertEquals(expectedErrorCount, err.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, err.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = "";
        final String expectedDescription =  "Description not empty";
        final var expectedErrorMessage = "'name' should not be null or blank";
        final var expectedErrorCount = 1;
        final var expectedIsActive = true;

        final var err = Assertions.assertThrows(DomainException.class, () ->
                Category.newCategory(expectedName, expectedDescription, expectedIsActive)
        );

        Assertions.assertEquals(expectedErrorCount, err.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, err.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNullDescription_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = "Copo descartável";
        final String expectedDescription =  null;
        final var expectedErrorMessage = "'description' should not be null or blank";
        final var expectedErrorCount = 1;
        final var expectedIsActive = true;

        final var err = Assertions.assertThrows(DomainException.class, () ->
                Category.newCategory(expectedName, expectedDescription, expectedIsActive)
        );

        Assertions.assertEquals(expectedErrorCount, err.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, err.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = "Copo descartável";
        final String expectedDescription =  "";
        final var expectedErrorMessage = "'description' should not be null or blank";
        final var expectedErrorCount = 1;
        final var expectedIsActive = true;

        final var err = Assertions.assertThrows(DomainException.class, () ->
                Category.newCategory(expectedName, expectedDescription, expectedIsActive)
        );

        Assertions.assertEquals(expectedErrorCount, err.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, err.getErrors().get(0).message());
    }

    @Test
    public void givenAValidCategory_whenCallInactive_thenShouldInactiveSuccessfully() {
        final var expectedName = "Copos";
        final var expectedDescription = "Copo descartável";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var updatedAtAfterCreation =  actualCategory.getUpdatedAt();

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertTrue(actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(updatedAtAfterCreation);
        Assertions.assertNull(actualCategory.getDeletedAt());

        actualCategory.deactivate();

        Assertions.assertFalse(actualCategory.isActive());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAtAfterCreation));
    }

    @Test
    public void givenAnInactiveCategory_whenActivate_thenShouldClearDeletedAt() {
        final var expectedName = "Copos";
        final var expectedDescription = "Copo descartável";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var updatedAtAfterCreation =  actualCategory.getUpdatedAt();

        actualCategory.deactivate();

        final var updatedAtAfterDeactivate = actualCategory.getUpdatedAt();

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertFalse(actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(updatedAtAfterCreation);
        Assertions.assertNotNull(actualCategory.getDeletedAt());
        Assertions.assertTrue(updatedAtAfterDeactivate.isAfter(updatedAtAfterCreation));

        actualCategory.activate();

        Assertions.assertTrue(actualCategory.isActive());
        Assertions.assertNull(actualCategory.getDeletedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAtAfterDeactivate));
    }

}