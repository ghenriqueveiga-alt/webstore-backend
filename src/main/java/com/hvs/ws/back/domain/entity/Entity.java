package com.hvs.ws.back.domain.entity;

import com.hvs.ws.back.domain.validation.ValidationHandler;

import java.util.Objects;

public abstract class Entity<ID extends Identifier> {

	protected final ID id;

	public Entity(final ID id) {
		Objects.requireNonNull(id, "'id' should not be null");
		this.id = id;
	}
	
	public abstract void validate(ValidationHandler handler);

	public ID getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final var other = (Entity<?>) obj;
		return getId().equals(other.getId());
	}
	
}
