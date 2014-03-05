package com.so.sofinances;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class UserHomeActivity extends Activity {
	TextView accountList;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_home);
		
		//accountList = (TextView) findViewById(R.id.accountList);
		//accountList.setText("accounts: " + UserHandler.getCU().accToString());
		
		lv = (ListView) findViewById(R.id.accountsList);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				String name = UserHandler.currentUser.getAccounts().get(position).getDisplayName();
				Toast.makeText(getApplicationContext(), "Found: " + name, Toast.LENGTH_SHORT).show();
			}
			
		});
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		ArrayList<Account> accounts = UserHandler.currentUser.getAccounts();
		
		ArrayList<String> names = new ArrayList<String>();
		
		for(int i=0; i<accounts.size(); i++) {
			names.add(accounts.get(i).getDisplayName());
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, 
				names);
		
		lv.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_home, menu);
		return true;
	}
	
	public void onCreateClick(View v){
		startActivity(new Intent(this, AddAccountActivity.class));
	}
}
