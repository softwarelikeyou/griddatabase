
package com.softwarelikeyou;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.softwarelikeyou.util.Util;

import junit.framework.TestCase;

public class DateCompareTestCase extends TestCase {

	private List<Date> list = new ArrayList<Date>();
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	
	private static String INTERVALENDING_FORMAT = "MM/dd/yyyy kk:mm:ss";
	
	public void testCompare() {
		
		try {
	        list.add(format.parse("20120919"));
		    list.add(format.parse("20120913"));
	   	    list.add(format.parse("20120916"));  
		    list.add(format.parse("20120914"));  
		    list.add(format.parse("20120917"));  
		    list.add(format.parse("20120915"));  
		    list.add(format.parse("20120918"));  
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		Collections.sort(list, new Comparator<Date>() {
			@Override
			public int compare(Date date1, Date date2) {
		        if (date1.getTime() < date2.getTime()) 
		        	return -1;
		        else if (date1.getTime() > date2.getTime()) 
		        	return 1;
		        else 
		        	return 0;
			}
		});
		
		System.out.println(list.toString());
	}

	public void test24Hour() {
		try {
		    Calendar calendar = Calendar.getInstance();
	        calendar.setTime(Util.stringToDate("09/17/2012 00:00:00", INTERVALENDING_FORMAT));
        	if (calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 0 && calendar.get(Calendar.SECOND) == 0)
        		System.out.println("true");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	    
	}
	
	public void testMillisecond() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2004);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(calendar.getTimeInMillis());
		System.out.println(calendar.getTime().toString());
		Date date = new Date(calendar.getTimeInMillis());
		System.out.println(date.toString());
	}
	
}
