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
    public static boolean createUser(String fullName, String userName, String password) {
        if (isValidFullName(fullName) && isValidUserName(userName) && isValidPassword(password)) {
            User temp = new User();
            temp.setUserName(userName);
            if (DBHandler.db() == null) return false;
            ObjectSet<Object> results = DBHandler.db().queryByExample(temp);
            if (results.hasNext()) {
                return false;
            } else {
                DBHandler.db().store(new User(fullName, userName, password));
                DBHandler.db().commit();
                return true;
            }
        } else {
            return false;
        }
        
    }
    
	/**
	 * Checks to see if the String entered is a valid full name. this means only letters and spaces and the length is 
	 * 
	 * The check is done by a RegEx check
	 * @param str The String is be compared
	 * @return True if the String is valid; False otherwise
	 */
    private static boolean isValidFullName(String str) {
    	if (str == null) return false;
        return str.matches("([A-Za-z0-9])([A-Za-z0-9]||[\" \"]).*") && (str.length() >= 2);
    }
    
    /** checks that username starts with a letter or number and contains on letters numbers underscores and spaces
     * @param str string to be tested
     * @return true if matches, false otherwise
     */
    private static boolean isValidUserName(String str){
    	if (str == null) return false;
    	return str.matches("([A-Za-z0-9])([A-Za-z0-9]||[_]||[\" \"]).*") && (str.length() >= 2);
    }
    
    
    /** Checks that password consists of letters and numbers
     * @param str string to be tested
     * @return true if matches, false otherwise
     */
    private static boolean isValidPassword(String str){
    	if (str == null) return false;
    	return str.matches("[A-Za-z0-9].*") && (str.length() >= 2);
    }
}
