package com.so.sofinances;

import com.db4o.ObjectSet;

public class LoginHandler {
	
	public static User checkLogin(String uName, String password){
		User ex = new User();
		ex.setUserName(uName);
		ex.setPassword(password);
		ObjectSet result = DBHandler.db().queryByExample(ex);
		if (result.hasNext()){
			return ex;
		} else {
			return null;
		}
	}
}
