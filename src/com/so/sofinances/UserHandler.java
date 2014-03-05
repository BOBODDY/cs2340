package com.so.sofinances;

import com.db4o.ObjectSet;

public class UserHandler {
	private static String username;
	
	public static User getCU(){
		User temp = new User();
		temp.setUserName(username);
		ObjectSet result = DBHandler.db().queryByExample(temp);
		return (User) result.next();
	}
	
	public static String getCurrentUsername(){
		return username;
	}
	
	public static void setCurrentUsername(String uN){
		username = uN;
	}
}
