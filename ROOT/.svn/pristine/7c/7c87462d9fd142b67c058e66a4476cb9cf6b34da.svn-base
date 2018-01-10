package com.griddatabase.viewcontroller.freemium.ercot;

import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

import com.softwarelikeyou.model.entity.Daily;

public class TypeLabelConverter implements TypeConverter {
	
	protected static final String STAT_FIELD_UNKNOWN = "--";

	public Object coerceToBean(Object val, Component comp) {
		return null;
	}

	public Object coerceToUi(Object value, Component comp) {
		
		if( value == null ) return STAT_FIELD_UNKNOWN;
		
		if( value instanceof Daily ) { 
			Daily daily = (Daily) value;
			
			String type = "";
			switch (daily.getType()) {
			case All:
				type = "Daily";
				break;
			case Peak:
				type = "Peak HE7-HE22";
				break;
			case OffPeak:
				type = "Off Peak";
				break;
			}
			
			return daily.getName() + " " + type;
		}
		
       	throw new IllegalArgumentException("object:" + value + " of type:" + value.getClass().getName() + "; expected type: " + Date.class.getName());
	}

}
