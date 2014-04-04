package com.so.sofinances.activities;

import com.so.sofinances.R;
import com.so.sofinances.handler.LoginHandler;
import com.so.sofinances.handler.UserHandler;
import com.so.sofinances.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/** the activity which facilitates login.
 * @author kodyPC
 *
 */
public class LoginActivity extends Activity {

	/**
	 * username entry.
	 */
    private EditText unText;
	/**
	 * password entry.
	 */
    private EditText pwText;
	/**
	 * textview used to display messages to the user.
	 */
    private TextView display;
    
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_login);
        unText = (EditText) findViewById(R.id.login_username);
        pwText = (EditText) findViewById(R.id.login_password);
        display = (TextView) findViewById(R.id.invalidLoginTV);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    
	/**
	 * Checks to see if the correct credentials for a User is verified or not.
	 * If it the User and Password used to login are checked and verified in the 
	 * database, then the UserHandler sets the currentUser to the User used to login,
	 * and a new Intent is made to move to the UserHomeActivity screen. If the User
	 * and Password given don't match, then an "Invalid login" message is displayed.
	 * 
	 * @param view The View of the current window
	 */
    public void onClickLogin(View view) {
        String username = unText.getText().toString();
        String password = pwText.getText().toString();
        
        User test = LoginHandler.checkLogin(username, password);
        if (test != null) {
            UserHandler.setCurrentUser(test.getUserName().toString());
            startActivity(new Intent(getApplicationContext(), UserHomeActivity.class));
            finish();
        } else {
            display.setText("Invalid login");
        }
    }
}
