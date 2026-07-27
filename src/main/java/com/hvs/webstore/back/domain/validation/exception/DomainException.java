package com.hvs.webstore.back.domain.validation.exception;

import com.hvs.webstore.back.domain.validation.notification.Erro;

import java.io.Serial;
import java.util.List;

public class DomainException extends NoStacktraceException {

	@Serial
	private static final long serialVersionUID = 1L;
    private final List<Erro> erros;
	
	private DomainException(final String aMessage,
							final List<Erro> anErros) {

		super(aMessage);
		this.erros = anErros;
	}
	
	public static DomainException with(final Erro anErrors) {

		return new DomainException(anErrors.message(), List.of(anErrors));
	}
	
	public static DomainException with(final List<Erro> anErros) {

		return new DomainException("", anErros);
	}

    public List<Erro> getErros() {

		return erros;
    }
}
