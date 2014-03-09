package com.so.sofinances;

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
		ObjectSet res = DBHandler.db().queryByExample(temp);
		if (res.hasNext()){
			currentUser = (User) res.next();
		} else {
			Log.d("com.so.sofinances", "no result found when searching for user");
		}
	}
}
