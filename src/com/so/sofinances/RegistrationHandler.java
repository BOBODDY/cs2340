package com.so.sofinances;

import com.db4o.ObjectSet;

public class RegistrationHandler {
	
	public static boolean createUser(String fN, String uN, String pW){
		User temp = new User();
		temp.setUserName(uN);
		ObjectSet results = DBHandler.db().queryByExample(temp);
		if (results.hasNext()){
			System.out.println(((User) results.next()).getFullName());
			return false;
		} else {
			DBHandler.db().store(new User(fN, uN, pW));
			DBHandler.db().commit();
			return true;
		}
	}
}
