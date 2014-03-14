package com.so.sofinances;
import java.text.NumberFormat;

public class Transaction implements Comparable<Transaction>{
	private TimeData timeOfTransaction;
	private double amount;
	private String name, category;
	private NumberFormat US = NumberFormat.getCurrencyInstance();
	
	private boolean isWithdrawal;
	public Transaction(TimeData timeOfTransaction, double amount,
			String name, String category, boolean isWithdrawal) {
		this.timeOfTransaction = timeOfTransaction;
		this.amount = amount;
		this.setWithdrawal(isWithdrawal);
		
		this.name = name;
		this.category = category;
	}
	
	@Override
	public int compareTo(Transaction t) {
		return t.getTimeOfTransaction().compareTo(this.getTimeOfTransaction());
	}
	
	@Override
	public String toString() {
		return category + ", " + name + ": " + US.format(amount);
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
