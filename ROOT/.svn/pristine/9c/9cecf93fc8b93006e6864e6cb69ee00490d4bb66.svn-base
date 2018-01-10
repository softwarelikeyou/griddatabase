package com.griddatabase.model.facade;

import java.util.Date;

import com.griddatabase.model.event.EventConstants;
import com.griddatabase.model.event.EventQueues;
import com.griddatabase.model.event.LMPEvent;
import com.griddatabase.model.event.SPPEvent;
import com.griddatabase.model.event.UserDataEvent;
import com.griddatabase.model.event.UserEvent;
import com.griddatabase.model.entity.User;
import com.griddatabase.model.entity.UserData;

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
	
	public static void fireLMPUpdated(final Date date) {
        EventQueues.lookup(EventConstants.LMP_EVENT_QUEUE_NAME, true).publish(new LMPEvent(EventConstants.LMP_UPDATE_EVENT_NAME, date));
	}
	
	public static void fireSPPUpdated(final Date date) {
        EventQueues.lookup(EventConstants.SPP_EVENT_QUEUE_NAME, true).publish(new SPPEvent(EventConstants.SPP_UPDATE_EVENT_NAME, date));
	}
}
