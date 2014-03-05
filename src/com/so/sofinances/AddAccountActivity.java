package com.so.sofinances;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddAccountActivity extends Activity {
	EditText fullName, displayName, balance, interestRate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_account);
		fullName = (EditText)findViewById(R.id.acc_fullname);
		displayName = (EditText)findViewById(R.id.acc_displayname);
		balance = (EditText) findViewById(R.id.acc_balance);
		interestRate = (EditText) findViewById(R.id.acc_interestrate);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_account, menu);
		return true;
	}
	
	public void onSubmitClick(View v){
		String fName = fullName.getText().toString();
		String dName = displayName.getText().toString();
		
		String balanceStr = balance.getText().toString();
		if(balanceStr.isEmpty()) {
			balanceStr = "0";
		}
		double bal = Double.parseDouble(balanceStr);
		
		String interest = interestRate.getText().toString();
		if(interest.isEmpty()) {
			interest = "0";
		}
		double intRate = Double.parseDouble(interest);	
		DBHandler.db().store(UserHandler.getCU().addAccount(fName, dName, bal, intRate));
		DBHandler.db().commit();
		startActivity(new Intent(this, UserHomeActivity.class));
	}
	
	public void onCancelClick(View v){
		startActivity(new Intent(this, UserHomeActivity.class));
	}
}
