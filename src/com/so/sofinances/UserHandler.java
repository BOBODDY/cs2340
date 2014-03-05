package com.so.sofinances;

import com.db4o.ObjectSet;

public class UserHandler {
	private static String username;
	public static User currentUser;
	
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
			System.out.println("no result found when searching for user");
		}
	}
}
