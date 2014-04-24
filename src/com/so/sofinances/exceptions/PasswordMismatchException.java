package com.so.sofinances.exceptions;

public class PasswordMismatchException extends InvalidInputException {
	public PasswordMismatchException() {
		super("Invalid Password");
	}
}
