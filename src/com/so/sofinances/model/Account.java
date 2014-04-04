package com.so.sofinances.model;

import java.util.ArrayList;
import java.util.List;


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
        this.fullName = fullName;
        this.displayName = displayName;
    }
    
    /**
     * Used to create an account without an interest rate.
     * 
     * @param fullName the full name
     * @param displayName the display name
     * @param interestRate the interest rate
     */
    public Account(String fullName, String displayName, double interestRate) {
        this.fullName = fullName;
        this.displayName = displayName;
        this.interestRate = interestRate;
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
        balance += transact.getAmount();
        //System.out.println("Added");
        return transactions.add(transact);
    }
    
}
