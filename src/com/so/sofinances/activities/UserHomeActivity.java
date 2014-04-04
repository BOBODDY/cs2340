package com.so.sofinances.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.so.sofinances.R;
import com.so.sofinances.handler.AccountHandler;
import com.so.sofinances.handler.DBHandler;
import com.so.sofinances.handler.UserHandler;
import com.so.sofinances.model.Account;
import com.so.sofinances.model.Currency;
import com.so.sofinances.model.TimeData;

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
     * used for the listview.
     */
    private static final String TEXT1 = "text1";
    /**
     * used for the listview.
     */
    private static final String TEXT2 = "text2";
    /**
     * the map displayed by the listview.
     */
    private final String[] fromMapKey = new String[] {TEXT1, TEXT2};
    /**
     * maps to the actual layout.
     */
    private final int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};
   
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
        
        List<Account> accounts = UserHandler.getAccounts();
        
        ArrayList<String> names = new ArrayList<String>();
        
        ArrayList<String> balances = new ArrayList<String>();
        
        List<Map<String, String>> namesAndBalances = new ArrayList<Map<String, String>>();
        
        if (accounts.size() < 1) {
            instruct.setVisibility(0);
            instruct.setText("Click \"+\" to add account");
        } else {
            instruct.setVisibility(8);
        }
        
        
        for (int i = 0; i < accounts.size(); i++) {
            names.add(accounts.get(i).getDisplayName());
            balances.add(Currency.format(accounts.get(i).getBalance()));
            Map<String, String> nameAndBalance = new HashMap<String, String>();
            nameAndBalance.put(TEXT1, names.get(i));
            nameAndBalance.put(TEXT2, balances.get(i));
            namesAndBalances.add(nameAndBalance);
        }
        
        SimpleAdapter balAdapter = new SimpleAdapter(this, namesAndBalances,
                android.R.layout.simple_list_item_2, fromMapKey, toLayoutId);
        
        list.setAdapter(balAdapter);
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
            
            Intent intent = new Intent(getApplicationContext(), ReportViewActivity.class);
            intent.putExtra(startDate, start);
            intent.putExtra(endDate, end);
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
}
