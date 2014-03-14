package com.so.sofinances;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class AddTransactionActivity extends Activity {
	
	EditText name, amount;
	
	String accountName;
	
	DatePicker cal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_transaction);
		
		cal = (DatePicker) findViewById(R.id.datePicker1);
		
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
		Intent i = new Intent(getApplicationContext(), AccountHomeActivity.class);
		i.putExtra("accountName", accountName);		
		startActivity(i);
	}
	
	public void finishTransaction(View v) {

		String transName = name.getText().toString();
		
		if (transName == null || transName.equals("")) {
			Intent i = new Intent(getApplicationContext(), AccountHomeActivity.class);
			i.putExtra("accountName", accountName);		
			startActivity(i);
			finish();
		}
		
		String amountStr = amount.getText().toString();
		if (transName != null && !(transName.equals("")) && amountStr != null && !(amountStr.equals(""))) {
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
			
			int day = cal.getDayOfMonth();
			int month = cal.getMonth() + 1;
			int year = cal.getYear();
			
			TimeData dateOfTrans = new TimeData(month, day, year);
			System.out.println(dateOfTrans);
			
			Spinner categories = (Spinner) findViewById(R.id.category_spinner1);
			
			String category = (String) categories.getSelectedItem();
			
			if (transType.equals("Withdrawal")) {
				toAdd = new Transaction(dateOfTrans, amount, transName, category, true);
			} else if (transType.equals("Deposit")){
				toAdd = new Transaction(dateOfTrans, amount, transName, category, false);
			}
			
			UserHandler.getAccount(accountName).addTransaction(toAdd);
			
			Intent i = new Intent(getApplicationContext(), AccountHomeActivity.class);
			i.putExtra("accountName", accountName);		
			//DBHandler.db().store(UserHandler.getCU());
			//DBHandler.db().commit();
			startActivity(i);
			finish();
		}
		
	}

}
