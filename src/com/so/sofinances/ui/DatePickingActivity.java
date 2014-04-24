package com.so.sofinances.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.so.sofinances.R;
import com.so.sofinances.model.TimeData;

/** facilitates picking of dates.
 * @author kodyPC
 *
 */
public class DatePickingActivity extends Activity {

    /**
     * the starting date.
     */
    private DatePicker start;
    /**
     * the ending date.
     */
    private DatePicker end;
    /**
     * the report spinner
     */
    private Spinner reportType;

    @Override
    protected void onCreate(Bundle savedState) {
    	super.onCreate(savedState);
    	setContentView(R.layout.activity_date_picking);
		
    	start = (DatePicker) findViewById(R.id.datePickerStart);
    	end = (DatePicker) findViewById(R.id.datePickerEnd);
    	reportType = (Spinner) findViewById(R.id.report_type_spinner);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.date_picking, menu);
    	return true;
    }
	
	/**
	 * The onClick method for the button to finish picking the date.
	 * 
	 * @param view The View that started this method
	 */
    public void finishDate(View view) {
    	int startDay = start.getDayOfMonth();
    	int startMonth = start.getMonth() + 1;
    	int startYear = start.getYear();
		
    	int endDay = end.getDayOfMonth();
    	int endMonth = end.getMonth() + 1;
    	int endYear = end.getYear();
		
        TimeData start = new TimeData(startMonth, startDay, startYear);
        TimeData end = new TimeData(endMonth, endDay, endYear);
        
        String report = (String) reportType.getSelectedItem(); 
		
    	Intent result = this.getIntent();
    	result.putExtra("startDate", start);
    	result.putExtra("endDate", end);
    	result.putExtra("reportType", report);
		
    	this.setResult(RESULT_OK, result);
    	finish();
    }

}
