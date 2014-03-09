package com.so.sofinances;
import java.sql.Time;

public class Transaction {
	private Time timeEntered, timeOfTransaction;
	private double amount;
	
	private String name, category;
	
	private boolean isWithdrawal;
	
	public Transaction(Time timeEntered, Time timeOfTransaction, double amount,
			String name, String category, boolean isWithdrawal) {
		this.timeEntered = timeEntered;
		this.timeOfTransaction = timeOfTransaction;
		this.amount = amount;
		this.setWithdrawal(isWithdrawal);
		
		this.name = name;
		this.category = category;
	}

	/**
	 * @return the timeEntered
	 */
	public Time getTimeEntered() {
		return timeEntered;
	}

	/**
	 * @param timeEntered the timeEntered to set
	 */
	public void setTimeEntered(Time timeEntered) {
		this.timeEntered = timeEntered;
	}

	/**
	 * @return the timeOfTransaction
	 */
	public Time getTimeOfTransaction() {
		return timeOfTransaction;
	}

	/**
	 * @param timeOfTransaction the timeOfTransaction to set
	 */
	public void setTimeOfTransaction(Time timeOfTransaction) {
		this.timeOfTransaction = timeOfTransaction;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the isWithdrawal
	 */
	public boolean isWithdrawal() {
		return isWithdrawal;
	}

	/**
	 * @param isWithdrawal the isWithdrawal to set
	 */
	public void setWithdrawal(boolean isWithdrawal) {
		this.isWithdrawal = isWithdrawal;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
}
