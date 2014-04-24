package com.so.sofinances.model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.SimpleAdapter;

import com.so.sofinances.utilities.AdapterBuilder;

/**
 * Represents a login for the application.
 *
 * @author  Joseph Rossi
 * @version 1.0 4/3/2014
 */
public class User {
    /**
     * Text representing User's full name.
     */
    private String fullName;
    /**
     * represents User's username.
     */
    private String userName;
    /**
     * Represents user's password.
     */
    private String password;

    /**
     * A List of financial accounts associated with this User.
     */
    private List<Account> accounts;

    /**
     * Empty Constructor.
     */
    public User() { }

    /**
     * Creates a User with given text information and instantiates the acct list.
     *
     * @param fullName    the User's full name
     * @param userName    the unique username
     * @param password    the password
     */
    public User(String fullName, String userName, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        
        this.accounts = new ArrayList<Account>();
    }

    /**
     * Creates a User with the given username (for persistence purposes).
     *
     * @param uName the User's username
     */
    public User(String uName) {
        this.userName = uName;
    }

    /**
     * Validates a new account and add's it to the User's list.
     *
     * @param fName the name of the account
     * @param dName the abbrev. display name of the account
     * @param bal   the beginning balance in the account
     * @param intRate   the account's interest rate
     * @return  true if the account is added, false if info is invalid
     */
    public boolean addAccount(String fName, String dName, double bal,
                                double intRate) {
    	boolean result = false;
    	
        if (isValid(fName) && isValid(dName)) {
            this.accounts.add(new Account(fName, dName, bal, intRate));
            result = true;
        }
        return result;

    }

    /**
     * Checks for valid account names with regex.
     *
     * @param ex    the expression to be checked
     * @return  true if ex is a valid name, false otherwise
     */
    public boolean isValid(String ex) {
    	return ex.matches("([A-Za-z0-9]).*");
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
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return  the User's account list
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Finds a User's account based on the name.
     *
     * @param accountName   the acct name being searched for
     * @return  the Account if a match is found, otherwise null
     */
    public Account getAccount(String accountName) {
        Account acc = null;

        for (int i = 0; i < accounts.size(); i++) {
            Account tmp = accounts.get(i);
            if (tmp.getDisplayName().equals(accountName)) {
                acc = tmp;
                break;
            }
        }

        return acc;
    }

    /**
     * @return  a String representation of the User's account list
     */
    public String accToString() {
        String ret = "";
        for (Account acc : accounts) {
            ret += acc.getFullName() + " ";
        }
        return ret;
    }

    public SimpleAdapter buildAccountList(Context c) {
    	return AdapterBuilder.buildAdapter(accounts, c);
    }
    
    public boolean hasAccounts() {
    	return accounts != null && !accounts.isEmpty();
    }

	public Account getAccountAt(int position) {
		return accounts.get(position);
	}

	public void adjustAmounts(double exchangeRate) {
		for (Account a : accounts) {
			a.adjustAmounts(exchangeRate);
		}
	}
}
