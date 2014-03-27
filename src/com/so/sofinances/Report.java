package com.so.sofinances;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class Report {
	
	String title;
	
	Time date;
	
	ArrayList<Object> data;

	public Report() {
		title = "";
		
		date = null;
		
		data = new ArrayList<Object>();
	}
	
	public void addData(Object data) {
		this.data.add(data);
	}
	
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
