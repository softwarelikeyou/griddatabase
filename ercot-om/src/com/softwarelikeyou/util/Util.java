package com.softwarelikeyou.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static Date stringToDate(final String source, final String format) throws ParseException {
		Date result = null;
        DateFormat df = new SimpleDateFormat(format);            
	    result = df.parse(source);
		return result;
	}
	
	public static Float toFloat(final String value) throws NumberFormatException {
		return Float.valueOf(value);
	}
}
