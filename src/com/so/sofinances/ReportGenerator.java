package com.so.sofinances;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ReportGenerator {

	public ReportGenerator() {
		
	}
	
	public static Report spendingCategoryReport() {
		Report rep = new Report();
		
		User user = UserHandler.getCU();
		
		String result = "Spending Category Report for " + user.getUserName();
		
		ArrayList<Account> userAccounts = user.getAccounts();
		
		ArrayList<Transaction> withdrawals = new ArrayList<Transaction>();
		
		for(Account acc:userAccounts) {
			for(Transaction t:acc.getTransactions()) {
				if(t.isWithdrawal()) {
					withdrawals.add(t);
				}
			}
		}
		
		HashMap<String, ArrayList<Transaction>> sortedTransacts = new HashMap<String, ArrayList<Transaction>>();
		for(Transaction t:withdrawals) {
			String cat = t.getCategory();
			ArrayList<Transaction> tmp = sortedTransacts.get(cat);
			if(tmp == null) {
				tmp = new ArrayList<Transaction>();
			}
			sortedTransacts.put(cat, tmp);
		}
		
		Set<String> strings = sortedTransacts.keySet();
		for(String s:strings) {
			ArrayList<Transaction> transacts = sortedTransacts.get(s);
			for(Transaction t:transacts) {
				result += s + "\t\t$" + t.getAmount();
			}
		}
		
		rep.addData(result);
		
		return rep;
	}

}
