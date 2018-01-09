package com.softwarelikeyou.viewcontroller.signup;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;

import com.softwarelikeyou.viewcontroller.ELFunctions;
import com.softwarelikeyou.I18NStrings;

public class NameConstraint implements Constraint {

	public void validate(Component comp, Object value) throws WrongValueException {
		if( value == null || ((String) value).trim().isEmpty() )
			throw new WrongValueException(comp, ELFunctions.getMessage(I18NStrings.NAME_CANNOT_BE_EMPTY));
	}
}
