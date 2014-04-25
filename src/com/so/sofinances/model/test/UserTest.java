package com.so.sofinances.model.test;
import junit.framework.TestCase;
import com.so.sofinances.model.User;

public class UserTest extends TestCase{
	
	public void testFoundAccount(){
		User user = new User("Fn Ln", "user1", "password", "hint");
		user.addAccount("name", "acc1", 100, 0);
		assertTrue(user.getAccount("acc1").getDisplayName() == "acc1");
		assertTrue(user.getAccount("acc1").getBalance() == 100);
		assertTrue(user.getAccount("acc1").getFullName() == "name");
		assertTrue(user.getAccount("acc1").getMonthlyInterestRate() == 0);
	}
	
	public void testNoAccountFound(){
		User user = new User("Fn Ln", "user1", "password", "hint");
		user.addAccount("name", "acc1", 100, 0);
		assertTrue(user.getAccount("acc2") == null);
	}

}
