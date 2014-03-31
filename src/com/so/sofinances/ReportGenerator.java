package com.so.sofinances;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.so.sofinances.handler.UserHandler;
import com.so.sofinances.model.Account;
import com.so.sofinances.model.Report;
import com.so.sofinances.model.TimeData;
import com.so.sofinances.model.Transaction;

public class ReportGenerator {

	public ReportGenerator() {}
	
	/**
	 * Creates a Spending Category Report of all withdrawals for all accounts of the
	 * current user
	 * 
	 * @param startDate Start date of the report
	 * @param endDate End date of the report
	 * @return A Report containing all of the withdrawals for all accounts between the startDate
	 * and endDate
	 */
	public static Report spendingCategoryReport(TimeData startDate, TimeData endDate) {
		Report rep = new Report();
		
		String title = "Spending Category Report for " + UserHandler.getUserName() + "\n";
		title += startDate.toString() + " - " + endDate.toString() + "\n";
		
		rep.setTitle(title);
		
		ArrayList<Account> userAccounts = UserHandler.getAccounts();
		
		ArrayList<Transaction> withdrawals = new ArrayList<Transaction>();
		
		// Gather all withdrawals within the time range for all accounts
		for(Account acc:userAccounts) {
			for(Transaction t:acc.getTransactions()) {
				if(t.isWithdrawal()) {
					if( !(startDate.compareTo(t.getTimeOfTransaction()) < 0) ) {
						// t.getTimeOfTransaction() is either the same as or after startDate
						if( endDate.compareTo(t.getTimeOfTransaction()) <= 0 ) {
							withdrawals.add(t);
						}
					}
				}
			}
		}
		
		// Sort gathered transactions into categories 
		HashMap<String, ArrayList<Transaction>> sortedTransacts = 
				new HashMap<String, ArrayList<Transaction>>();
		for(Transaction t:withdrawals) {
			String cat = t.getCategory();
			ArrayList<Transaction> tmp = sortedTransacts.get(cat);
			if(tmp == null) {
				tmp = new ArrayList<Transaction>();
			}
			tmp.add(t);
			sortedTransacts.put(cat, tmp);
		}
		
		Set<String> categories = sortedTransacts.keySet();
		HashMap<String, Double> data = new HashMap<String, Double>();
		
		// Sum up the transactions in each category 
		for(String cat:categories) {
			ArrayList<Transaction> transacts = sortedTransacts.get(cat);
			double amountSpent = 0;
			for(Transaction t:transacts) {
				amountSpent += t.getAmount();
			}
			data.put(cat, Double.valueOf(amountSpent));
		}
		
		rep.addData(data);
		
		return rep;
	}

}
