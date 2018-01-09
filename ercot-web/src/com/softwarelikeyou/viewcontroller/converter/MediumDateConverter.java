package com.softwarelikeyou.viewcontroller.converter;

import java.text.DateFormat;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

import com.softwarelikeyou.viewcontroller.SessionUtil;

public class MediumDateConverter implements TypeConverter {

	public static String getString(Date date) { 
		return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, SessionUtil.getSelectedLocale()).format(date);
	}
	
	public Object coerceToBean(Object val, Component comp) {
		return null;
	}

	public Object coerceToUi(Object value, Component comp) {
		
		if( value == null ) return "";
		
		if( value instanceof Date ) { 
			Date date = (Date) value;
			return getString(date);
		}

		throw new IllegalArgumentException("object:" + value + " of type:" + value.getClass().getName() + "; expected type: " + Date.class.getName());        
	}

}
