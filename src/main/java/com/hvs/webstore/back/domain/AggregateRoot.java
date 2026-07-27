package com.hvs.webstore.back.domain;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {

	protected AggregateRoot(final ID id) {

		super(id);
	}
}
