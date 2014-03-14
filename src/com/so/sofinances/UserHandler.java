package com.so.sofinances;

import java.util.ArrayList;

import android.util.Log;

import com.db4o.ObjectSet;

public class UserHandler {
	private static String username;
	private static User currentUser;
	
	public static User getCU(){
		return currentUser;
	}
	
	public static String getCurrentUsername(){
		return username;
	}
	
	public static void setCurrentUsername(String uN){
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
		return getCU().addAccount(fName, dName, bal, intRate);
	}
	
	public static ArrayList<Account> getAccounts() {
		return getCU().getAccounts();
	}
	
	public static Account getAccount(String accountName) {
		return getCU().getAccount(accountName);
	}
	
	public static String accToString(){
		return getCU().accToString();
	}
	
	public static String getUserName() {
		return getCU().getUserName();
	}
}
