package com.hvs.webstore.back.domain.validation.exception;

import java.io.Serial;

public class NoStacktraceException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public NoStacktraceException(final String aMessage,
								 final Throwable aCause) {

		super(aMessage, aCause, true, false);
	}

	public NoStacktraceException(final String aMessage) {

		this(aMessage, null);
	}
}
