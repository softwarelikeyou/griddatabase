package com.softwarelikeyou.viewcontroller.converter;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

public class NegativePriceRedLabelConverter implements TypeConverter {
	
	protected static final String STAT_FIELD_UNKNOWN = "--";

	public Object coerceToBean(Object val, Component comp) {
		return null;
	}

	public Object coerceToUi(Object value, Component comp) {
		
		if( value == null ) return STAT_FIELD_UNKNOWN;
		
		if( value instanceof Float ) { 
			Float price = (Float) value;
			
			if (price < 0f)
				return "color: red; text-align: right;";
			
			return "text-align: right;";
		}
		
       	throw new IllegalArgumentException("object:" + value + " of type:" + value.getClass().getName() + "; expected type: " + Float.class.getName());
	}

}
