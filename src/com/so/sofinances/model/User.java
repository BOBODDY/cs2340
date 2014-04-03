package com.so.sofinances.model;

import java.util.List;
import java.util.ArrayList;

public class User {
    private String fullName;
    private String userName; 
    private String password;
    private List<Account> accounts;
    
    public User() { }
    
    public User(String fN, String uN, String pW)  {
        this.fullName = fN;
        this.userName = uN;
        this.password = pW;
        this.accounts = new ArrayList<Account>();
    }

    public User(String uName) {
        this.userName = uName;
    }
    
    public boolean addAccount(String fName, String dName, double bal, double intRate) {
        if (isValid(fName) && isValid(dName)) {
            this.accounts.add(new Account(fName, dName, bal, intRate));
            return true;
        }
        return false;
        
    }
    
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
        for (Account a : accounts) {
            ret += a.getFullName() + " ";
        }
        return ret;
    }
    
}
