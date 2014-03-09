package com.so.sofinances;

import java.sql.Time;
import java.util.ArrayList;

public class Report {
	
	String title;
	
	Time date;
	
	ArrayList data;

	public Report() {
		title = "";
		
		date = null;
		
		data = new ArrayList();
	}
	
	public void addData(Object data) {
		this.data.add(data);
	}
	
	public String toString() {
		String res = "";
		
		for(Object o:data) {
			res += (String) o;
		}
		
		return res;
	}

}
