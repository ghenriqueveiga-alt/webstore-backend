package com.hvs.webstore.back.domain.validation.notification;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.exception.DomainException;

import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {
	
	private final List<Erro> erros;
	
	private Notification(List<Erro> anErros) {

		this.erros = anErros;
	}
	
	public static Notification create() {

		return new Notification(new ArrayList<>());
	}
	
	public static Notification create(final Erro aErro) {

		return new Notification(new ArrayList<>()).append(aErro);
	}
	
	public static Notification create(final Throwable aThrowable) {

		return new Notification(new ArrayList<>()).append(new Erro(aThrowable.getMessage()));
	}

	@Override
	public Notification append(final Erro aErro) {

		this.erros.add(aErro);

		return this;
	}

	@Override
	public ValidationHandler append(ValidationHandler aHandler) {

		this.erros.addAll(aHandler.getErrors());

		return this;
	}

	@Override
	public ValidationHandler validate(Validation aValidation) {

		try {
			aValidation.validate();
		} catch (final DomainException e) {
			this.erros.addAll(e.getErros());
		} catch (final Throwable t) {
			this.erros.add(new Erro(t.getMessage()));
		}

		return this;
	}

	@Override
	public List<Erro> getErrors() {

		return this.erros;
	}
}
