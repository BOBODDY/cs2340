package com.so.sofinances.exceptions;

public class PasswordMismatchException extends InvalidInputException {
	public PasswordMismatchException(String hint) {
		super("Incorrect Password. " + hint);
	}
	public PasswordMismatchException() {
		super("Incorrect Password.");
	}
}
