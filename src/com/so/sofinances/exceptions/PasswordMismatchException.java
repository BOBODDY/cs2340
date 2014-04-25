package com.so.sofinances.exceptions;

import com.so.sofinances.controllers.UserHandler;

public class PasswordMismatchException extends InvalidInputException {
	public PasswordMismatchException() {
		super("Invalid Password");
	}
	public PasswordMismatchException(String hint) {
		super("Invalid Password. Hint: " + hint);
	}
}
