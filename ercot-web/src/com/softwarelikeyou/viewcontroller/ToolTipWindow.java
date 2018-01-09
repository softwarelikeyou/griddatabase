package com.softwarelikeyou.viewcontroller;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Window;

import com.softwarelikeyou.model.entity.UserData;

public class ToolTipWindow extends Window implements AfterCompose {

	private static final long serialVersionUID = 1L;

	protected Checkbox displayNextTimeCheckbox;
	protected String displayNextTimeDataKey;
	protected String followingEventName;

	public ToolTipWindow() {
	}

	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
	}

	public void setUserDataKey( String datakey ) {
		displayNextTimeDataKey = datakey;
	}

	public void setFollowingEventName( String eventName ) {
		followingEventName = eventName;
	}

	public void onClick$okButton( Event event ) {

		UserData userData = new UserData();
		userData.setUserId(SessionUtil.getCurrentUser().getId());
		userData.setProperty(displayNextTimeDataKey);
		userData.setValue(Boolean.toString(displayNextTimeCheckbox.isChecked()));
		SessionUtil.setUserData(userData);

		if( followingEventName != null )
			Events.postEvent(new Event(followingEventName, null, null));
		else
			detach();

	}

}
