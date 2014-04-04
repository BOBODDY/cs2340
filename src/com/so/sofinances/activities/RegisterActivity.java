package com.so.sofinances.activities;

import com.so.sofinances.R;
import com.so.sofinances.handler.DBHandler;
import com.so.sofinances.handler.RegistrationHandler;

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

/**Activity used for registering a new user.
 * 
 * @author kodyPC
 *
 */
public class RegisterActivity extends Activity {
    
    /**
     * full name of the new account.
     */
    private EditText fullName;
    /**
     * username of the new account.
     */
    private EditText username;
    /**
     * password of the new account.
     */
    private EditText password;
    /**
     * display for showing user messages.
     */
    private TextView display;

    /* (non-Javadoc) creates variable representations of the textfields
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_register);
        
        fullName = (EditText) findViewById(R.id.full_name);
        
        username = (EditText) findViewById(R.id.username);
        
        password = (EditText) findViewById(R.id.password);
        
        display = (TextView) findViewById(R.id.reg_display);
    }

    /* (non-Javadoc) adds the associated buttons to the menu bar
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

    /* (non-Javadoc) returns to the parent activity when pressed
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	int itemId = item.getItemId();
    	if(itemId == android.R.id.home) {
    		NavUtils.navigateUpFromSameTask(this);
            return true;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    /**passes in field information to the registration 
     * handler to attempt to register the user.
     * 
     * @param v the view
     */
    public void onRegisterClick(View view) {
        String fName = fullName.getText().toString();
        String uName = username.getText().toString();
        String pass = password.getText().toString();
        
        if (RegistrationHandler.createUser(fName, uName, pass)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            //DBHandler.db().store(UserHandler.getCU());
            //DBHandler.db().commit();
        } else {
            display.setText("Username already exists or names and password don't start with letter/number");
        }
    }
    
    /* (non-Javadoc) updates the database with the activity is paused
     * @see android.app.Activity#onPause()
     */
    @Override
    public void onPause() {
        super.onPause();
        //System.out.println("DB Updated");
        DBHandler.update();
    }
}
