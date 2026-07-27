package com.hvs.webstore.back.domain.validation;

import com.hvs.webstore.back.domain.validation.exception.DomainException;
import com.hvs.webstore.back.domain.validation.notification.Erro;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

	@Override
	public ValidationHandler append(final Erro aErro) {

		throw DomainException.with(aErro);
	}

	@Override
	public ValidationHandler append(final ValidationHandler aHandler) {

		throw DomainException.with(aHandler.getErrors());
	}

	@Override
	public ValidationHandler validate(final Validation aValidation) {

		try {
			aValidation.validate();
		}catch(final Exception e ) {
			throw DomainException.with(List.of(new Erro(e.getMessage())));
		}

		return this;
	}

	@Override
	public List<Erro> getErrors() {

		return List.of();
	}
}
