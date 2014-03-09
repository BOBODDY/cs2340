package com.so.sofinances;
import java.sql.Time;
import java.text.NumberFormat;

public abstract class Transaction {
	private TimeData timeEntered, timeOfTransaction;
	private double amount;
	private String name, category;
	private NumberFormat US = NumberFormat.getCurrencyInstance();
	
	public Transaction(TimeData timeEntered, TimeData timeOfTransaction, double amount,
			String name, String category) {
		this.timeEntered = timeEntered;
		this.timeOfTransaction = timeOfTransaction;
		this.amount = amount;
		this.name = name;
		this.category = category;
	}
	
	@Override
	public String toString() {
		return category + ", " + name + ": " + US.format(amount);
	}
	
	/**
	 * @return the timeEntered
	 */
	public TimeData getTimeEntered() {
		return timeEntered;
	}
	
	/**
	 * 
	 * @return	the name of the transaction
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param timeEntered the timeEntered to set
	 */
	public void setTimeEntered(TimeData timeEntered) {
		this.timeEntered = timeEntered;
	}

	/**
	 * @return the timeOfTransaction
	 */
	public TimeData getTimeOfTransaction() {
		return timeOfTransaction;
	}

	/**
	 * @param timeOfTransaction the timeOfTransaction to set
	 */
	public void setTimeOfTransaction(TimeData timeOfTransaction) {
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
}
