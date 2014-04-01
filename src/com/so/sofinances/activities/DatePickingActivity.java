package com.so.sofinances.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;

import com.so.sofinances.R;
import com.so.sofinances.model.TimeData;

public class DatePickingActivity extends Activity {
	
	DatePicker start, end;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_picking);
		
		start = (DatePicker) findViewById(R.id.datePickerStart);
		end = (DatePicker) findViewById(R.id.datePickerEnd);
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
	 * @param v The View that started this method
	 */
	public void finishDate(View v) {
		int startDay = start.getDayOfMonth();
		int startMonth = start.getMonth() + 1;
		int startYear = start.getYear();
		
		int endDay = end.getDayOfMonth();
		int endMonth = end.getMonth() + 1;
		int endYear = end.getYear();
		
		TimeData start = new TimeData(startMonth, startDay, startYear);
		TimeData end = new TimeData(endMonth, endDay, endYear);
		
		Intent result = this.getIntent();
		result.putExtra("startDate", start);
		result.putExtra("endDate", end);
		
		this.setResult(RESULT_OK, result);
		finish();
	}

}
