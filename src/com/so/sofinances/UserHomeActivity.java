package com.so.sofinances;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class UserHomeActivity extends Activity {
	private static final String TEXT1 = "text1";
	private static final String TEXT2 = "text2";
	final String[] fromMapKey = new String[] {TEXT1, TEXT2};
    final int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};
	TextView accountList;
	ListView lv;
	private NumberFormat us = NumberFormat.getCurrencyInstance();
	static final int PICK_DATE = 314156;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_home);
		
		lv = (ListView) findViewById(R.id.accountsList);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				String name = UserHandler.getAccounts().get(position).getDisplayName();
				//Toast.makeText(getApplicationContext(), "Found: " + name, Toast.LENGTH_SHORT).show();
				
				Intent i = new Intent(getApplicationContext(), AccountHomeActivity.class);
				i.putExtra("accountName", name);
				
				startActivity(i);
			}
			
		});
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		ArrayList<Account> accounts = UserHandler.getAccounts();
		
		ArrayList<String> names = new ArrayList<String>();
		
		ArrayList<String> balances = new ArrayList<String>();
		
		List<Map<String, String>> namesAndBalances = new ArrayList<Map<String, String>>();
		
		for(int i=0; i<accounts.size(); i++) {
			names.add(accounts.get(i).getDisplayName());
			balances.add(us.format(accounts.get(i).getBalance()));
			Map<String, String> nameAndBalance = new HashMap<String, String>();
			nameAndBalance.put(TEXT1, names.get(i));
			nameAndBalance.put(TEXT2, balances.get(i));
			namesAndBalances.add(nameAndBalance);
		}
		
		SimpleAdapter balAdapter = new SimpleAdapter(this, namesAndBalances,
				android.R.layout.simple_list_item_2, fromMapKey, toLayoutId);
		
		lv.setAdapter(balAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_home, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_report: // Just going to do the spending category report by default
	        	Intent i = new Intent(getApplicationContext(), DatePickingActivity.class);
	        	startActivityForResult(i, PICK_DATE);
	        	return true;
	        case R.id.action_add_account:
	        	startActivity(new Intent(this, AddAccountActivity.class));
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == PICK_DATE) {
			if(resultCode == RESULT_OK) {
				//get data from result intent and send to report generator
				TimeData start = (TimeData) data.getSerializableExtra("startDate");
				TimeData end = (TimeData) data.getSerializableExtra("endDate");
				
				Intent i = new Intent(getApplicationContext(), ReportViewActivity.class);
				i.putExtra("startDate", start);
				i.putExtra("endDate", end);
				startActivity(i);
			}
		}
	}
	
	public void onCreateClick(View v){
		startActivity(new Intent(this, AddAccountActivity.class));
	}
	
	@Override
	public void onPause(){
		super.onPause();
		System.out.println("DB Updated");
		DBHandler.update();
	}
}
