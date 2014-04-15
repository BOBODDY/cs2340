package com.so.sofinances.model.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.so.sofinances.model.Account;
import com.so.sofinances.model.Transaction;

public class AddTransactionTest {
	private Account a = new Account("name", "dispName", 0, 0);
	
	@Test
	public void testNoTransactions() {
		assertTrue(a.getTransactions().isEmpty());
	}
	
	@Test
	public void testAddNull() {
		double balance = a.getBalance();
		a.addTransaction(null);
		assertTrue(a.getTransactions().isEmpty());
		assertEquals(balance, a.getBalance(), 0);
	}
	
	@Test
	public void testDeposit() {
		Transaction d = new Transaction(null, 100, "name", "income", false);
		a.addTransaction(d);
		assertFalse(a.getTransactions().isEmpty());
		assertEquals(1, a.getTransactions().size());
		assertEquals(100, a.getBalance(), 0);
	}
	
	@Test
	public void testWithdrawal() {
		Transaction w = new Transaction(null, 100, "name", "food", true);
		a.addTransaction(w);
		assertFalse(a.getTransactions().isEmpty());
		assertEquals(1, a.getTransactions().size());
		assertEquals(-100, a.getBalance(), 0);
	}
	
	@Test
	public void testInvalidDeposit() {
		Transaction d = new Transaction(null, -100, "name", "type", true);
		a.addTransaction(d);
		assertFalse(a.getTransactions().isEmpty());
		assertEquals(1, a.getTransactions().size());
		assertEquals(100, a.getBalance(),0);
	}
}
