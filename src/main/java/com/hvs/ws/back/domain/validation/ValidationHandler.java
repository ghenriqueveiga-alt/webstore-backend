package com.hvs.ws.back.domain.validation;

import com.hvs.ws.back.domain.validation.notification.Erro;

import java.util.List;

public interface ValidationHandler {

	ValidationHandler append(Erro erro);
	
	ValidationHandler append(ValidationHandler handler);
	
	ValidationHandler validate(Validation validation);
	
	List<Erro> getErrors();
	
	default boolean hasError() {

		return getErrors() != null && !getErrors().isEmpty();
	}
	
	default Erro firstError() {

		if (getErrors() != null && !getErrors().isEmpty())
			return getErrors().get(0);

		return null;
	}
	
	interface Validation {

		void validate();
	}
}
