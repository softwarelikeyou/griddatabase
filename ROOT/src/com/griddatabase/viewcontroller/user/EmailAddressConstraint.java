package com.griddatabase.viewcontroller.user;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;

import com.griddatabase.model.entity.User;
import com.griddatabase.model.facade.UserFacade;
import com.griddatabase.viewcontroller.WebConstants;
import com.softwarelikeyou.exception.UserException;

public class EmailAddressConstraint implements Constraint {
	
	public void validate(Component comp, Object value) throws WrongValueException {
		if( value == null || ((String) value).trim().isEmpty() )
			throw new WrongValueException(comp, "Email can not be empty");
		
		String string = ((String) value).trim();
		if( !WebConstants.EMAIL_ADDRESS_PATTERN.matcher(string).matches() )
			throw new WrongValueException(comp, "Invalid email address");
	
		User user = null;
		try {
			user = UserFacade.findByUsername(value.toString());
		}
		catch (UserException e) {
			throw new WrongValueException(comp, "Invalid email address");
		}
		
		if( user != null ) { 
		    throw new WrongValueException(comp, "Email address in use");	
		}
	}
}
