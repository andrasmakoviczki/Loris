package edu.elte.spring.loris.backend.util.exception;

public class ChannelException extends Exception {

	private static final long serialVersionUID = -5454648998900832573L;

	public ChannelException(String message) {
		super(message);
	}

	public ChannelException(Throwable throwable) {
		super(throwable);
	}

	public ChannelException(String message, Throwable throwable) {
		super(message, throwable);
	}

}