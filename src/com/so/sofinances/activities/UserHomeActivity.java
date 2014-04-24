package com.so.sofinances.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.so.sofinances.R;
import com.so.sofinances.handler.AccountHandler;
import com.so.sofinances.handler.DBHandler;
import com.so.sofinances.handler.UserHandler;
import com.so.sofinances.model.Account;
import com.so.sofinances.model.TimeData;
import com.so.sofinances.utilities.CurrencyFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

/** The user home which lists all of the accounts and allows for report generation and account creation.
 * @author kodyPC
 *
 */
public class UserHomeActivity extends Activity {
   
    /**
     * the ListView displayed on screen.
     */
    private ListView list;
    /**
     * instruction at the top of the screen.
     */
    private TextView instruct;
    /**
     * a constant date used for testing.
     */
    private static final int PICK_DATE = 314156;
    
    private SimpleAdapter balAdapter;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_user_home);
        
        list = (ListView) findViewById(R.id.accountsList);
        instruct = (TextView) findViewById(R.id.user_home_instruct);
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long rowId) {
                String name = UserHandler.getAccounts().get(position).getDisplayName();
                AccountHandler.setCurrentAccount(name);
                //Toast.makeText(getApplicationContext(), "Found: " + name, Toast.LENGTH_SHORT).show();
                
                Intent intent = new Intent(getApplicationContext(), AccountHomeActivity.class);
                
                startActivity(intent);
            }
            
        });
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        update();
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
    	int itemId = item.getItemId();
    	boolean result;
    	
    	if (itemId == R.id.action_report) {
    		// Just going to do the spending category report by default
            Intent intent = new Intent(getApplicationContext(), DatePickingActivity.class);
            startActivityForResult(intent, PICK_DATE);
            result = true;
    	} else if (itemId == R.id.action_add_account) {
    	    startActivity(new Intent(this, AddAccountActivity.class));
            result = true;
    	} else if (itemId == R.id.logout) {
    	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Are you sure you want to log out?");
            builder.setInverseBackgroundForced(true);
            builder.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                int which) {
                            dialog.dismiss();
                            UserHandler.clear();
                            
                            startActivity(new Intent(getApplicationContext(), 
                                    WelcomeActivity.class));
                            finish();
                        }
                    });
            builder.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            result = true;
    	} else if (itemId == R.id.gbp) {
    		CurrencyFormat.changeLocale(Locale.UK);
    		update();
    		return true;
    	} else if (itemId == R.id.us_dollars) {
    		CurrencyFormat.changeLocale(Locale.US);
    		update();
    		return true;
    	} else {
    	    result = super.onOptionsItemSelected(item);
    	}
    	
    	return result;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	String startDate = "startDate";
    	String endDate = "endDate";
        if (requestCode == PICK_DATE && resultCode == RESULT_OK) {
            //get data from result intent and send to report generator
            TimeData start = (TimeData) data.getSerializableExtra(startDate);
            TimeData end = (TimeData) data.getSerializableExtra(endDate);
            
            String reportType = (String) data.getStringExtra("reportType");
            
            int type = -24312; //some random number
            if(reportType.equals("Spending Category Report")) {
            	type = ReportViewActivity.SPENDING_REPORT;
            } else if(reportType.equals("Cash Flow Report")) {
            	type = ReportViewActivity.CASH_FLOW_REPORT;
            } else if(reportType.equals("Income Category Report")) {
            	type = ReportViewActivity.INCOME_REPORT;
            }
            
            Intent intent = new Intent(getApplicationContext(), ReportViewActivity.class);
            intent.putExtra(startDate, start);
            intent.putExtra(endDate, end);
            intent.putExtra("reportType", type);
            startActivity(intent);
        }
    }
    
	/**
	 * Moves to the Add Account Screen.
	 * 
	 * Creates a new Intent for the AddAccount Activity and starts up the Activity to move to the Add Account Screen
	 * @param view The view of the screen
	 */
    public void onCreateClick(View view) {
        startActivity(new Intent(this, AddAccountActivity.class));
    }
    
    @Override
    public void onPause() {
        super.onPause();
        //System.out.println("DB Updated");
        DBHandler.update();
    }
    
	private void update() {
        balAdapter = UserHandler.buildList(this);
		list.setAdapter(balAdapter);
		if (!UserHandler.hasAccounts()) {
			instruct.setText("Click \"+\" to add account");
			instruct.setVisibility(0);
		} else {
			instruct.setVisibility(8);
		}
	}
}
