package com.so.sofinances;

import java.util.ArrayList;
import java.util.List;

public class AccountHandler {
	private static String accountName;
	private static Account currentAccount;

	public static void setCurrentAccount(String accName) {
		if (accName != null && !accName.equals("")) {
			accountName = accName;
			currentAccount = UserHandler.getAccount(accountName);
		}
		
	}
	
	public static void clear() {
		currentAccount = null;
		accountName = null;
	}
	/**
	 * @return the fullName
	 */
	public static String getFullName() {
		return currentAccount.getFullName();
	}

	/**
	 * @param fullName the fullName to set
	 */
	public static void setFullName(String fullName) {
		currentAccount.setFullName(fullName);
	}

	/**
	 * @return the displayName
	 */
	public static String getDisplayName() {
		return currentAccount.getDisplayName();
	}

	/**
	 * @param displayName the displayName to set
	 */
	public static void setDisplayName(String displayName) {
		currentAccount.setDisplayName(displayName);
	}

	/**
	 * @return the balance
	 */
	public static double getBalance() {
		return currentAccount.getBalance();
	}
	
	public static String getBalanceString() {
		return currentAccount.getBalanceString();
	}

	/**
	 * @param balance the balance to set
	 */
	public static void setBalance(double balance) {
		currentAccount.setBalance(balance);
	}

	/**
	 * @return the monthlyInterestRate
	 */
	public static double getMonthlyInterestRate() {
		return currentAccount.getMonthlyInterestRate();
	}

	/**
	 * @param monthlyInterestRate the monthlyInterestRate to set
	 */
	public static void setMonthlyInterestRate(double monthlyInterestRate) {
		currentAccount.setMonthlyInterestRate(monthlyInterestRate);
	}

	/**
	 * @return the transactions
	 */
	public static List<Transaction> getTransactions() {
		return currentAccount.getTransactions();
	}

	/**
	 * @param transactions the transactions to set
	 */
	public static void setTransactions(ArrayList<Transaction> transactions) {
		currentAccount.setTransactions(transactions);
	}
	
	/**
	 * 
	 * @param t	a new transaction to add
	 * @return	true if added successfully
	 */
	public static boolean addTransaction(Transaction t) {
		return currentAccount.addTransaction(t);
	}
}
