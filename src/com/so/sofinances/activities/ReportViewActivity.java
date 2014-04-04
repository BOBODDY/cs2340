package com.so.sofinances.activities;

import java.util.HashMap;
import java.util.Map;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.so.sofinances.R;
import com.so.sofinances.ReportGenerator;
import com.so.sofinances.model.Report;
import com.so.sofinances.model.TimeData;

public class ReportViewActivity extends Activity {
    
	private TextView reporter;
    
	private TimeData start;
	private TimeData end;
    
	private Report currentReport;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_report_view);
        
        reporter = (TextView) findViewById(R.id.reporter);
        reporter.setText("");
        
        Intent incomingData = this.getIntent();
        
        if (incomingData != null) {
            start = (TimeData) incomingData.getSerializableExtra("startDate");
            end = (TimeData) incomingData.getSerializableExtra("endDate");
        }
        
        new ReportGeneratorTask().execute("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.report_view, menu);
        return true;
    }
    
    public void viewChart(View view) {
        createPieChart((HashMap<String, Double>) currentReport.getData().get(0));
    }
    
    /**
     * Private method for generating the pie chart and launching the activity
     * 
     * @param data A map of the category/title of each 
     * line in the data connected to the
     *  
     * amount of that category
     */
    private void createPieChart(Map<String, Double> data) {
	    
	      // Pie Chart Section Names
    	String[] code = data.keySet().toArray(new String[0]);
	    
	      // Pie Chart Section Value
    	Double[] temp = new Double[code.length];
    	for (int i = 0; i < code.length; i++) {
    	    temp[i] = data.get(code[i]);
    	}
    	double[] distribution = new double[temp.length];
    	
    	System.arraycopy(temp, 0, temp, 0, temp.length);
	    
	      // Color of each Pie Chart Sections
    	int[] colors = {Color.GRAY, Color.GREEN, Color.RED, Color.YELLOW, Color.BLUE};
	    
	      // Instantiating CategorySeries to plot Pie Chart
    	CategorySeries distSeries = new CategorySeries(
    			"Withdrawals");
    	for (int i = 0; i < distribution.length; i++) {
	       // Adding a slice with its values and name to the Pie Chart
    	    distSeries.add(code[i], distribution[i]);
    	}
	      // Instantiating a renderer for the Pie Chart
    	DefaultRenderer defaultRenderer = new DefaultRenderer();
    	for (int i = 0; i < distribution.length; i++) {
    	    SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
    	    seriesRenderer.setColor(colors[i]);
    	    seriesRenderer.setDisplayChartValues(true);
		      // Adding a renderer for a slice
    	    defaultRenderer.addSeriesRenderer(seriesRenderer);
    	}
    	defaultRenderer.setLegendTextSize(30);
    	defaultRenderer.setChartTitle("Withdrawals");
    	defaultRenderer.setChartTitleTextSize(20);
    	defaultRenderer.setZoomButtonsVisible(true);
    	defaultRenderer.setBackgroundColor(45454545);
	    
	      // Creating an intent to plot bar chart using dataset and
	      // multipleRenderer
    	Intent intent = ChartFactory.getPieChartIntent(getBaseContext(),
	        distSeries, defaultRenderer,
	        "PieChart");
	    
	      // Start Activity
    	startActivity(intent);
    }

    private class ReportGeneratorTask extends AsyncTask<String, Void, Report> {

        @Override
        protected Report doInBackground(String... params) {
            return ReportGenerator.spendingCategoryReport(start, end);
        }
        
        protected void onPostExecute(Report result) {
            ProgressBar progress = (ProgressBar) findViewById(R.id.report_loading);
            progress.setVisibility(View.INVISIBLE);
            
            reporter.setText(result.getTitle() + "\n" + result.toString());
            
            currentReport = result;
        }
    }
}