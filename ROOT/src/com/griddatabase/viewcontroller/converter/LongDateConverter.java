package com.griddatabase.viewcontroller.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

public class LongDateConverter implements TypeConverter {
	
	protected static final String STAT_FIELD_UNKNOWN = "--";
	
	protected static DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy kk:hh:ss");
	
	public static String convertDate(Date date) {
		if (date == null) return STAT_FIELD_UNKNOWN;
		return sdf.format(date);
	}

	public Object coerceToBean(Object val, Component comp) {
		return null;
	}

	public Object coerceToUi(Object value, Component comp) {
		
		if( value == null ) return STAT_FIELD_UNKNOWN;
		
		if( value instanceof Date ) { 
			Date date = (Date) value;
			return convertDate(date);		
		}
		
       	throw new IllegalArgumentException("object:" + value + " of type:" + value.getClass().getName() + "; expected type: " + Date.class.getName());
	}

}
