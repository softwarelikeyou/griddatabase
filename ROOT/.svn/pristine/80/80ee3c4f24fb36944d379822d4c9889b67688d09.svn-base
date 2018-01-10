package com.griddatabase.viewcontroller.user;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

import com.griddatabase.model.entity.User;

public class UserTypeConverter implements TypeConverter {

	public static String getString(User user) { 
		if (user.isAdministrator()) 
			return "Administrator";
			
		if (user.isPremium()) 
			return "Premium";
		
		return "Freemium";
	}
	
	public Object coerceToBean(Object arg0, Component arg1) {
		return null;
	}

	public Object coerceToUi(Object value, Component comp) {
		if( value == null ) return "";
		
		if( value instanceof User ) 
			return getString((User) value);			
		
		throw new IllegalArgumentException("object:" + value + " of type:" + value.getClass().getName() + "; expected type: " + User.class.getName() + " or " + User.class.getName());
	}

}
