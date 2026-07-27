package com.hvs.webstore.back.domain;

import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public abstract class Entity<ID extends Identifier> {

    protected final ID id;

	protected Entity(final ID id) {

		Objects.requireNonNull(id, "'id' should not be null");
		this.id = id;
	}

    public ID getId() {

        return id;
    }

    public abstract void validate(ValidationHandler aHandler);

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;
        Entity<?> entity = (Entity<?>) o;

        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(id);
    }
}
