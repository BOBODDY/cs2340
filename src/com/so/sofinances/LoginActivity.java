package com.so.sofinances;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	EditText unText, pwText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	    unText = (EditText)findViewById(R.id.login_username);
	    pwText = (EditText)findViewById(R.id.login_password);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void successResponse(View view) {
	    Intent successIntent = new Intent(this, SuccessActivity.class);
	    String username = unText.getText().toString();
	    String password = pwText.getText().toString();
	    User theUser = new User(username, password);
	    if (theUser.isDefault()) {
	    	startActivity(successIntent);
	    } else {
	    	((TextView)findViewById (R.id.invalidLoginTV)).setText("Invalid Login");
	    }
	}

}
