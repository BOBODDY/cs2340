package com.so.sofinances;

import java.util.ArrayList;

public class Account {
	private String fullName, displayName;
	private double balance, monthlyInterestRate;
	private ArrayList<Transaction> transactions;
	
	
	public Account(String fullName, String displayName, double balance,
			double monthlyInterestRate) {
		this.fullName = fullName;
		this.displayName = displayName;
		this.balance = balance;
		this.monthlyInterestRate = monthlyInterestRate;
	}

	public Account(String fullName, String displayName){
		this.fullName = fullName;
		this.displayName = displayName;
		this.setTransactions(new ArrayList<Transaction>());
	}
	
	public Account(String fullName, String displayName, double interestRate){
		this.fullName = fullName;
		this.displayName = displayName;
		this.monthlyInterestRate = interestRate;
		this.setTransactions(new ArrayList<Transaction>());
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
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the monthlyInterestRate
	 */
	public double getMonthlyInterestRate() {
		return monthlyInterestRate;
	}

	/**
	 * @param monthlyInterestRate the monthlyInterestRate to set
	 */
	public void setMonthlyInterestRate(double monthlyInterestRate) {
		this.monthlyInterestRate = monthlyInterestRate;
	}

	/**
	 * @return the transactions
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
