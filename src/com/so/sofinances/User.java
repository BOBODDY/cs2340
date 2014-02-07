package com.so.sofinances;

public class User {
	private String username, password;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public boolean isDefault() {
		return username.equalsIgnoreCase("admin")
				&& password.equals("pass123");
	}
}
