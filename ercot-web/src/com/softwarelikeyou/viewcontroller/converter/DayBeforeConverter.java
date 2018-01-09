package com.softwarelikeyou.viewcontroller.converter;

import java.util.Calendar;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

public class DayBeforeConverter implements TypeConverter {

	public Object coerceToBean(Object val, Component comp) {
		return null;
	}

	public Object coerceToUi(Object value, Component comp) {
				
		if( value instanceof Date ) { 
			Date date = (Date) value;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
			return calendar.getTime();		
		}
		
       	throw new IllegalArgumentException("object:" + value + " of type:" + value.getClass().getName() + "; expected type: " + Date.class.getName());
	}

}
