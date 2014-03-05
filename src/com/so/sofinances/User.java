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
	
	public boolean addAccount(String fName, String dName, double bal, double intRate){
		if (isValid(fName) && isValid(dName)){
			this.accounts.add(new Account(fName, dName, bal, intRate));
			return true;
		}
		return false;
		
	}
	
	public boolean isValid(String ex){
		return ex.matches("([A-Za-z0-9]).*");
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
	
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	public String accToString(){
		String ret = "";
		for (Account a : accounts){
			ret += a.getFullName() + " ";
		}
		return ret;
	}
	
}
