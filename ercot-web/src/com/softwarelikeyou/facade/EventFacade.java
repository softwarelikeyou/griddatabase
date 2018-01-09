package com.softwarelikeyou.facade;

import com.softwarelikeyou.event.EventConstants;
import com.softwarelikeyou.event.EventQueues;
import com.softwarelikeyou.event.UserDataEvent;
import com.softwarelikeyou.event.UserEvent;
import com.softwarelikeyou.model.entity.User;
import com.softwarelikeyou.model.entity.UserData;

public class EventFacade {
	public static void fireUserCreated(final User user) {
		EventQueues.lookup(EventConstants.USER_EVENT_QUEUE_NAME, true).publish(new UserEvent(EventConstants.USER_CREATE_EVENT_NAME, user));	
	}
	
	public static void fireUserUpdated(final User user) {
        EventQueues.lookup(EventConstants.USER_EVENT_QUEUE_NAME, true).publish(new UserEvent(EventConstants.USER_UPDATE_EVENT_NAME, user));
	}
	
	public static void fireUserDeleted(final User user) {
        EventQueues.lookup(EventConstants.USER_EVENT_QUEUE_NAME, true).publish(new UserEvent(EventConstants.USER_DELETE_EVENT_NAME, user));
	}
	
	public static void fireUserDataUpdated(final UserData userData) {
        EventQueues.lookup(EventConstants.USER_EVENT_QUEUE_NAME, true).publish(new UserDataEvent(EventConstants.USERDATA_UPDATE_EVENT_NAME, userData));
	}
}
