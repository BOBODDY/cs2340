package com.so.sofinances.model.test;

import junit.framework.TestCase;

import com.so.sofinances.model.User;


public class AccToStringTest extends TestCase {
	public void testAccToString(){
		User person = new User("first last", "user1", "password");
		person.addAccount("name", "acc1", 100, 0);
		person.addAccount("name2", "acc1", 100, 0);

		assertTrue(person.accToString() == "name name2");
	}

}


