package com.so.sofinances.handler;

import java.util.ArrayList;

import android.util.Log;

import com.db4o.ObjectSet;
import com.so.sofinances.model.Account;
import com.so.sofinances.model.User;

public class UserHandler {
	private static String username;
	private static User currentUser;
	
	public static User getCU(){
		return currentUser;
	}
	
	public static String getCurrentUsername(){
		return username;
	}
	
	public static void setCurrentUser(String uN){
		username = uN;
		User temp = new User();
		temp.setUserName(uN);
		ObjectSet<Object> res = DBHandler.db().queryByExample(temp);
		if (res.hasNext()){
			currentUser = (User) res.next();
		} else {
			Log.d("com.so.sofinances", "no result found when searching for user");
		}
	}
	
	public static boolean addAccount(String fName, String dName, double bal, double intRate) {
		return currentUser.addAccount(fName, dName, bal, intRate);
	}
	
	public static ArrayList<Account> getAccounts() {
		return currentUser.getAccounts();
	}
	
	public static Account getAccount(String accountName) {
		return currentUser.getAccount(accountName);
	}
	
	public static String accToString(){
		return currentUser.accToString();
	}
	
	public static String getUserName() {
		return currentUser.getUserName();
	}
	
	public static void clear() {
		currentUser = null;
	}
}
