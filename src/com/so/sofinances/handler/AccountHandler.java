package com.so.sofinances.handler;

import java.util.List;

import android.content.Context;
import android.widget.SimpleAdapter;

import com.so.sofinances.model.Account;
import com.so.sofinances.model.Transaction;

/**
 * Contains the user's currently selected account and provides wrapper methods
 * that interfaces between the presenter/UI and the domain model.
 *
 * @author Joseph Rossi
 * @version 1.0 4/3/2014
 */
public class AccountHandler {

    /**
     * the name of the current account.
     */
    private static String accountName;
    /**
     * the actual current account.
     */
    private static Account currentAccount;

    /**
     * Validates the name of the account received from the UI and updates
     * current account information.
     *
     * @param accName   the text account name grabbed from the UI
     */
    public static void setCurrentAccount(String accName) {
        if (accName != null && !accName.equals("")) {
            accountName = accName;
            currentAccount = UserHandler.getAccount(accountName);
        }

    }

    /**
     * Resets current account information.
     */
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

    /**
     * Wrapper for the UI to pull the account's balance as formatted text.
     *
     * @return  balance formatted as currency
     */
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
     * @param interestRate the monthlyInterestRate to set
     */
    public static void setMonthlyInterestRate(double interestRate) {
        currentAccount.setMonthlyInterestRate(interestRate);
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
    public static void setTransactions(List<Transaction> transactions) {
        currentAccount.setTransactions(transactions);
    }

    /**
     *
     * @param transact    a new transaction to add
     * @return    true if added successfully
     */
    public static boolean addTransaction(Transaction transact) {
        return currentAccount.addTransaction(transact);
    }
    
    public static boolean hasTransactions() {
    	return currentAccount.hasTransactions();
    }
    
    public static SimpleAdapter buildList(Context c) {
    	return currentAccount.buildTransactionList(c);
    }
    
    public static void removeTransactionByIndex(int index) {
    	currentAccount.removeTransactionByIndex(index);
    }
    
    public static SimpleAdapter sortByDate(Context c) {
    	return currentAccount.sortTransByDate(c);
    }
}
