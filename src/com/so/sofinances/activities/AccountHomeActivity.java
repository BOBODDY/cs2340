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
import com.so.sofinances.SwipeDismissListViewTouchListener;
import com.so.sofinances.handler.AccountHandler;
import com.so.sofinances.handler.DBHandler;
import com.so.sofinances.model.TimeData;
import com.so.sofinances.model.Transaction;


/** Home activity which lists accounts.
 * @author kodyPC
 * 
 * 
 *
 */
public class AccountHomeActivity extends Activity {
    /**
     * first key for mapping.
     */
    private static final String TEXT1 = "text1";
    /**
     * second key for mapping.
     */
    private static final String TEXT2 = "text2";
    /**
     * mapping for first to second keys.
     */
    private final String[] fromMapKey = new String[] {TEXT1, TEXT2};
    /**
     * array of layout IDs.
     */
    private final int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};
    /**
     * listview for transaction history.
     */
    private ListView history;
    /**
     * textview for balace.
     */
    private TextView balance;
    /**
     * textview for instructions.
     */
    private TextView instruct;
    
    private SimpleAdapter transAdapter;
    private List<Map<String, String>> transTimeList;

    
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_transaction_home);
        
        balance = (TextView) findViewById(R.id.transactionBalance); 
        balance.setText("Balance: " + AccountHandler.getBalanceString());
        history = (ListView) findViewById(R.id.transactionHistory);
        instruct = (TextView) findViewById(R.id.account_home_instruct);
        
        SwipeDismissListViewTouchListener touchListener =
        		new SwipeDismissListViewTouchListener(
        				history,
        				new SwipeDismissListViewTouchListener.DismissCallbacks() {
        					public void onDismiss(ListView listView, int[] reverseSortedPositions) {
        						String transactionString = null;
        						for (int position : reverseSortedPositions) {
        							Map<String, String> tMap = transTimeList.get(position);
        							transactionString = tMap.get(TEXT1);
        							transTimeList.remove(tMap);
        						}
        						transAdapter.notifyDataSetChanged();
        						if (transactionString != null && transactionString != "") {
        							System.out.println(transactionString);
        							AccountHandler.removeTransactByString(transactionString);
        							balance.setText("Balance: " + AccountHandler.getBalanceString());
        							if (!AccountHandler.hasTransactions()) {
        								instruct.setText("Click \"+\" to add transaction");
        								instruct.setVisibility(0);
        							}
        						}
        					}
        					public boolean canDismiss(int position) {
        						return true;
        					}
        				});
        history.setOnTouchListener(touchListener);
        history.setOnScrollListener(touchListener.makeScrollListener());

        OnItemClickListener listener = new OnItemClickListener() {
        	@Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
//                if (touchListener.swipeDetected() && touchListener.getAction() == Action.RL) {
//                    Toast.makeText(getApplicationContext(), "swiped", Toast.LENGTH_SHORT).show();
//                }
            }
        };
        history.setOnItemClickListener(listener);
        
        List<Transaction> transacts = AccountHandler.getTransactions();
        if (transacts != null) {
            List<String> transList = new ArrayList<String>(transacts.size());
            transTimeList = new ArrayList<Map<String, String>>(transacts.size());
            
            if (transacts.isEmpty()) {
            	instruct.setVisibility(0);
                instruct.setText("Click \"+\" to add transaction");
            } else {
                instruct.setVisibility(8);
                Collections.sort(transacts);
                
                String balanceStr = AccountHandler.getBalanceString();
             
                balance.setText("Balance: " + balanceStr);
                for (int i = 0; i < transacts.size(); i++) {
                    Transaction transact = transacts.get(i);
                    if (transact != null) {
                        transList.add(transact.toString());
                        Map<String, String> transAndTime = new HashMap<String, String>();
                        transAndTime.put(TEXT1, transList.get(i));
                        TimeData timeData = transact.getTimeOfTransaction();
                        if (timeData == null) {
                            transAndTime.put(TEXT2, "");
                        } else {
                            transAndTime.put(TEXT2, timeData.toString());
                        }
                        transTimeList.add(transAndTime);
                    }
                }
            }
           transAdapter = new SimpleAdapter(this,
                    transTimeList,
                    android.R.layout.simple_list_item_2, 
                    fromMapKey, toLayoutId);
            history.setAdapter(transAdapter);
        }
        
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
}
