package com.so.sofinances;

public class TimeData {
	private int hours, minutes, month, day, year;
	
	public TimeData(int hours, int minutes, int month, int day, int year) {
		this.hours = hours;
		this.minutes = minutes;
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	public String toString() {
		return String.format("%2d/%2d/%4d %2d:%2d", month, day, year, hours, minutes);
	}
	
}
