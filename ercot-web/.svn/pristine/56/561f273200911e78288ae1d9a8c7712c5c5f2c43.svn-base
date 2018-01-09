package com.softwarelikeyou.viewcontroller.converter;

import java.text.DecimalFormat;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

public class FloatPrecisionConverter implements TypeConverter {

	DecimalFormat format = new DecimalFormat("###0.00");
	
	public Object coerceToBean(Object arg0, Component arg1) {
		return null;
	}

	public Object coerceToUi(Object object, Component arg1) {
		if( object == null )
			return format.format(0);
		if( object instanceof Float ) 
			return format.format((Float) object);
		else
        	throw new IllegalArgumentException("object:" + object + " of type:" + object.getClass().getName() + "; expected numeric datatype");
	}
}
