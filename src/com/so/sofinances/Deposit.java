package com.so.sofinances;

import java.sql.Time;

public class Deposit extends Transaction {
	
	String name;

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

	public Deposit(Time timeEntered, Time timeOfTransaction, double amount,
			String name, String category) {
		super(timeEntered, timeOfTransaction, amount, name, category);
		// TODO Auto-generated constructor stub
	}

}
