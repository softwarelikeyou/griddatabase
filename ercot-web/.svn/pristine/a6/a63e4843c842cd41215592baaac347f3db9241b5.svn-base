package com.softwarelikeyou.viewcontroller.user;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Window;

import com.softwarelikeyou.model.entity.User;

public class UserWindow extends Window implements AfterCompose {
	private static final long serialVersionUID = 1L;

	private User user;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
	}		
	
	public void onClick$cancelButton(Event event) { 
		this.detach();
	}

}
