package com.so.sofinances.handler;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.SimpleAdapter;

import com.db4o.ObjectSet;
import com.so.sofinances.model.Account;
import com.so.sofinances.model.User;

/** Facilitates interaction between the application and users.
 * 
 * @author kodyPC
 *
 */
public class UserHandler {
    
	/**
	 * the current username.
	 */
    private static String username;
    /**
     * the current user.
     */
    private static User currentUser;
    
    /**
     * @return the current user
     */
    public static User getCU() {
        return currentUser;
    }
    
    /**
     * @return the current username
     */
    public static String getCurrentUsername() {
        return username;
    }
    
    /**Sets the current username. 
     * 
     * @param userName the username needing to be set
     */
    public static void setCurrentUser(String userName) {
        username = userName;
        User temp = new User();
        temp.setUserName(userName);
        ObjectSet<Object> res = DBHandler.db().queryByExample(temp);
        if (res.hasNext()) {
            currentUser = (User) res.next();
        } else {
            Log.d("com.so.sofinances", "no result found when searching for user");
        }
    }
    
    /** Relays account information to the User method addAccount().
     * 
     * @param fName full name of the account
     * @param dName display name of the account
     * @param bal initial balance of the account
     * @param intRate interest rate of the account
     * @return true if account added successfully
     */
    public static boolean addAccount(String fName, String dName, double bal, double intRate) {
        return currentUser.addAccount(fName, dName, bal, intRate);
    }
    
    /**
     * @return the current user's account as an arraylist
     */
    public static List<Account> getAccounts() {
        return currentUser.getAccounts();
    }
    
    /**
     * @param accountName the name of the desired account
     * @return the desired account from the current user
     */
    public static Account getAccount(String accountName) {
        return currentUser.getAccount(accountName);
    }
    
    /**
     * @return a string representation of the user's accounts
     */
    public static String accToString() {
        return currentUser.accToString();
    }
    
    /**
     * @return the current user's username
     */
    public static String getUserName() {
        return currentUser.getUserName();
    }
    
    /**
     * 
     * @return the current user's full name
     */
    public static String getFullName() {
    	return currentUser.getFullName();
    }
    
    /** Clears the current user.
     * 
     */
    public static void clear() {
        currentUser = null;
    }
    
    public static SimpleAdapter buildList(Context c) {
    	return currentUser.buildAccountList(c);
    }
    
    public static boolean hasAccounts() {
    	return currentUser.hasAccounts();
    }
}
