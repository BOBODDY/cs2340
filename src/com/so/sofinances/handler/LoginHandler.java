package com.so.sofinances.handler;

import com.db4o.ObjectSet;
import com.so.sofinances.model.User;

public class LoginHandler {
	
	public static User checkLogin(String uName, String password){
		User ex = new User();
		ex.setUserName(uName);
		ex.setPassword(password);
		ObjectSet<Object> result = DBHandler.db().queryByExample(ex);
		if (result.hasNext()){
			return ex;
		} else {
			return null;
		}
	}
}
