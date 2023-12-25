package dev.mrkevr.bankingapplication.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8150148223228725818L;

	public UserNotFoundException() {
		super("User not found");
	}

	public UserNotFoundException(Long id) {
		super("User not found with id: " + id);
	}

	public UserNotFoundException(String message) {
		super(message);
	}
}
