package com.softwarelikeyou.viewcontroller.signup;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;

import com.softwarelikeyou.facade.PasswordEncoder;
import com.softwarelikeyou.facade.UserFacade;
import com.softwarelikeyou.model.entity.User;
import com.softwarelikeyou.viewcontroller.BaseController;
import com.softwarelikeyou.viewcontroller.ELFunctions;
import com.softwarelikeyou.I18NStrings;

public class Controller extends BaseController {
	private static final long serialVersionUID = 1L;

	private SignupWindow signupWindow;
	
	public void onSignup(Event evt) {
		if( signupWindow == null ) throw new IllegalStateException("Signup window object is not available");
		signupWindow.validate();
		Clients.showBusy(ELFunctions.getMessageWithParams(I18NStrings.SIGNINGUP_USER, signupWindow.getUsername()));
		Events.echoEvent("onDoSignup", signupWindow, null);
	}
	
	public void onDoSignup(Event event) {

		if( signupWindow == null ) throw new IllegalStateException("Signup window object is not available");

		User user = new User();
		user.setUsername(signupWindow.getUsername());
		user.setDisplay(signupWindow.getDisplay());
		user.setPassword(new PasswordEncoder().encodePassword(signupWindow.getPassword(), null));
		user.setType(signupWindow.getUserType());

		try {
			user = UserFacade.createOrUpdate(user);
			try {
				Messagebox.show(ELFunctions.getMessageWithParams(I18NStrings.SUCCESSFULLY_CREATED_USER, user.getUsername()),
						        ELFunctions.getLabel(I18NStrings.SUCCESS),
						        Messagebox.OK,
						        Messagebox.NONE);
			}
			catch(InterruptedException e) {
				showErrorBox(e, I18NStrings.COULD_NOT_CREATE_USER, user.getUsername());
			}
		}
		catch (Exception e) {
			Clients.clearBusy();
			showErrorBox(e, I18NStrings.COULD_NOT_SIGNUP_USER, user.getUsername());
		}

		Clients.clearBusy();
		signupWindow.detach();
		signupWindow = null;
	}
}
