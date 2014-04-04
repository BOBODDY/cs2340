package com.so.sofinances.model;

import java.util.List;
import java.util.ArrayList;

public class User {
    private String fullName;
    private String userName; 
    private String password;
    private List<Account> accounts;
    
    /**
     * Empty Constructor
     */
    public User() { }
    
    public User(String firstName, String userName, String password)  {
        this.fullName = firstName;
        this.userName = userName;
        this.password = password;
        this.accounts = new ArrayList<Account>();
    }

    public User(String uName) {
        this.userName = uName;
    }
    
    public boolean addAccount(String fName, String dName, double bal, double intRate) {
    	boolean result = false;
    	
        if (isValid(fName) && isValid(dName)) {
            this.accounts.add(new Account(fName, dName, bal, intRate));
            result = true;
        }
        return result;
        
    }
    
    public boolean isValid(String str) {
        return str.matches("([A-Za-z0-9]).*");
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
    
    public List<Account> getAccounts() {
        return accounts;
    }
    
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
    
    public String accToString() {
        String ret = "";
        for (Account acc : accounts) {
            ret += acc.getFullName() + " ";
        }
        return ret;
    }
    
}
