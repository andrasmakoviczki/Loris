package edu.elte.spring.loris.backend.util.exception;

public class UserException extends Exception {

	private static final long serialVersionUID = -4454311035137277947L;

	public UserException(String message) {
		super(message);
	}

	public UserException(Throwable throwable) {
		super(throwable);
	}

	public UserException(String message, Throwable throwable) {
		super(message, throwable);
	}

}