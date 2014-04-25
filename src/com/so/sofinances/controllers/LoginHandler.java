package com.so.sofinances.controllers;

import android.widget.Toast;

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
        User u = (result.hasNext()) ? (User) result.next() : null;
        if (u != null && encryptor.decrypt(u.getPassword()).equals(password)) {
            return example;
        } else if (u != null){
        	String hint = u.getHint();
        	if (hint != null && !hint.equals("")) {
        		System.out.println(hint);
        		throw new PasswordMismatchException(hint);
        	} else {
        		throw new PasswordMismatchException();
        	}
        } else {
        	throw new InvalidInputException("Username doesn't exist");
        }
    }
    
    public static String[] getLostPassword(String uName) throws InvalidInputException {
    	String[] retStr = new String[2];
    	BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("ENCRYPT");
        User example = new User();
        example.setUserName(uName);
        ObjectSet<Object> result = DBHandler.db().queryByExample(example);
        User u = (result.hasNext()) ? (User) result.next() : null;
        if (u == null){
        	throw new InvalidInputException("No such user");
        } else {
        	retStr[0] = u.getEmail();
        	retStr[1] = encryptor.decrypt(u.getPassword());
        	return retStr;
        }
    }
}
