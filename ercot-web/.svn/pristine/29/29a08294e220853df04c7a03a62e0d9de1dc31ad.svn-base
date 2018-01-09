package com.softwarelikeyou.viewcontroller.signup;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;

import com.softwarelikeyou.I18NStrings;
import com.softwarelikeyou.viewcontroller.ELFunctions;


public class CompanyConstraint implements Constraint {
	
	public void validate(Component comp, Object value) throws WrongValueException {
		if( value == null || ((String) value).trim().isEmpty() )
			throw new WrongValueException(comp, ELFunctions.getMessage(I18NStrings.COMPANY_CAN_NOT_BE_NULL));
	}
}
