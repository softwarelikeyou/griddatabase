package com.softwarelikeyou.viewcontroller.signup;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.softwarelikeyou.viewcontroller.ELFunctions;
import com.softwarelikeyou.I18NStrings;
import com.softwarelikeyou.viewcontroller.WebUtil;

public class PasswordConstraint implements Constraint {

	public void validate(Component comp, Object value) throws WrongValueException {

		if( value == null || ((String) value).trim().equals("") )
			throw new WrongValueException(comp, ELFunctions.getMessage(I18NStrings.PASSWORD_CANNOT_BE_EMPTY));

		Window window = (Window) WebUtil.getParentByClass(comp, Window.class);

		((Textbox)Path.getComponent( Path.getPath(window) + "/" + comp.getId() )).setRawValue(value);

		String otherValue = null;
		
		if( comp.getId().equals("password") )
			otherValue = ((Textbox) Path.getComponent(Path.getPath(window) + "/confirmPassword")).getRawText();
		else
			otherValue = ((Textbox) Path.getComponent(Path.getPath(window) + "/signupPassword")).getRawText();

		if( otherValue == null || otherValue.trim().equals("") )
			return;

		if ( !value.toString().equals(otherValue) )
			throw new WrongValueException(comp, ELFunctions.getMessage(I18NStrings.PASSWORDS_DONT_MATCH));
		
	}

}
