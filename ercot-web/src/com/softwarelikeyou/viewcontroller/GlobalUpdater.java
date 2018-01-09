package com.softwarelikeyou.viewcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.util.DesktopInit;
import org.zkoss.zk.ui.util.WebAppCleanup;

import com.softwarelikeyou.event.EventConstants;
import com.softwarelikeyou.event.UserEvent;
import com.softwarelikeyou.model.entity.User;
import com.softwarelikeyou.model.entity.UserData;

public class GlobalUpdater implements DesktopInit, WebAppCleanup {
	private static Map<String, EventQueue> applicationEventQueues = null;
	private static boolean shutdown = false;

	private static List<Desktop> activeDesktops = new ArrayList<Desktop>();
	
	protected static final List<String> excludePaths = new ArrayList<String>();
	static {
		excludePaths.add("login.zul");
	}
	
	protected static boolean isExcluded(Desktop desktop) {
		for( String exclude : excludePaths ) {
			if( desktop.getRequestPath().contains(exclude) ) return true;
		}
		return false;
	}
	
	private static synchronized void initEventQueues() {
		if( applicationEventQueues == null ) {

			applicationEventQueues = new HashMap<String, EventQueue>();
			applicationEventQueues.put(EventConstants.USER_EVENT_QUEUE_NAME, EventQueues.lookup(EventConstants.USER_EVENT_QUEUE_NAME, EventQueues.APPLICATION, true));
	
			com.softwarelikeyou.event.EventQueues.lookup(EventConstants.USER_EVENT_QUEUE_NAME,true).subscribe(new com.softwarelikeyou.event.EventListener() {
				@Override
				public void onEvent(com.softwarelikeyou.event.Event event) {
					if (isShutdown()) return;
					if ( !(event instanceof UserEvent) ) return;
					if ( event.getName().equals(EventConstants.USER_CREATE_EVENT_NAME))
						applicationEventQueues.get(EventConstants.USER_EVENT_QUEUE_NAME).publish(new Event(EventConstants.USER_CREATE_EVENT_NAME, null, (User)event.getData()));
					else if (event.getName().equals(EventConstants.USER_UPDATE_EVENT_NAME)) 
						applicationEventQueues.get(EventConstants.USER_EVENT_QUEUE_NAME).publish(new Event(EventConstants.USER_UPDATE_EVENT_NAME, null, (User)event.getData()));
					else if (event.getName().equals(EventConstants.USER_DELETE_EVENT_NAME)) 
						applicationEventQueues.get(EventConstants.USER_EVENT_QUEUE_NAME).publish(new Event(EventConstants.USER_DELETE_EVENT_NAME, null, (User)event.getData()));
					else if (event.getName().equals(EventConstants.USERDATA_UPDATE_EVENT_NAME)) 
						applicationEventQueues.get(EventConstants.USER_EVENT_QUEUE_NAME).publish(new Event(EventConstants.USERDATA_UPDATE_EVENT_NAME, null, (UserData)event.getData()));
				} 
			});
		}
	}
	
	public static boolean isShutdown() {
		return shutdown;
	}
	
	protected static void start() {
		shutdown = false;
		initEventQueues();
	}

	protected static void stop() {
		shutdown = true;
		applicationEventQueues = null;
	}

	public void init(Desktop desktop, Object req) throws Exception {
		GlobalUpdater.start();
		
		synchronized( activeDesktops ) {
			if( !isExcluded(desktop) ) {
				activeDesktops.add(desktop);
			}
		}
	}

	public void cleanup(Desktop desktop) throws Exception {
		synchronized(activeDesktops) {
			activeDesktops.remove(desktop);
	    }
	}
	
	public void cleanup(WebApp webapp) throws Exception {
		GlobalUpdater.stop();
	}
}
