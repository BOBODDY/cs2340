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

    private static final long serialVersionUID = 5024086716108214641L;

    /**
     * Integer values for day month and year.
     */
    private int month;
    private int day;
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

    /** 
     * Compares 2 TimeData objects
     * 
     * Ex: time1 is April 4, 2014
     * time2 is March 15, 2014
     * 
     * time1.compareTo(time2) will return a value less than 0
     * 
     * @param time Other time object to compare with
     * @return Greater than 0 if given TimeData comes after this TimeData
     * 0 if given TimeData is the same day as this TimeData
     * Less than 0 if given TimeData is before this TimeData
     */
    public int compareTo(TimeData time) {
    	/*if (time.getYear() < this.getYear()) {
            return -1;
        } else if (time.getYear() == this.getYear()) {
            if (time.getMonth() < this.getMonth()) {
                return -1;
            } else if (time.getMonth() == this.getMonth()) {
                if (time.getDay() < this.getDay()) {
                    return -1;
                } else if (time.getDay() == this.getDay()) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        } else {
            return 1;
        } */

        // This implementation looks much cleaner. -Joe
        int yearDiff = time.year - this.year;
        int monthDiff = time.month - this.month;
        int dayDiff = time.day - this.day;
        if (yearDiff != 0) {
            return yearDiff;
        }
        if (monthDiff != 0) {
            return monthDiff;
        }
        return dayDiff;
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
