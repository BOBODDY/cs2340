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
					// !!!!!!!!!!!!!!!!!!!1
					// I hope the hours are in 24-hour mode
					// Otherwise we need another way to determine AM vs PM
					// I'll assume 24-hour
					if(time.getHours() < this.getHours()) {
						return -1;
					} else if(time.getHours() == this.getHours()) {
						if(time.getMinutes() < this.getMinutes()) {
							return -1;
						} else if(time.getMinutes() == this.getMinutes()) {
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
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
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
