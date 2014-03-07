package com.so.sofinances;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

public class AddTransactionActivity extends Activity {
	
	EditText name, amount;
	
	String accountName;
	
	TimePicker clock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_transaction);
		
		clock = (TimePicker) findViewById(R.id.timePicker1);
		
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
	
	public void cancelAdd(View v) {
		Intent i = new Intent(getApplicationContext(), TransactionHomeActivity.class);
		i.putExtra("accountName", accountName);		
		startActivity(i);
	}
	
	public void finishTransaction(View v) {

		String transName = name.getText().toString();
		
		String amountStr = amount.getText().toString();
		if(amountStr.isEmpty()) {
			amountStr = "0";
		}
		double amount = Double.parseDouble(amountStr);
		
		String transType = "";
		
		RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
		if(rg.getCheckedRadioButtonId()!=-1){
		    int id= rg.getCheckedRadioButtonId();
		    View radioButton = rg.findViewById(id);
		    int radioId = rg.indexOfChild(radioButton);
		    RadioButton btn = (RadioButton) rg.getChildAt(radioId);
		    transType = btn.getText().toString();
		}
		Transaction toAdd = null;
		
		int hour = clock.getCurrentHour();
		int minute = clock.getCurrentMinute();
		
		if (transType.equals("Withdrawal")) {
			toAdd = new Withdrawal(null, null, amount, transName, transType);
		} else if (transType.equals("Deposit")){
			toAdd = new Deposit(null, null, amount, transName, transType);
		}
		
		UserHandler.currentUser.getAccount(accountName).addTransaction(toAdd);
		Intent i = new Intent(getApplicationContext(), TransactionHomeActivity.class);
		i.putExtra("accountName", accountName);		
		startActivity(i);
		DBHandler.db().store(UserHandler.currentUser);
		DBHandler.db().commit();
		finish();
	}

}
