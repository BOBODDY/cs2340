package com.so.sofinances.ui;

import com.so.sofinances.R;
import com.so.sofinances.controllers.DBHandler;
import com.so.sofinances.controllers.RegistrationHandler;
import com.so.sofinances.exceptions.InvalidInputException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    
    private EditText confirmPassword;
    
    private EditText email;
    
    private EditText hint;
    
    private View registerButton;

    /* (non-Javadoc) creates variable representations of the textfields
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_register);
        
        TextWatcher passConfirm = new PasswordConfirm();
        
        fullName = (EditText) findViewById(R.id.full_name);
        
        username = (EditText) findViewById(R.id.username);
        
        password = (EditText) findViewById(R.id.password);
        password.addTextChangedListener(passConfirm);
        
        confirmPassword = (EditText) findViewById(R.id.confirm_password);

        confirmPassword.addTextChangedListener(passConfirm);
        
        email = (EditText) findViewById(R.id.email);
        hint = (EditText) findViewById(R.id.hint);
        
        registerButton = findViewById(R.id.submit_register_button);
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
    	if (itemId == android.R.id.home) {
    	    NavUtils.navigateUpFromSameTask(this);
            return true;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    /**passes in field information to the registration 
     * handler to attempt to register the user.
     * 
     * @param view the view
     */
    public void onRegisterClick(View view) {
        String fName = fullName.getText().toString();
        String uName = username.getText().toString();
        String pass = password.getText().toString();
        String eM = email.getText().toString();
        String h = hint.getText().toString();
        
        try {
        	RegistrationHandler.createUser(fName, uName, pass, eM, h);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } catch (InvalidInputException e) {
        	Toast t = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
        	t.show();
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
    
    public void updateButtonState() {
    	String p1 = password.getText().toString();
    	String p2 = confirmPassword.getText().toString();
    	if (p1.equals(p2)) {
    		registerButton.setEnabled(true);
    	} else {
    		registerButton.setEnabled(false);
    	}
    }
    
    private class PasswordConfirm implements TextWatcher {
        public void afterTextChanged(Editable s) {
            updateButtonState();
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }

}
