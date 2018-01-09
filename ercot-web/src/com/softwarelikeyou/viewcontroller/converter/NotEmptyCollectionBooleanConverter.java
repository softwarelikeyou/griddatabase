package com.softwarelikeyou.viewcontroller.converter;

import java.util.Collection;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;
import org.zkoss.zul.ListModelList;

public class NotEmptyCollectionBooleanConverter implements TypeConverter {

	public Object coerceToBean(Object arg0, Component arg1) {
		return null;
	}

	public Object coerceToUi(Object value, Component comp) {

		if( value == null ) return false;
		
		if( value instanceof ListModelList ) value = ((ListModelList) value).getInnerList();
		
        if( value instanceof Collection ) {
        	return !((Collection<?>) value).isEmpty();
        } 
        else {
        	throw new IllegalArgumentException("object:" + value + " of type:" + value.getClass().getName() + "; expected type: " + Collection.class.getName());
        }		

	}

}
