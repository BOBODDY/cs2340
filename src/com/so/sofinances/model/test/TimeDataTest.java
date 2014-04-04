package com.so.sofinances.model.test;

import junit.framework.TestCase;

import com.so.sofinances.model.TimeData;

public class TimeDataTest extends TestCase {
	
	public void testSameTime() {
		TimeData time1 = new TimeData(4,4,2014);
		TimeData time2 = new TimeData(4,4,2014);
		
		assertEquals(time1.compareTo(time2), 0);
	}
	
	public void testDifferentTime() {
		TimeData time1 = new TimeData(5,12,2012);
		TimeData time2 = new TimeData(6,20,2006);
		
		assertEquals(time1.compareTo(time2), -1);
	}
	
	public void testYearBefore() {
		TimeData time1 = new TimeData(4,4,2014);
		TimeData time2 = new TimeData(4,4,2013);
		
		assertEquals(time1.compareTo(time2), -1);
	}
	
	public void testYearAfter() {
		TimeData time1 = new TimeData(4,4,2014);
		TimeData time2 = new TimeData(4,4,2015);
		
		assertEquals(time1.compareTo(time2), 1);
	}
	
	public void testMonthBefore() {
		TimeData time1 = new TimeData(4,4,2014);
		TimeData time2 = new TimeData(3,4,2014);
		
		assertEquals(time1.compareTo(time2), -1);
	}
	
	public void testMonthAfter() {
		TimeData time1 = new TimeData(4,4,2014);
		TimeData time2 = new TimeData(5,4,2014);
		
		assertEquals(time1.compareTo(time2), 1);
	}
	
	public void testDayBefore() {
		TimeData time1 = new TimeData(4,4,2014);
		TimeData time2 = new TimeData(4,3,2014);
		
		assertEquals(time1.compareTo(time2), -1);
	}
	
	public void testDayAfter() {
		TimeData time1 = new TimeData(4,4,2014);
		TimeData time2 = new TimeData(4,5,2014);
		
		assertEquals(time1.compareTo(time2), 1);
	}
}
