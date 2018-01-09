package com.softwarelikeyou.viewcontroller.profile;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import org.zkoss.zul.Center;

import com.softwarelikeyou.facade.PasswordEncoder;
import com.softwarelikeyou.facade.UserFacade;
import com.softwarelikeyou.model.entity.User;
import com.softwarelikeyou.viewcontroller.BaseController;
import com.softwarelikeyou.viewcontroller.ELFunctions;
import com.softwarelikeyou.I18NStrings;
import com.softwarelikeyou.viewcontroller.SessionUtil;
import com.softwarelikeyou.viewcontroller.WebUtil;

public class Controller extends BaseController {
	private static final long serialVersionUID = 1L;

	private Center center;
	
	private EditWindow editWindow;
	private static final String EDIT_URL = "/Profile/edit.zul";
	
	public void onEditSetup(Event event) {
		center = (Center) WebUtil.getComponentById(desktop, "centerPanel");
		center.getChildren().clear();
		editWindow = (EditWindow) Executions.createComponents(EDIT_URL, center, null);
	}

	public void onEdit(Event event) {
		if (editWindow == null) 
			throw new IllegalStateException("EditWindow object is not available");
		editWindow.validate();
		Clients.showBusy(ELFunctions.getMessageWithParams(I18NStrings.EDITTING_USER, editWindow.getUsername()));
		Events.echoEvent("onDoEdit", editWindow, null);
	}

	public void onDoEdit(Event event) {
		Clients.clearBusy();
		if (editWindow == null ) 
			throw new IllegalStateException("EditWindow object is not available");
		User user = null;
		try {
			user = UserFacade.findByUsername(editWindow.getUsername());
			if (editWindow.getPassword().length() > 0)
			    user.setPassword(new PasswordEncoder().encodePassword(editWindow.getPassword(), null));
			user.setDisplay(editWindow.getDisplay());
			user = UserFacade.createOrUpdate(user);
			SessionUtil.setCurrentUser(user);
			logger.info(I18NStrings.SUCCESSFULLY_EDITTED_USER, user.getUsername());

			try {
				Messagebox.show(
						ELFunctions.getMessageWithParams(I18NStrings.SUCCESSFULLY_EDITTED_USER, user.getUsername()),
						ELFunctions.getLabel(I18NStrings.SUCCESS),
						Messagebox.OK,
						Messagebox.NONE
				); 
			}
			catch(InterruptedException ex) {
				// TODO do a better job on exceptions
				ex.printStackTrace();
			}

		}
		catch(Exception ex) {
			showErrorBox(ex, I18NStrings.COULD_NOT_EDIT_USER, user.getUsername());
		}
		center = (Center) WebUtil.getComponentById(desktop, "centerPanel");
		center.getChildren().clear();
		editWindow = (EditWindow) Executions.createComponents(EDIT_URL, center, null);
	}
}
