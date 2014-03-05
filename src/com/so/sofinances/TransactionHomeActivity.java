package com.so.sofinances;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class TransactionHomeActivity extends Activity {
	
	ListView transactionHistory;
	
	String accountName;

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
		
		transactionHistory = (ListView) findViewById(R.id.transactionHistory);
		
		ArrayList<String> names = new ArrayList<String>();
		
		ArrayList<Transaction> transacts = UserHandler.getCU().getAccount(accountName).getTransactions();
		
		if(transacts != null) {
			for(Transaction t : transacts) {
				if(t instanceof Deposit) {
					names.add( ((Deposit)t).getName() );
				} else if( t instanceof Withdrawal) {
					names.add( ((Withdrawal)t).getCategory() );
				}
			}
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
	        	i.putExtra("accountName", accountName);
	        	startActivity(i);
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
