package com.so.sofinances.handler;

import com.db4o.ObjectSet;
import com.so.sofinances.model.User;

public class LoginHandler {
	
	/**
	 * Checks and returns the User if the User and Password both match with the Database
	 * The method sets a temporary User object with the User and Password given. It then searches through
	 * the Object database with that User Object. If the User and Password match, then it returns the
	 * User Object that matches, if not, then it returns null.
	 * @param uName The User name that's being checked
	 * @param password The Password that's being checked
	 * @return The User that matches the database of null if none match
	 */
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
