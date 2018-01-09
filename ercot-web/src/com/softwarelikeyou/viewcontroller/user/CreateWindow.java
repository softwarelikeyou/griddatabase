package com.softwarelikeyou.viewcontroller.user;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.WrongValuesException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.softwarelikeyou.model.entity.User.UserType;
import com.softwarelikeyou.viewcontroller.ELFunctions;
import com.softwarelikeyou.I18NStrings;
import com.softwarelikeyou.viewcontroller.WebUtil;

public class CreateWindow extends Window implements AfterCompose {
	private static final long serialVersionUID = 1L;

	private Textbox username;
	private Textbox display;
	private Textbox password;
	private Textbox confirmPassword;
	private boolean passFocused;
	private boolean confirmFocused;
	private Radiogroup userType;
	
	public String getUsername() { 
		return username.getValue();
	}
	
	public String getDisplay() { 
		return display.getValue();
	}
	
	public String getPassword() { 
		return password.getValue();
	}
	
	public UserType getUserType() {
		return UserType.valueOf((String) userType.getSelectedItem().getValue());
	}
	
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
	}

	public void onFocus$password(Event event) {
		passFocused = true;
	}
	
	public void onFocus$confirmPassword(Event event) {
		confirmFocused = true;
	}
	
	public void validate() throws WrongValuesException { 
		
		List<WrongValueException> exs = new ArrayList<WrongValueException>();
	
		try { 
			username.getValue();
		}
		catch(WrongValueException ex) { 
			exs.add(ex);
		}
		
		try {
			 display.getValue();
		}
		catch(WrongValueException ex) {
			exs.add(ex);
		}
		
		try { 
			validatePasswordFields();
		}
		catch(WrongValuesException ex) {
			for( WrongValueException e : ex.getWrongValueExceptions() ) { 
				exs.add(e);
			}
		}
		
		if( !exs.isEmpty() ) throw new WrongValuesException(exs.toArray(new WrongValueException[]{}));
	}
	
	private void validatePasswordFields() throws WrongValuesException { 
		
		WebUtil.clearWrongValue(password);
		WebUtil.clearWrongValue(confirmPassword);
		
		String pass = password.getRawText();
		String confirm = confirmPassword.getRawText();
		
		List<WrongValueException> exs = new ArrayList<WrongValueException>();
		
		if( passFocused && (pass == null || pass.trim().equals("")) ) { 
			exs.add(new WrongValueException(password, ELFunctions.getMessage(I18NStrings.PASSWORD_CANNOT_BE_EMPTY)));
		}
		
		if( confirmFocused && (confirm == null || confirm.trim().equals("")) ) { 
			exs.add(new WrongValueException(confirmPassword, ELFunctions.getMessage(I18NStrings.PASSWORD_CANNOT_BE_EMPTY)));
		}
		
		if( passFocused && confirmFocused && exs.isEmpty() && !pass.trim().equals(confirm.trim()) ) { 
			exs.add(new WrongValueException(password, ELFunctions.getMessage(I18NStrings.PASSWORDS_DONT_MATCH)));
			exs.add(new WrongValueException(confirmPassword, ELFunctions.getMessage(I18NStrings.PASSWORDS_DONT_MATCH)));
		}

		if( !exs.isEmpty() )
			throw new WrongValuesException(exs.toArray(new WrongValueException[]{}));	
	}
	
	public void onClick$cancelButton(Event event) { 
		this.detach();
	}
}
