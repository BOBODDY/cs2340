package com.so.sofinances;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ReportViewActivity extends Activity {
	
	TextView reporter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_view);
		
		reporter = (TextView) findViewById(R.id.reporter);
		reporter.setText("");
		
		new ReportGeneratorTask().execute("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report_view, menu);
		return true;
	}

	private class ReportGeneratorTask extends AsyncTask<String, Void, Report> {

		@Override
		protected Report doInBackground(String... params) {
			return ReportGenerator.spendingCategoryReport();
		}
		
		protected void onPostExecute(Report result) {
			ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar1);
			pb.setVisibility(View.INVISIBLE);
			
			reporter.setText(result.toString());
		}
	}
}