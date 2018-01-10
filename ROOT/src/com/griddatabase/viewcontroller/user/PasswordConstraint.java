package com.griddatabase.viewcontroller.user;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.griddatabase.util.ZKUtil;

public class PasswordConstraint implements Constraint {

	public void validate(Component comp, Object value) throws WrongValueException {

		if( value == null || ((String) value).trim().equals("") )
			throw new WrongValueException(comp, "Password can not be empty");

		Window window = (Window) ZKUtil.getParentByClass(comp, Window.class);
		
		((Textbox)Path.getComponent( Path.getPath(window) + "/" + comp.getId() )).setRawValue(value);

		String otherValue = null;
		
		if( comp.getId().equals("confirmPassword") )
			otherValue = ((Textbox) Path.getComponent(Path.getPath(window) + "/password")).getRawText();
		else
			otherValue = ((Textbox) Path.getComponent(Path.getPath(window) + "/confirmPassword")).getRawText();

		if( otherValue == null || otherValue.trim().equals("") )
			return;

		if ( !value.toString().equals(otherValue) )
			throw new WrongValueException(comp, "Password do not match");
		
	}

}