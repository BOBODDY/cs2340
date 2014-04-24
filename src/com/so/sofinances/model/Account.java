package com.so.sofinances.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.so.sofinances.utilities.AdapterBuilder;

import android.content.Context;
import android.widget.SimpleAdapter;


/**
 * @author kodyPC
 * Represents a financial account within the user. 
 * 
 * Contains full name, display name, balance, 
 * monthly interest rate, and list of transactions.
 */
public class Account {
    /**
     * full name of the account.
     */
    private String fullName;
    /**
     * account's displayname.
     */
    private String displayName;
    /**
     * account's balance.
     */
    private double balance;
    
    private final double INITIAL_BALANCE;
    /**
     * account's monthly interest rate.
     */
    private double interestRate;
    /**
     * account's list of transactions.
     */
    private List<Transaction> transactions = new ArrayList<Transaction>();
    
    
    /**
     * @param fullName the full name of the account
     * @param displayName the name displayed that represents the account
     * @param balance the current balance of the account
     * @param interestRate monthly interest rate of the account. not used currently.
     */
    public Account(String fullName, String displayName, double balance,
            double interestRate) {
    	transactions = new ArrayList<Transaction>();
    	this.INITIAL_BALANCE = balance;
        this.fullName = fullName;
        this.displayName = displayName;
        this.balance = balance;
        this.interestRate = interestRate;
    }

    /**
     * Used to create an account with no interest rate or balance.
     * 
     * @param fullName the full name
     * @param displayName  the displayname
     */
    public Account(String fullName, String displayName) {
        this(fullName, displayName, 0, 0);
    }
    
    /**
     * Used to create an account without an interest rate.
     * 
     * @param fullName the full name
     * @param displayName the display name
     * @param interestRate the interest rate
     */
    public Account(String fullName, String displayName, double interestRate) {
        this(fullName, displayName, 0, interestRate);
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
    
    /** returns string of the balance.
     * @return the balance as a string
     */
    public String getBalanceString() {
        return Currency.format(balance);
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
        return interestRate;
    }

    /**
     * @param interestRate the monthlyInterestRate to set
     */
    public void setMonthlyInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * @return the transactions
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * @param transactions the transactions to set
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    
    /** 
     * adds a transaction to the list and updates the balance.
     * 
     * @param transact    a new transaction to add
     * @return    true if added successfully
     */
    public boolean addTransaction(Transaction transact) {
    	if (transact != null) {
    		balance += transact.getAmount();
    		return transactions.add(transact);
    	}
        return false;
    }
    
    public boolean hasTransactions() {
    	return transactions != null && !transactions.isEmpty();
    }
    
    public SimpleAdapter buildTransactionList(Context c) {
    	return AdapterBuilder.buildAdapter(transactions, c);
    }
    
    public void removeTransactionByIndex(int index) {
    	Transaction t = transactions.get(index);
    	if (t != null) {
	    	transactions.remove(index);
	    	balance -= t.getAmount();
    	}
    }
    
    public SimpleAdapter sortTransByName(Context c) {
    	Collections.sort(transactions, new Comparator<Transaction>() {

			@Override
			public int compare(Transaction t1, Transaction t2) {
				return t1.getName().compareTo(t2.getName());
			}
    		
    	});
    	return AdapterBuilder.buildAdapter(transactions, c);
    }
    
    public SimpleAdapter sortTransByDate(Context c) {
    	Collections.sort(transactions);
    	return AdapterBuilder.buildAdapter(transactions, c);
    }
    
    public SimpleAdapter sortTransByAmount(Context c) {
    	Collections.sort(transactions, new Comparator<Transaction>() {
			@Override
			public int compare(Transaction t1, Transaction t2) {
				double amt1 = t1.getAmount();
				double amt2 = t2.getAmount();
				if (amt1 < amt2) {
					return 1;
				} else if (amt1 > amt2) {
					return -1;
				} else {
					return 0;
				}
			}
    	});
    	return AdapterBuilder.buildAdapter(transactions, c);
    }
}
