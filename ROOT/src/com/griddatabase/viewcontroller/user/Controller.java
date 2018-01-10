package com.griddatabase.viewcontroller.user;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericAutowireComposer;
import org.zkoss.zul.Messagebox;

import com.griddatabase.model.entity.User;
import com.griddatabase.model.facade.PasswordEncoder;
import com.griddatabase.model.facade.UserFacade;
import com.griddatabase.util.SessionUtil;
import com.griddatabase.viewcontroller.user.SignupWindow;

@SuppressWarnings("rawtypes")
public class Controller extends GenericAutowireComposer {
	private static final long serialVersionUID = 1L;
	
	protected UserWindow disableWindow;
	private static final String DISABLE_ONE_URL = "/Portal/User/disable-one.zul";
	protected UserWindow enableWindow;
	private static final String ENABLE_ONE_URL = "/Portal/User/enable-one.zul";
	protected UserWindow resetWindow;
	private static final String RESET_ONE_URL = "/Portal/User/reset-one.zul";
	protected TypeChangeWindow typeChangeWindow;
	private static final String TYPE_CHANGE_URL = "/Portal/User/type-change.zul";
	private EditWindow editWindow;
	private static final String EDIT_URL = "/Portal/User/edit.zul";
	private SignupWindow signupWindow;

	public void onDisableSetup(Event event) {
		User user = (User) event.getData();
		disableWindow = (UserWindow) Executions.createComponents(DISABLE_ONE_URL, null, null);
		disableWindow.setUser(user);
		disableWindow.doHighlighted();
	}
	
	public void onDisable(Event event) {		
		User user = disableWindow.getUser();				
		Clients.showBusy("Disabling " + user.getUsername());			
		Events.echoEvent("onDoDisable", disableWindow, user);
	}
	
	public void onDoDisable(Event event) {
		try {
			Clients.clearBusy();
			User user = ((User)event.getData());
			user = UserFacade.disable(user);
		    if (!user.isEnabled())
		       Messagebox.show("Successfully disabled user " + user.getUsername(), "Success", Messagebox.OK, Messagebox.NONE);
		}
		catch (Exception e) {
		    Messagebox.show("Could not disable user ", "Error", Messagebox.OK, Messagebox.NONE);
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
		Clients.showBusy("Enabling " + user.getUsername());			
		Events.echoEvent("onDoEnable", enableWindow, user);
	}
	
	public void onDoEnable(Event event) {
		try {
			Clients.clearBusy();
			User user = ((User)event.getData());
			user = UserFacade.enable(user);
		    if (user.isEnabled())
		       Messagebox.show("Successfully enabled user " + user.getUsername(), "Success", Messagebox.OK, Messagebox.NONE);
		}
		catch (Exception e) {
		    Messagebox.show("Could not enable user", "Error", Messagebox.OK, Messagebox.NONE);
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
		Clients.showBusy("Resetting password for user " + user.getUsername());			
		Events.echoEvent("onDoReset", resetWindow, user);
	}
	
	public void onDoReset(Event event) {
		try {
			Clients.clearBusy();
			User user = ((User)event.getData());
			user = UserFacade.resetPassword(user);
		    if (user != null) {
		       Messagebox.show("Seccussfully reset password for user " + user.getUsername(), "Success", Messagebox.OK, Messagebox.NONE);
		    }
		}
		catch (Exception e) {
		    Messagebox.show("Could not reset password", "Success", Messagebox.OK, Messagebox.NONE);
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
		Clients.showBusy("Changing user type for user " + user.getUsername());			
		Events.echoEvent("onDoTypeChange", typeChangeWindow, user);
	}
	
	public void onDoTypeChange(Event event) {
		try {
			Clients.clearBusy();
			User user = ((User)event.getData());
			user.setType(User.UserType.get(typeChangeWindow.userType.getSelectedItem().getValue()));
			user = UserFacade.changeType(user);
			SessionUtil.setCurrentUser(user);
		    Messagebox.show("Seccussfully changed user type for user " + user.getUsername(), "Success", Messagebox.OK, Messagebox.NONE);
		}
		catch (Exception e) {
		    Messagebox.show("Could not change user type for user ", "Success", Messagebox.OK, Messagebox.NONE);
		}
		finally {
			typeChangeWindow.detach();
			typeChangeWindow = null;
			Clients.clearBusy();
		}
	}
	
	public void onEditSetup(Event event) {
		editWindow = (EditWindow) Executions.createComponents(EDIT_URL, null, null);
		editWindow.doHighlighted();
	}

	public void onEdit(Event event) {
		if (editWindow == null) 
			throw new IllegalStateException("EditWindow object is not available");
		editWindow.validate();
		Clients.showBusy("Saving user " + editWindow.getUsername());
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
			Messagebox.show("Successfully saved user " + user.getUsername(), "Success", Messagebox.OK, Messagebox.NONE); 
		}
		catch(Exception ex) {
			Messagebox.show("Could not save user " + user.getUsername(), "Error", Messagebox.OK, Messagebox.NONE); 
		}
	}
	
	public void onSignup(Event evt) {
		if( signupWindow == null ) throw new IllegalStateException("Signup window object is not available");
		signupWindow.validate();
		Clients.showBusy("Creating user name " + signupWindow.getUsername());
		Events.echoEvent("onDoSignup", signupWindow, null);
	}
	
	@SuppressWarnings("unchecked")
	public void onDoSignup(Event event) {

		if( signupWindow == null ) throw new IllegalStateException("Signup window object is not available");

		User user = new User();
		user.setUsername(signupWindow.getUsername());
		user.setDisplay(signupWindow.getDisplay());
		user.setPassword(new PasswordEncoder().encodePassword(signupWindow.getPassword(), null));
		user.setType(signupWindow.getUserType());

		try {
			user = UserFacade.createOrUpdate(user);
			SessionUtil.setCurrentUser(user);
			Messagebox.show(user.getDisplay() + ", Thank you for signing up, if you have any problems please email Steve.Thomas@griddataabase.com ", 
					        "Success", 
					        Messagebox.OK, 
					        Messagebox.NONE,
					        new org.zkoss.zk.ui.event.EventListener() {
	                            public void onEvent(Event e) {
	                                if(Messagebox.ON_OK.equals(e.getName())) {
	                        			Executions.getCurrent().sendRedirect("/downloads.zul");
	                                }
	                            } 
	                        }
	        );
		}
		catch (Exception e) {
			Clients.clearBusy();
			Messagebox.show("Could not create user " + user.getUsername(), "Error", Messagebox.OK, Messagebox.NONE);
		}

		Clients.clearBusy();
		signupWindow.detach();
		signupWindow = null;
	}
}
