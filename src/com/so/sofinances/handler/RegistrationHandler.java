package com.so.sofinances.handler;

import com.db4o.ObjectSet;
import com.so.sofinances.model.User;

/** handles registration of new users.
 * @author kodyPC
 *
 */
public class RegistrationHandler {
    
	/**
	 * Creates a User Object that contains all of the parameters given.
	 * A new User Object is created with the following parameters. It is then checked in the database if
	 * there is another User with the same parameters. If so, then DO NOt create it; otherwise, create and store the User
	 * @param firstName The First Name of the User
	 * @param userName The Last Name of the User
	 * @param password The Password of the User
	 * @return True if the User is created and stored in the database; otherwise, it returns False
	 */
    public static boolean createUser(String firstName, String userName, String password) {
        if (isValid(firstName) && isValid(userName) && isValid(password)) {
            User temp = new User();
            temp.setUserName(userName);
            ObjectSet<Object> results = DBHandler.db().queryByExample(temp);
            if (results.hasNext()) {
                return false;
            } else {
                DBHandler.db().store(new User(firstName, userName, password));
                DBHandler.db().commit();
                return true;
            }
        } else {
            return false;
        }
        
    }
    
	/**
	 * Checks to see if the String entered is a valid set of characters.
	 * 
	 * The check is done by a RegEx check
	 * @param str The String is be compared
	 * @return True if the String is valid; False otherwise
	 */
    private static boolean isValid(String str) {
        return str.matches("([A-Za-z0-9]).*");
    }
}
