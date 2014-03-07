package com.so.sofinances;

import java.sql.Time;

public class Withdrawal extends Transaction {
	
	String category;

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

	public Withdrawal(Time timeEntered, Time timeOfTransaction, double amount,
			String name, String category) {
		super(timeEntered, timeOfTransaction, amount * -1, name, category);
		// TODO Auto-generated constructor stub
	}

}
