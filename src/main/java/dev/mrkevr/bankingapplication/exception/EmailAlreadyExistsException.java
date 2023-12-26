package dev.mrkevr.bankingapplication.exception;

public class EmailAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 4690182040586590141L;

	public EmailAlreadyExistsException() {
		super("Email already exists");
	}

	public EmailAlreadyExistsException(String email) {
		super("User already exists with that email: " + email);
	}
}
