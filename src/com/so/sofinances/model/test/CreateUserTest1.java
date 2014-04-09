package com.so.sofinances.model.test;

import com.so.sofinances.handler.RegistrationHandler;

import junit.framework.TestCase;

public class CreateUserTest1 extends TestCase {

	public void testCreateNullUser(){
		assertFalse(RegistrationHandler.createUser(null, null, null));
	}
	
	public void testCreateIncorrectFullname1(){
		assertFalse(RegistrationHandler.createUser(" 12", "1234", "12345"));
	}
	
	public void testCreateIncorrectFullname2(){
		assertFalse(RegistrationHandler.createUser("1", "1234", "12345"));
	}
	
	public void testCreateIncorrectFullname3(){
		assertFalse(RegistrationHandler.createUser(" ", "1234", "12345"));
	}
	
	public void testCreateIncorrectFullname4(){
		assertFalse(RegistrationHandler.createUser("&&abd", "1234", "12345"));
	}
	
	public void testCreateIncorrectFullname5(){
		assertFalse(RegistrationHandler.createUser(" _abv", "1234", "12345"));
	}
	
	public void testCreateIncorrectFullname6(){
		assertFalse(RegistrationHandler.createUser(" abc^", "1234", "12345"));
	}
	
	public void testCreateIncorrectUsername1(){
		assertFalse(RegistrationHandler.createUser("abcd", " abc", "12345"));
	}
	public void testCreateIncorrectUsername2(){
		assertFalse(RegistrationHandler.createUser("abnme", "()a", "12345"));
	}
	
	public void testCreateIncorrectUsername3(){
		assertFalse(RegistrationHandler.createUser("abcdwerw", "a", "12345"));
	}
	
	public void testCreateIncorrectPassword1(){
		assertFalse(RegistrationHandler.createUser("ab123", "point_", "  "));
	}
	public void testCreateIncorrectPassword2(){
		assertFalse(RegistrationHandler.createUser("ab12", "point_", "abcd_"));
	}

	
	public void testCreateIncorrectPassword3(){
		assertFalse(RegistrationHandler.createUser("ab12556", "point_", "__ab"));
	}
	
	public void testCorrectAccount(){
		assertTrue(RegistrationHandler.createUser("abkljwerjwl", "oiwuer", "12345"));
	}
}
