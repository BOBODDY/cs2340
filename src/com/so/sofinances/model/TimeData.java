package com.so.sofinances.model;

import java.io.Serializable;
import java.util.Locale;

public class TimeData implements Serializable {
    
    private static final long serialVersionUID = 5024086716108214641L;
    
    private int month;
    private int day;
    private int year;
    
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
    	int result = 0;
        if (time.getYear() < this.getYear()) {
            result = -1;
        } else if (time.getYear() == this.getYear()) {
            if (time.getMonth() < this.getMonth()) {
                result = -1;
            } else if (time.getMonth() == this.getMonth()) {
                if (time.getDay() < this.getDay()) {
                    result = -1;
                } else if (time.getDay() == this.getDay()) {
                    result = 0;
                } else {
                    result = 1;
                }
            } else {
                result = 1;
            }
        } else {
            result = 1;
        }
        
        return result;
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
