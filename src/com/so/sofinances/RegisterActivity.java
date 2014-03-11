package com.so.sofinances;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {
	
	EditText fullName, username, password;
	
	TextView display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		// Show the Up button in the action bar.
	//	setupActionBar();
		
		fullName = (EditText) findViewById(R.id.full_name);
		
		username = (EditText) findViewById(R.id.username);
		
		password = (EditText) findViewById(R.id.password);
		
		display = (TextView) findViewById(R.id.reg_display);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onRegisterClick(View v) {
		String fName = fullName.getText().toString();
		String uName = username.getText().toString();
		String pass = password.getText().toString();
		
		if (RegistrationHandler.createUser(fName, uName, pass)) {
			startActivity(new Intent(this, WelcomeActivity.class));
			finish();
			DBHandler.db().store(UserHandler.getCU());
			DBHandler.db().commit();
		} else {
			display.setText("Username already exists or names and password don't start with letter/number");
		}
	}
}
