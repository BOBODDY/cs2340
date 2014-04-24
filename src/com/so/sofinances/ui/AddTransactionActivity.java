package com.so.sofinances.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

import com.so.sofinances.R;
import com.so.sofinances.controllers.AccountHandler;
import com.so.sofinances.model.TimeData;
import com.so.sofinances.model.Transaction;

/**
 * UI class for adding new transaction on the current account. Contains fields
 * for name, amount, type, and date. App user may choose submit or cancel, and
 * in either case, they are returned to the AccountHomeActivity from which they
 * entered.
 *
 * @author Joseph Rossi
 * @version 1.0 4/3/2014
 */
public class AddTransactionActivity extends Activity {

	/**
	 * A spinner containing the withdrawal categories
	 */
	private Spinner categories;
	
	/**
	 * A RadioGroup that chooses between Withdrawal and Deposit
	 */
	private RadioGroup radios;
	
	/**
	 * the name of the transaction.
	 */
    private EditText name;
	/**
	 * the amount of the transaction.
	 */
    private EditText amount;

	/**
	 * used for picking the transaction time.
	 */
    private DatePicker cal;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_add_transaction);

        cal = (DatePicker) findViewById(R.id.datePicker1);

        name = (EditText) findViewById(R.id.add_transact_name);

        amount = (EditText) findViewById(R.id.add_trans_amount);
        
        radios = (RadioGroup) findViewById(R.id.radioGroup1);
        radios.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId == R.id.radio_deposit) {
					categories.setVisibility(View.GONE);
				} else if(checkedId == R.id.radio_withdraw) {
					categories.setVisibility(View.VISIBLE);
				}
			}
        });
        
        categories = (Spinner) findViewById(R.id.category_spinner1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_transaction, menu);
        return true;
    }

    /**
     * Returns app user to the previous activity if they select "cancel".
     *
     * @param v the current View
     */
    public void cancelAdd(View v) {
        Intent i = new Intent(getApplicationContext(), AccountHomeActivity.class);
        startActivity(i);
    }

    /**
     * Saves the entered data as a new transaction if the user selects "submit".
     *
     * @param v the current View
     */
    public void finishTransaction(View v) {

        String transName = name.getText().toString();

        if (transName == null || transName.equals("")) {
            Intent i = new Intent(getApplicationContext(), AccountHomeActivity.class);
            startActivity(i);
            finish();
        }

        String amountStr = amount.getText().toString();
        if (amountStr != null && !(amountStr.equals(""))) {
            double amount = Double.parseDouble(amountStr);

            String transType = "";

            if (radios.getCheckedRadioButtonId() != -1) {
                int id = radios.getCheckedRadioButtonId();
                View radioButton = radios.findViewById(id);
                int radioId = radios.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) radios.getChildAt(radioId);
                transType = btn.getText().toString();
            }
            Transaction toAdd = null;

            int day = cal.getDayOfMonth();
            int month = cal.getMonth() + 1;
            int year = cal.getYear();

            TimeData dateOfTrans = new TimeData(month, day, year);
            
            String category = "";
            if(categories.getVisibility() != View.GONE) {
            	category = (String) categories.getSelectedItem();
            }

            boolean isWithdrawal = transType.equals("Withdrawal");

            toAdd = new Transaction(dateOfTrans, amount, transName, category, isWithdrawal);

            AccountHandler.addTransaction(toAdd);

            Intent i = new Intent(getApplicationContext(), AccountHomeActivity.class);
            //DBHandler.db().store(UserHandler.getCU());
            //DBHandler.db().commit();
            startActivity(i);
            finish();
        }

    }

}
