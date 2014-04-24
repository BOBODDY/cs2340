package com.so.sofinances.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.so.sofinances.utilities.CurrencyFormat;

/** Contains info for report.
 * @author kodyPC
 *
 */
public class Report {
    
    /**
     * title of the report.
     */
    private String title;
    /**
     * list of data.
     */
    private final List<Object> data;

    
    /**
     * Constructor that sets empty title and data list.
     */
    public Report() {
        title = "";
        
        data = new ArrayList<Object>();
    }
    
    /**
     * @param title New title of report
     */
    public void setTitle(final String title) {
        this.title = title;
    }
    
    /**
     * @return Title of report
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * @param data Any object to add to the report
     */
    public void addData(Object data) {
        this.data.add(data);
    }
    
    /**
     * @return Report's data
     */
    public List<Object> getData() {
        return data;
    }
    
    /**
     * creates a string representation.
     * @return the string representation
     */
    public String toString() {
        String res = "";
        HashMap<String, Double> dat = (HashMap<String, Double>) data.get(0);
        
        for (String cat:dat.keySet()) {
            res += cat + " " + CurrencyFormat.format(dat.get(cat)) + "\n";
        }
        
        return res;
    }

}
