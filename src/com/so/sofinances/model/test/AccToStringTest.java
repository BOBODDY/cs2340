package com.so.sofinances;

import junit.framework.TestCase;

import com.so.sofinances.model.User;


public class AccToStringTest extends TestCase {
	
	User user = new User("first last", "user1", "password");
	user.addAccount("name", "acc1", 100, 0);
	user.addAccount("name2", "acc1", 100, 0);
	
	assertTrue(user.getAccount(acc1).accToString() == "name name2");
	
}

