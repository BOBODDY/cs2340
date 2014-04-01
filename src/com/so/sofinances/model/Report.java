package com.so.sofinances.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Report {
	
	String title;
	
	ArrayList<Object> data;

	public Report() {
		title = "";
		
		data = new ArrayList<Object>();
	}
	
	/**
	 * @param title New title of report
	 */
	public void setTitle(String title) {
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
	public ArrayList<Object> getData() {
		return data;
	}
	
	public String toString() {
		String res = "";
		HashMap<String, Double> dat = (HashMap<String, Double>) data.get(0);
		
		for(String cat:dat.keySet()) {
			res += cat + " $" + dat.get(cat) + "\n";
		}
		
		return res;
	}

}
