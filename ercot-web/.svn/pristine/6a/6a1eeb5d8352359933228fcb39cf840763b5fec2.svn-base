package com.softwarelikeyou.viewcontroller.user;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Window;

import com.softwarelikeyou.model.entity.User;

public class TypeChangeWindow extends Window implements AfterCompose {
	private static final long serialVersionUID = 1L;

	private User user;
	protected Radiogroup userType;
	
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
	
	protected void setUserType(String userTypeString) {
		
		if (userType != null) {
			
			for( int x=0; x < this.userType.getItemCount(); ++x ) { 
				
				Radio item = this.userType.getItemAtIndex(x);
				
				if( item.getValue().equals(userTypeString) ) {
					this.userType.setSelectedItem(item);
				}
			}
		}
	}
}
