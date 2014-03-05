package com.so.sofinances;

import com.db4o.ObjectSet;

public class RegistrationHandler {
	
	public static boolean createUser(String fN, String uN, String pW){
		if (isValid(fN) && isValid(uN) && isValid(pW)){
			User temp = new User();
			temp.setUserName(uN);
			ObjectSet results = DBHandler.db().queryByExample(temp);
			if (results.hasNext()){
				return false;
			} else {
				DBHandler.db().store(new User(fN, uN, pW));
				DBHandler.db().commit();
				return true;
			}
		} else {
			return false;
		}
		
	}
	
	private static boolean isValid(String ex){
		return ex.matches("([A-Za-z0-9]).*");
	}
}
