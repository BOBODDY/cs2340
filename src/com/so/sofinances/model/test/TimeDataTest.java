package com.so.sofinances.model.test;

import junit.framework.TestCase;

import com.so.sofinances.model.TimeData;

public class TimeDataTest extends TestCase {
	
	public void testSameTime() {
		TimeData time1 = new TimeData(4,4,2014);
		TimeData time2 = new TimeData(4,4,2014);
		
		assertTrue(time1.compareTo(time2) == 0);
	}
	
	public void testDifferentTime() {
		TimeData time1 = new TimeData(5,12,2012);
		TimeData time2 = new TimeData(6,20,2006);
		
		assertTrue(time1.compareTo(time2) > 0);
	}
	
	public void testYearBefore() {
		TimeData time1 = new TimeData(4,4,2014);
		TimeData time2 = new TimeData(4,4,2013);
		
		assertTrue(time1.compareTo(time2) > 0);
	}
	
	public void testYearAfter() {
		TimeData time1 = new TimeData(4,4,2014);
		TimeData time2 = new TimeData(4,4,2015);
		
		assertTrue(time1.compareTo(time2) < 0);
	}
	
	public void testMonthBefore() {
		TimeData time1 = new TimeData(4,4,2014);
		TimeData time2 = new TimeData(3,4,2014);
		
		assertTrue(time1.compareTo(time2) > 0);
	}
	
	public void testMonthAfter() {
		TimeData time1 = new TimeData(4,4,2014);
		TimeData time2 = new TimeData(5,4,2014);
		
		assertTrue(time1.compareTo(time2) < 0);
	}
	
	public void testDayBefore() {
		TimeData time1 = new TimeData(4,4,2014);
		TimeData time2 = new TimeData(4,3,2014);
		
		assertTrue(time1.compareTo(time2) > 0);
	}
	
	public void testDayAfter() {
		TimeData time1 = new TimeData(4,4,2014);
		TimeData time2 = new TimeData(4,5,2014);
		
		assertTrue(time1.compareTo(time2) < 0);
	}
	
	public void testNullEquals() {
		TimeData time1 = new TimeData(4,9,2014);
		
		assertFalse(time1.equals(null));
	}
	
	public void testNull() {
		TimeData time1 = new TimeData(4,9,2014);
		int year = (Integer) null;
		int month = (Integer) null;
		int day = (Integer) null;
		TimeData time2 = new TimeData(month, day, year);
		
		assertTrue(time1.compareTo(time2) == 0);
	}
}
