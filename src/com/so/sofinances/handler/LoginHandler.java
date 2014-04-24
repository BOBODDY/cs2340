package com.so.sofinances.handler;

import com.db4o.ObjectSet;
import com.so.sofinances.exceptions.InvalidInputException;
import com.so.sofinances.exceptions.PasswordMismatchException;
import com.so.sofinances.model.User;
import org.jasypt.util.text.BasicTextEncryptor;

/** Handles logins.
 * @author kodyPC
 *
 */
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
    public static User checkLogin(String uName, String password) throws InvalidInputException {
        User example = new User();
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("ENCRYPT");
        example.setUserName(uName);
        ObjectSet<Object> result = DBHandler.db().queryByExample(example);
        User u = (User)result.next();
        ObjectSet<Object> resultNoPW = DBHandler.db().queryByExample(new User(uName));
        if (encryptor.decrypt(u.getPassword()).equals(password)) {
            return example;
        } else if (resultNoPW.hasNext()){
            throw new PasswordMismatchException();
        } else {
        	throw new InvalidInputException("Username doesn't exist");
        }
    }
}
