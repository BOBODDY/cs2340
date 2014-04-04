package com.so.sofinances.activities;

import com.so.sofinances.R;
import com.so.sofinances.handler.AccountHandler;
import com.so.sofinances.model.TimeData;
import com.so.sofinances.model.Transaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

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

        name = (EditText) findViewById(R.id.add_trans_name);

        amount = (EditText) findViewById(R.id.add_trans_amount);
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

            RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
            if (rg.getCheckedRadioButtonId() != -1) {
                int id = rg.getCheckedRadioButtonId();
                View radioButton = rg.findViewById(id);
                int radioId = rg.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) rg.getChildAt(radioId);
                transType = btn.getText().toString();
            }
            Transaction toAdd = null;

            int day = cal.getDayOfMonth();
            int month = cal.getMonth() + 1;
            int year = cal.getYear();

            TimeData dateOfTrans = new TimeData(month, day, year);
            //System.out.println(dateOfTrans);

            Spinner categories = (Spinner) findViewById(R.id.category_spinner1);

            String category = (String) categories.getSelectedItem();

            boolean isWithdrawal = transType.equals("Withdrawal");

            if (isWithdrawal) {
                amount *= -1;
            }

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
