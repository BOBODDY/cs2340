package com.so.sofinances;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ReportGenerator {

	public ReportGenerator() {
		
	}
	
	public static Report spendingCategoryReport(TimeData startDate, TimeData endDate) {
		Report rep = new Report();
		
		User user = UserHandler.getCU();
		
		String result = "Spending Category Report for " + user.getUserName() + "\n";
		result += startDate.toString() + " - " + endDate.toString() + "\n";
		
		ArrayList<Account> userAccounts = user.getAccounts();
		
		//Log.d("com.so.sofinances", "User has " + userAccounts.size() + " accounts");
		
		ArrayList<Transaction> withdrawals = new ArrayList<Transaction>();
		
		for(Account acc:userAccounts) {
			for(Transaction t:acc.getTransactions()) {
				if(t.isWithdrawal()) {
					if(t.getTimeEntered().compareTo(startDate) <= 0 && t.getTimeEntered().compareTo(endDate) > 0)
						withdrawals.add(t);
				}
			}
		}
		
		//Log.d("com.so.sofinances", "Found " + withdrawals.size() + " withdrawals");
		
		HashMap<String, ArrayList<Transaction>> sortedTransacts = new HashMap<String, ArrayList<Transaction>>();
		for(Transaction t:withdrawals) {
			String cat = t.getCategory();
			//Log.d("com.so.sofinances", "Have a category: " + cat);
			ArrayList<Transaction> tmp = sortedTransacts.get(cat);
			if(tmp == null) {
				tmp = new ArrayList<Transaction>();
			}
			tmp.add(t);
			sortedTransacts.put(cat, tmp);
		}
		
		Set<String> strings = sortedTransacts.keySet();
		for(String s:strings) {
			ArrayList<Transaction> transacts = sortedTransacts.get(s);
			double amountSpent = 0;
			for(Transaction t:transacts) {
				amountSpent += t.getAmount();
			}
			NumberFormat US = NumberFormat.getCurrencyInstance();
			result += s + "\t\t" + US.format(amountSpent) + "\n";
		}
		
		rep.addData(result);
		
		return rep;
	}

}
