package com.daw.services.exceptions;

public class JuegoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1312676328759028216L;

	public JuegoNotFoundException(String message) {
		super(message);
	}

}
