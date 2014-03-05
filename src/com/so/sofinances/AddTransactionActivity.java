package com.so.sofinances;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddTransactionActivity extends Activity {
	
	EditText name, amount;
	
	String accountName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_transaction);
		
		name = (EditText) findViewById(R.id.add_trans_name);
		
		amount = (EditText) findViewById(R.id.add_trans_amount);
		
		Bundle bundle = getIntent().getExtras();
		
		if(bundle != null) {
			accountName = bundle.getString("accountName");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_transaction, menu);
		return true;
	}
	
	public void finishTransaction(View v) {
		UserHandler.getCU().getAccount(accountName);
	}

}
