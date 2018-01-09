package com.softwarelikeyou.viewcontroller.converter;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

import com.softwarelikeyou.viewcontroller.SessionUtil;

public class ShortDateConverter implements TypeConverter {
	
	protected static final String STAT_FIELD_UNKNOWN = "--";
	
	public static String convertDate(Date date, Locale locale) {
		if (date == null) return STAT_FIELD_UNKNOWN;
		return DateFormat.getDateInstance(DateFormat.SHORT, locale).format(date);
		//return DateFormat.getDateInstance().format(date);
	}

	public Object coerceToBean(Object val, Component comp) {
		return null;
	}

	public Object coerceToUi(Object value, Component comp) {
		
		if( value == null ) return STAT_FIELD_UNKNOWN;
		
		if( value instanceof Date ) { 
			Date date = (Date) value;
			return convertDate(date, SessionUtil.getSelectedLocale());		
		}
		
       	throw new IllegalArgumentException("object:" + value + " of type:" + value.getClass().getName() + "; expected type: " + Date.class.getName());
	}

}
