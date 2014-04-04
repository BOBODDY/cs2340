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

    @Override
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
