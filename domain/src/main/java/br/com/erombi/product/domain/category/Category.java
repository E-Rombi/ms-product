package br.com.erombi.product.domain.category;

import br.com.erombi.product.domain.AggregateRoot;
import br.com.erombi.product.domain.exceptions.DomainException;
import br.com.erombi.product.domain.validation.Error;

import java.time.Instant;
import java.util.ArrayList;

public class Category extends AggregateRoot<CategoryID> {
    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(
            final CategoryID anId,
            final String aName,
            final String aDescription,
            final boolean isActive,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeleteDate
    ) {
        super(anId);
        this.name = aName;
        this.description = aDescription;
        this.active = isActive;
        this.createdAt = aCreationDate;
        this.updatedAt = aUpdateDate;
        this.deletedAt = aDeleteDate;
        validate();
    }

    public static Category newCategory(final String aName, final String aDescription, final boolean isActive) {
        final var id = CategoryID.unique();
        final var now = Instant.now();

        return new Category(id, aName, aDescription, isActive, now, now, null);
    }

    @Override
    public void validate() {
        var errors = new ArrayList<Error>();

        if (this.name == null || this.name.isBlank()) {
            errors.add(new Error("'name' should not be null or blank"));
        }
        if (this.description == null || this.description.isBlank()) {
            errors.add(new Error("'description' should not be null or blank"));
        }

        if (!errors.isEmpty()) {
            throw DomainException.with(errors);
        }
    }

    public CategoryID getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }

        this.active = false;
        this.updatedAt = Instant.now();
    }

    public void activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = Instant.now();
    }
}
