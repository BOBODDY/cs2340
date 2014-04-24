package com.so.sofinances.exceptions;

public class AccountNotFoundException extends Exception {
	public AccountNotFoundException() {
		super("Account not Found");
	}
}
