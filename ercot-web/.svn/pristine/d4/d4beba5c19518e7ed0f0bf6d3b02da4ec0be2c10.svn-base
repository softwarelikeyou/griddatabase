package com.softwarelikeyou.viewcontroller.signup;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;

import com.softwarelikeyou.exception.UserException;
import com.softwarelikeyou.facade.UserFacade;
import com.softwarelikeyou.model.entity.User;
import com.softwarelikeyou.viewcontroller.ELFunctions;
import com.softwarelikeyou.I18NStrings;
import com.softwarelikeyou.viewcontroller.WebConstants;

public class EmailAddressConstraint implements Constraint {
	
	public void validate(Component comp, Object value) throws WrongValueException {
		if( value == null || ((String) value).trim().isEmpty() )
			throw new WrongValueException(comp, ELFunctions.getMessage(I18NStrings.EMAIL_ADDRESS_CANNOT_BE_EMPTY));
		
		String string = ((String) value).trim();
		if( !WebConstants.EMAIL_ADDRESS_PATTERN.matcher(string).matches() )
			throw new WrongValueException(comp, ELFunctions.getMessage(I18NStrings.INVALID_EMAIL_ADDRESS));
	
		User user = null;
		try {
			user = UserFacade.findByUsername(value.toString());
		}
		catch (UserException e) {
			throw new WrongValueException(comp, ELFunctions.getMessage(I18NStrings.INVALID_EMAIL_ADDRESS));
		}
		
		if( user != null ) { 
		    throw new WrongValueException(comp, ELFunctions.getMessage(I18NStrings.EMAIL_ADDRESS_IS_IN_USE));	
		}
	}
}
