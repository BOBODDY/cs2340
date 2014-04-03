package com.so.sofinances.activities;

import com.so.sofinances.R;
import com.so.sofinances.handler.UserHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**Activity for displaying the fields associated with adding a new account. 
 * Then passes off info to UserHandler to try and add the account
 * 
 * @author kodyPC
 *
 */
public class AddAccountActivity extends Activity {
    /**
     * full name of the new account.
     */
    EditText fullName;
    /**
     * display name of the new account.
     */
    EditText displayName;
    /**
     * balance of the new account.
     */
    EditText balance;
    /**
     * interest rate of the new account.
     */
    EditText interestRate;
    /**
     * warning shown to the user.
     */
    TextView warning;

    /* (non-Javadoc)creates variables for all of the fields in the activity
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        fullName = (EditText) findViewById(R.id.acc_fullname);
        displayName = (EditText) findViewById(R.id.acc_displayname);
        balance = (EditText) findViewById(R.id.acc_balance);
        interestRate = (EditText) findViewById(R.id.acc_interestrate);
        warning = (TextView) findViewById(R.id.add_acc_warning);
    }

    /* (non-Javadoc)adds the associated buttons to the menu bar
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_account, menu);
        return true;
    }
    
    /** creates strings from all of the info on the activity and attempts to add the account.
     * 
     * @param v the view
     */
    public void onSubmitClick(View v) {
        String fName = fullName.getText().toString();
        String dName = displayName.getText().toString();
        
        String balanceStr = balance.getText().toString();
        if (balanceStr.isEmpty()) {
            balanceStr = "0";
        }
        double bal = Double.parseDouble(balanceStr);
        
        String interest = interestRate.getText().toString();
        if (interest.isEmpty()) {
            interest = "0";
        }
        double intRate = Double.parseDouble(interest);    
        if (UserHandler.addAccount(fName, dName, bal, intRate)) {
            startActivity(new Intent(this, UserHomeActivity.class));
            //DBHandler.db().store(UserHandler.getCU());
            //DBHandler.db().commit();
            finish();
        } else {
            warning.setText("Username and display name must start with a letter or number");
        }
    }
    
    /** Simply returns to the User Home page. Does not add an account
     * @param v the view
     */
    public void onCancelClick(View v) {
        startActivity(new Intent(this, UserHomeActivity.class));
        finish();
    }
}
