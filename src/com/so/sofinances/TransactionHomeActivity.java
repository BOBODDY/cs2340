package com.so.sofinances;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TransactionHomeActivity extends Activity {
	
	ListView transactionHistory;
	TextView noTrans;
	
	String accountName;
	NumberFormat us = NumberFormat.getCurrencyInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_home);
		
		Bundle bundle = getIntent().getExtras();
		
		if(bundle != null) {
			accountName = bundle.getString("accountName");
		} else {
			accountName = "";
		}
		
		noTrans = (TextView) findViewById(R.id.transactionBalanceBar);
		noTrans.setText("Current Balance: "
				+ UserHandler.getCU().getAccount(accountName).getBalanceString()
				+ ", click ''+'' to add transaction");
		
		transactionHistory = (ListView) findViewById(R.id.transactionHistory);
		
		List<Transaction> transacts = UserHandler.getCU().getAccount(accountName).getTransactions();
		List<String> transList = new ArrayList<String>();
		
		if(!transacts.isEmpty()) {
			for(Transaction t : transacts) {
				if(t.isWithdrawal()) { //Withdrawal
					names.add(t.getCategory());
				} else { //Deposit
					names.add(t.getName());
			}
		}
		ArrayAdapter<String> transAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, 
				transList);
		transactionHistory.setAdapter(transAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction_home, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.addTransact:
	        	Intent i = new Intent(getApplicationContext(), AddTransactionActivity.class);
	        	i.putExtra("accountName", accountName);
	        	startActivity(i);
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
