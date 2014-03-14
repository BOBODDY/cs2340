package com.so.sofinances;

import java.io.Serializable;
import java.util.Locale;

public class TimeData implements Serializable {
	
	private static final long serialVersionUID = 5024086716108214641L;
	
	private int month, day, year;
	
	public TimeData(int month, int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	public String toString() {
		return String.format(Locale.getDefault(), "%2d/%2d/%4d", month, day, year);
	}
	
	/**
	 * 
	 * @param time TimeData object to compare with
	 * @return -1 if time comes before this TimeData, 0 if they're the same, 1 if time comes after this TimeData
	 */
	public int compareTo(TimeData time) {
		if(time.getYear() < this.getYear()) {
			return -1;
		} else if(time.getYear() == this.getYear()) {
			if(time.getMonth() < this.getMonth()) {
				return -1;
			} else if(time.getMonth() == this.getMonth()) {
				if(time.getDay() < this.getDay()) {
					return -1;
				} else if(time.getDay() == this.getDay()) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	
}
