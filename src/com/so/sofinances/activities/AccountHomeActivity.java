package com.so.sofinances.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.so.sofinances.R;
import com.so.sofinances.SwipeDetector;
import com.so.sofinances.SwipeDetector.Action;
import com.so.sofinances.handler.AccountHandler;
import com.so.sofinances.handler.DBHandler;
import com.so.sofinances.model.TimeData;
import com.so.sofinances.model.Transaction;
import com.so.sofinances.utilities.SwipeDismissListViewTouchListener;
import com.so.sofinances.utilities.SwipeDismissListViewTouchListener.DismissCallbacks;


/** 
 * Home activity which lists an Accounts Transactions
 * @author kodyPC
 */
public class AccountHomeActivity extends Activity {
    /**
     * listview for transaction history.
     */
    private ListView history;
    /**
     * textview for balace.
     */
    private TextView balance;
    
    private String balanceStr;
    /**
     * textview for instructions.
     */
    private TextView instruct;
    
    private SimpleAdapter transAdapter;

    
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_transaction_home);
        
        balance = (TextView) findViewById(R.id.transactionBalance);
        history = (ListView) findViewById(R.id.transactionHistory);
        instruct = (TextView) findViewById(R.id.account_home_instruct);
        
        SwipeDismissListViewTouchListener touchListener =
        		new SwipeDismissListViewTouchListener(
        				history,
        				new TransactionDismissCallback());
        history.setOnTouchListener(touchListener);
        history.setOnScrollListener(touchListener.makeScrollListener());

        if (!AccountHandler.hasTransactions()) {
        	instruct.setText("Click \"+\" to add transaction");
        	instruct.setVisibility(0);
        } else {
        	instruct.setVisibility(8);
        }
        balanceStr = AccountHandler.getBalanceString();
        balance.setText("Balance: " + balanceStr);
        
        transAdapter = AccountHandler.buildList(this);
        history.setAdapter(transAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.transaction_home, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
    	int itemId = item.getItemId();
    	
    	if (itemId == R.id.addTransact) {
    	    Intent intent = new Intent(getApplicationContext(), AddTransactionActivity.class);
            startActivity(intent);
            return true;
    	} else if (itemId == R.id.sort_by_date) {
    		transAdapter = AccountHandler.sortByDate(this);
    		update();
    		return true;
    	} else {
    	    return super.onOptionsItemSelected(item);
    	}
    }
    
    @Override
    public void onPause() {
        super.onPause();
        //System.out.println("DB Updated");
        DBHandler.update();
    }
    
	private void update() {
		//transAdapter.notifyDataSetChanged();
		history.setAdapter(transAdapter);
		balance.setText("Balance: " + AccountHandler.getBalanceString());
		if (!AccountHandler.hasTransactions()) {
			instruct.setText("Click \"+\" to add transaction");
			instruct.setVisibility(0);
		}
	}
    
    private class TransactionDismissCallback implements DismissCallbacks {
    	public void onDismiss(ListView listView, int[] reverseSortedPositions) {
    		for (int position : reverseSortedPositions) {
    			System.out.println(position);
    			AccountHandler.removeTransactionByIndex(position);
    			transAdapter = AccountHandler.buildList(AccountHomeActivity.this);
    		}
    		update();
    	}
    	public boolean canDismiss(int position) {
    		return true;
    	}
    }
}
