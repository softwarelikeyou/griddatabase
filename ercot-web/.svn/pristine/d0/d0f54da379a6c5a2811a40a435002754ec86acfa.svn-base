package com.softwarelikeyou.viewcontroller.user;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import com.softwarelikeyou.facade.UserFacade;
import com.softwarelikeyou.model.entity.User;
import com.softwarelikeyou.viewcontroller.BaseController;
import com.softwarelikeyou.viewcontroller.ELFunctions;
import com.softwarelikeyou.I18NStrings;

public class Controller extends BaseController {
	private static final long serialVersionUID = 1L;
	
	protected UserWindow disableWindow;
	private static final String DISABLE_ONE_URL = "/User/disable-one.zul";
	protected UserWindow enableWindow;
	private static final String ENABLE_ONE_URL = "/User/enable-one.zul";
	protected UserWindow resetWindow;
	private static final String RESET_ONE_URL = "/User/reset-one.zul";
	protected TypeChangeWindow typeChangeWindow;
	private static final String TYPE_CHANGE_URL = "/User/type-change.zul";
	
	public void onDisableSetup(Event event) {
		User user = (User) event.getData();
		disableWindow = (UserWindow) Executions.createComponents(DISABLE_ONE_URL, null, null);
		disableWindow.setUser(user);
		disableWindow.doHighlighted();
	}
	
	public void onDisable(Event event) {		
		User user = disableWindow.getUser();				
		Clients.showBusy(ELFunctions.getMessageWithParams(I18NStrings.DISABLING, user.getUsername()));			
		Events.echoEvent("onDoDisable", disableWindow, user);
	}
	
	public void onDoDisable(Event event) {
		try {
			Clients.clearBusy();
			User user = ((User)event.getData());
			user = UserFacade.disable(user);
		    if (!user.isEnabled()) {
			   logger.info(I18NStrings.SUCCESSFULLY_DISABLED_USER, user.getUsername());
		       Messagebox.show(ELFunctions.getMessageWithParams(I18NStrings.SUCCESSFULLY_DISABLED_USER, user.getUsername()),
					           ELFunctions.getLabel(I18NStrings.SUCCESS),
					           Messagebox.OK,
					           Messagebox.NONE);
		    }
		}
		catch (Exception e) {
			showErrorBox(e, I18NStrings.COULD_NOT_DISABLE_USER);
		}
		finally {
			disableWindow.detach();
			disableWindow = null;
			Clients.clearBusy();
		}
	}
	
	public void onEnableSetup(Event event) {
		User user = (User) event.getData();
		enableWindow = (UserWindow) Executions.createComponents(ENABLE_ONE_URL, null, null);
		enableWindow.setUser(user);
		enableWindow.doHighlighted();
	}
	
	public void onEnable(Event event) {		
		User user = enableWindow.getUser();				
		Clients.showBusy(ELFunctions.getMessageWithParams(I18NStrings.ENABLING, user.getUsername()));			
		Events.echoEvent("onDoEnable", enableWindow, user);
	}
	
	public void onDoEnable(Event event) {
		try {
			Clients.clearBusy();
			User user = ((User)event.getData());
			user = UserFacade.enable(user);
		    if (user.isEnabled()) {
			   logger.info(I18NStrings.SUCCESSFULLY_ENABLED_USER, user.getUsername());
		       Messagebox.show(ELFunctions.getMessageWithParams(I18NStrings.SUCCESSFULLY_ENABLED_USER, user.getUsername()),
					           ELFunctions.getLabel(I18NStrings.SUCCESS),
					           Messagebox.OK,
					           Messagebox.NONE);
		    }
		}
		catch (Exception e) {
			showErrorBox(e, I18NStrings.COULD_NOT_ENABLE_USER);
		}
		finally {
			enableWindow.detach();
			enableWindow = null;
			Clients.clearBusy();
		}
	}
	
	public void onResetSetup(Event event) {
		User user = (User) event.getData();
		resetWindow = (UserWindow) Executions.createComponents(RESET_ONE_URL, null, null);
		resetWindow.setUser(user);
		resetWindow.doHighlighted();
	}
	
	public void onReset(Event event) {		
		User user = resetWindow.getUser();				
		Clients.showBusy(ELFunctions.getMessageWithParams(I18NStrings.RESETTING_PASSWORD_USER, user.getUsername()));			
		Events.echoEvent("onDoReset", resetWindow, user);
	}
	
	public void onDoReset(Event event) {
		try {
			Clients.clearBusy();
			User user = ((User)event.getData());
			user = UserFacade.resetPassword(user);
		    if (user != null) {
			   logger.info(I18NStrings.SUCCESSFULLY_RESET_PASSWORD_FOR_USER, user.getUsername());
		       Messagebox.show(ELFunctions.getMessageWithParams(I18NStrings.SUCCESSFULLY_RESET_PASSWORD_FOR_USER, user.getUsername()),
					           ELFunctions.getLabel(I18NStrings.SUCCESS),
					           Messagebox.OK,
					           Messagebox.NONE);
		    }
		}
		catch (Exception e) {
			showErrorBox(e, I18NStrings.COULD_NOT_RESET_PASSWORD);
		}
		finally {
			resetWindow.detach();
			resetWindow = null;
			Clients.clearBusy();
		}
	}	
	
	public void onTypeChangeSetup(Event event) {
		User user = (User) event.getData();
		typeChangeWindow = (TypeChangeWindow) Executions.createComponents(TYPE_CHANGE_URL, null, null);
		typeChangeWindow.setUserType(user.getType().name());
		typeChangeWindow.setUser(user);
	    typeChangeWindow.doHighlighted();
	}
	
	public void onTypeChange(Event event) {		
		User user = typeChangeWindow.getUser();				
		Clients.showBusy(ELFunctions.getMessageWithParams(I18NStrings.CHANGING_USER_TYPE, user.getUsername()));			
		Events.echoEvent("onDoTypeChange", typeChangeWindow, user);
	}
	
	public void onDoTypeChange(Event event) {
		try {
			Clients.clearBusy();
			User user = ((User)event.getData());
			user.setType(User.UserType.get(typeChangeWindow.userType.getSelectedItem().getValue()));
			user = UserFacade.changeType(user);
			logger.info(I18NStrings.SUCCESSFULLY_CHANGED_USER_TYPE, user.getUsername());
		    Messagebox.show(ELFunctions.getMessageWithParams(I18NStrings.SUCCESSFULLY_CHANGED_USER_TYPE, user.getUsername()),
					        ELFunctions.getLabel(I18NStrings.SUCCESS),
					        Messagebox.OK,
					        Messagebox.NONE);
		}
		catch (Exception e) {
			showErrorBox(e, I18NStrings.COULD_NOT_CHANGE_USER_TYPE);
		}
		finally {
			typeChangeWindow.detach();
			typeChangeWindow = null;
			Clients.clearBusy();
		}
	}
}
