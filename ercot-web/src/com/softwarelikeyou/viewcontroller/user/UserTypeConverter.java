package com.softwarelikeyou.viewcontroller.user;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

import com.softwarelikeyou.I18NStrings;
import com.softwarelikeyou.model.entity.User;
import com.softwarelikeyou.viewcontroller.ELFunctions;

public class UserTypeConverter implements TypeConverter {

	public static String getString(User user) { 
		if (user.isAdministrator()) 
			return ELFunctions.getLabel(I18NStrings.ADMINISTRATOR) ;
			
		if (user.isPremium()) 
			return ELFunctions.getLabel(I18NStrings.PREMIUM);
		
		return ELFunctions.getLabel(I18NStrings.FREEMIUM);
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
