package com.so.sofinances;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AccountHomeActivity extends Activity {
	private static final String TEXT1 = "text1";
	private static final String TEXT2 = "text2";
	final String[] fromMapKey = new String[] {TEXT1, TEXT2};
    final int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};
	ListView transactionHistory;
	TextView balance;
	TextView instruct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_home);
		
		balance = (TextView) findViewById(R.id.transactionBalance); 
		balance.setText("Balance: " + AccountHandler.getBalanceString());
		transactionHistory = (ListView) findViewById(R.id.transactionHistory);
		instruct = (TextView) findViewById(R.id.account_home_instruct);
		
		List<Transaction> transacts = AccountHandler.getTransactions();
		if(transacts != null) {
			Log.d("com.so.sofinances", "transacts not null");
			List<String> transList = new ArrayList<String>(transacts.size());
			List<Map<String, String>> transTimeList = new ArrayList<Map<String, String>>(transacts.size());
			
			if(!transacts.isEmpty()) {
				instruct.setText("");
				Collections.sort(transacts);
				
				String balanceStr = AccountHandler.getBalanceString();
				Log.d("com.so.sofinances", "Balance " + (balanceStr==null? "":"not") + " null");
				Log.d("com.so.sofinances", "Balance is " + balanceStr);
				balance.setText("Balance: " + balanceStr);
				Log.d("com.so.sofinances", "set balance string");
				for(int i = 0; i < transacts.size(); i++) {
					Transaction t = transacts.get(i);
					if (t != null) {
						transList.add(t.toString());
						Map<String, String> transAndTime = new HashMap<String, String>();
						transAndTime.put(TEXT1, transList.get(i));
						TimeData td = t.getTimeOfTransaction();
						if(td != null) {
							transAndTime.put(TEXT2, td.toString());
						} else {
							transAndTime.put(TEXT2, "");
						}
						transTimeList.add(transAndTime);
					}
				}
			} else {
				instruct.setText("Click \"+\" to add transaction");
			}
			SimpleAdapter transAdapter = new SimpleAdapter(this,
					transTimeList,
					android.R.layout.simple_list_item_2, 
					fromMapKey, toLayoutId);
			transactionHistory.setAdapter(transAdapter);
		} else {
			Log.d("com.so.sofinances", "transacts is null");
		}
		
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
	        	startActivity(i);
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onPause(){
		super.onPause();
		System.out.println("DB Updated");
		DBHandler.update();
	}
}
