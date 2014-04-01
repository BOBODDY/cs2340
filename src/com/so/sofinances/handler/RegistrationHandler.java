package com.so.sofinances.handler;

import com.db4o.ObjectSet;
import com.so.sofinances.model.User;

public class RegistrationHandler {
    
	/**
	 * Creates a User Object that contains all of the parameters given.
	 * A new User Object is created with the following parameters. It is then checked in the database if
	 * there is another User with the same parameters. If so, then DO NOt create it; otherwise, create and store the User
	 * @param fN The First Name of the User
	 * @param uN The Last Name of the User
	 * @param pW The Password of the User
	 * @return True if the User is created and stored in the database; otherwise, it returns False
	 */
    public static boolean createUser(String fN, String uN, String pW){
        if (isValid(fN) && isValid(uN) && isValid(pW)){
            User temp = new User();
            temp.setUserName(uN);
            ObjectSet<Object> results = DBHandler.db().queryByExample(temp);
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
    
	/**
	 * Checks to see if the String entered is a valid set of characters
	 * The check is done by a RegEx check
	 * @param ex The String is be compared
	 * @return True if the String is valid; False otherwise
	 */
    private static boolean isValid(String ex){
        return ex.matches("([A-Za-z0-9]).*");
    }
}
