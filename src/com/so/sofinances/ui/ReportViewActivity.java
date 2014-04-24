package com.so.sofinances.ui;

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
import com.so.sofinances.model.Report;
import com.so.sofinances.model.TimeData;
import com.so.sofinances.utilities.ReportGenerator;

/** Activity for displaying reports. 
 * @author kodyPC
 *
 */
public class ReportViewActivity extends Activity {
	
	public static final int SPENDING_REPORT = 12;
	public static final int CASH_FLOW_REPORT = 32;
	public static final int INCOME_REPORT = 61;
    
	/**
	 * textview used to display the report.
	 */
    private TextView reporter;
    
	/**
	 * the start date for the report.
	 */
    private TimeData start;
	/**
	 * the end date for the report.
	 */
    private TimeData end;
    
	/**
	 * the actual report.
	 */
    private Report currentReport;
    
    /**
     * the report type
     */
    private int reportType;

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
            
            reportType = (int) incomingData.getIntExtra("reportType", 0); //another random value
        }
        
        new ReportGeneratorTask().execute(reportType);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.report_view, menu);
        return true;
    }
    
    /**displays a piechart of the report data.
     * @param view the view.
     */
    public void viewChart(View view) {
        createPieChart((HashMap<String, Double>) currentReport.getData().get(0));
    }
    
    /**
     * Private method for generating the pie chart and launching the activity.
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
    	Double[] distribution = new Double[temp.length];
    	
    	System.arraycopy(temp, 0, distribution, 0, temp.length);
	    
	      // Color of each Pie Chart Sections
    	int[] colors = {Color.GREEN, Color.RED, Color.YELLOW, Color.BLUE,
    			Color.MAGENTA, Color.LTGRAY, Color.GRAY};
	    
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

    /** creates a new thread for the report generation.
     * @author kodyPC
     *
     */
    private class ReportGeneratorTask extends AsyncTask<Integer, Void, Report> {

        @Override
        protected Report doInBackground(Integer... params) {
        	int param = params[0];
        	if(param == SPENDING_REPORT) {
            	return ReportGenerator.spendingCategoryReport(start, end);
        	} else if(param == CASH_FLOW_REPORT) {
        		return ReportGenerator.cashFlowReport(start, end);
        	}else if(param == INCOME_REPORT) {
        		return ReportGenerator.incomeCategoryReport(start, end);
        	} else {
        		return null;
        	}
        }
        
        /**
         * sets the current report equal to the generated report.
         * @param result the generated report
         */
        protected void onPostExecute(Report result) {
            ProgressBar progress = (ProgressBar) findViewById(R.id.report_loading);
            progress.setVisibility(View.INVISIBLE);
            
            reporter.setText(result.getTitle() + "\n" + result.toString());
            
            currentReport = result;
        }
    }
}