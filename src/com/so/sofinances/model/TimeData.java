package com.so.sofinances.model;

import java.io.Serializable;
import java.util.Locale;

/**
 * Class for storing and comparing day, month, and year integer data.
 *
 * @author  Joseph Rossi
 * @version 1.0 4/3/2014
 */
public class TimeData implements Comparable<TimeData>, Serializable {

    /**
     * the UID for passing time data.
     */
    private static final long serialVersionUID = 5024086716108214641L;

    /**
     * Integer values for day month and year.
     */
    private int month;
    /**
     * the Day.
     */
    private int day;
    /**
     * the year.
     */
    private int year;

    /**
     * Constructs a TimeData object with given data.
     *
     * @param month the month (1 - 12 for Jan - Dec)
     * @param day   the day of the month (ie 1 up to 31)
     * @param year  the year represented with 4 digits (ie 2014 not 14)
     */
    public TimeData(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%2d/%2d/%4d", month, day, year);
    }

    @Override
    public int compareTo(TimeData time) {
    	if(time == null) {
    		return 1;
    	}
        int yearDiff = this.year - time.year;
        int monthDiff = this.month - time.month;
        int dayDiff = this.day - time.day;
        if (yearDiff != 0) {
            return yearDiff;
        }
        if (monthDiff != 0) {
            return monthDiff;
        }
        return dayDiff;
    }

    @Override
    public boolean equals(Object o){
    	if(o == null) {
    		return false;
    	}
    	
    	TimeData time = (TimeData)o;
    	int yearDiff = this.year - time.year;
    	int monthDiff = this.month - time.month;
    	int dayDiff = this.day - time.day;
   		return (yearDiff == 0 && monthDiff == 0 && dayDiff == 0);
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
