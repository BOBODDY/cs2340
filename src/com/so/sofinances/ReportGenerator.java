package com.so.sofinances;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Set;

import com.so.sofinances.handler.UserHandler;
import com.so.sofinances.model.Account;
import com.so.sofinances.model.Report;
import com.so.sofinances.model.TimeData;
import com.so.sofinances.model.Transaction;


/**Generates reports.
 * 
 * @author kodyPC
 *
 */
public class ReportGenerator { 
    
    /**
     * Creates a Spending Category Report of all withdrawals for all accounts of the
     * current user.
     * 
     * @param startDate Start date of the report
     * @param endDate End date of the report
     * @return A Report containing all of the withdrawals for all accounts
     * between the startDate and endDate
     */
    public static Report spendingCategoryReport(final TimeData startDate, final TimeData endDate) {
        Report rep = new Report();
        
        String title = "Spending Category Report for " + UserHandler.getFullName() + "\n";
        title += startDate.toString() + " - " + endDate.toString() + "\n";
        
        rep.setTitle(title);
        
        List<Account> userAccounts = UserHandler.getAccounts();
        
        ArrayList<Transaction> withdrawals = new ArrayList<Transaction>();
        
        // Gather all withdrawals within the time range for all accounts
        for (Account acc:userAccounts) {
            for (Transaction transact:acc.getTransactions()) { 
                if (transact.isWithdrawal()) {
                    if ( startDate.compareTo(transact.getTimeOfTransaction()) <= 0 && 
                    		endDate.compareTo(transact.getTimeOfTransaction()) >= 0) {
                    	System.out.println("adding a value");
                    	withdrawals.add(transact);
                    } else {
                    	System.out.println("did not add");
                    }
                }
            }
        }
        
        // Sort gathered transactions into categories 
        HashMap<String, ArrayList<Transaction>> sortedTransacts = 
                new HashMap<String, ArrayList<Transaction>>();
        for (Transaction transact:withdrawals) { 
            String cat = transact.getCategory();
            ArrayList<Transaction> tmp = sortedTransacts.get(cat);
            if (tmp == null) {
                tmp = new ArrayList<Transaction>();
            }
            tmp.add(transact);
            sortedTransacts.put(cat, tmp);
        }
        
        Set<String> categories = sortedTransacts.keySet();
        HashMap<String, Double> data = new HashMap<String, Double>();
        
        // Sum up the transactions in each category 
        for (String cat:categories) {
            ArrayList<Transaction> transacts = sortedTransacts.get(cat);
            double amountSpent = 0;
            for (Transaction transact:transacts) {
                amountSpent += transact.getAmount();
            }
            data.put(cat, Double.valueOf(amountSpent));
        }
        
        rep.addData(data);
        
        return rep;
    }
    
    public static Report cashFlowReport(TimeData startDate, TimeData endDate) {
    	Report rep = new Report();
    	
    	String title = "Cash Flow Report for " + UserHandler.getFullName() + "\n";
        title += startDate.toString() + " - " + endDate.toString() + "\n";
        
        rep.setTitle(title);
        
        List<Account> userAccounts = UserHandler.getAccounts();
        
        ArrayList<Transaction> withdrawals = new ArrayList<Transaction>();
        ArrayList<Transaction> deposits = new ArrayList<Transaction>();
        
        // Gather all withdrawals within the time range for all accounts
        for (Account acc:userAccounts) {
            for (Transaction transact:acc.getTransactions()) { 
                if (transact.isWithdrawal()) {
                    if ( startDate.compareTo(transact.getTimeOfTransaction()) <= 0 && 
                    		endDate.compareTo(transact.getTimeOfTransaction()) >= 0) {
                    	withdrawals.add(transact);
                    }
                } else {
                	if ( startDate.compareTo(transact.getTimeOfTransaction()) <= 0 && 
                    		endDate.compareTo(transact.getTimeOfTransaction()) >= 0) {
                    	deposits.add(transact);
                    }
                	
                }
            }
        }
        
        double totalWithdraw = 0;
        double totalDeposit = 0;
        
        for(Transaction transact: withdrawals) {
        	totalWithdraw += transact.getAmount();
        }
        
        for(Transaction transact: deposits) {
        	totalDeposit += transact.getAmount();
        }
        
        double difference = totalDeposit - totalWithdraw;

        return rep;
    }

}
