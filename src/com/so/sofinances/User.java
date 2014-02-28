package com.so.sofinances;

import java.util.ArrayList;

public class User {
	private String fullName, userName, password;
	private ArrayList<Account> accounts;
	
	public User(String fN, String uN, String pW){
		this.fullName = fN;
		this.userName = uN;
		this.password = pW;
		this.accounts = new ArrayList<Account>();
	}

	public User() {
	}
	
	public User(String uName){
		this.userName = uName;
	}
	
	public User addAccount(String fName, String dName, double bal, double intRate){
		this.accounts.add(new Account(fName, dName, bal, intRate));
		return this;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String accToString(){
		String ret = "";
		for (Account a : accounts){
			System.out.println(a.getFullName());
			ret += a.getFullName() + " ";
		}
		return ret;
	}
	
}
