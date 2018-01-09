package com.softwarelikeyou.event;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public abstract class EventListener implements MessageListener {
	protected static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EventListener.class);

	public abstract void onEvent(Event event);
	
	@Override
	public void onMessage(Message message) {
		if( !(message instanceof ObjectMessage) ) {
			throw new IllegalArgumentException("Got: " + (message != null ? message.getClass() : null) + ", expected: " + ObjectMessage.class);
		}
		logger.info("EventListener:onMessage: message = " + message.toString());
		ObjectMessage objMsg = (ObjectMessage) message;
		Event event = null;
		
		try {
			event = (Event) objMsg.getObject();
			logger.info("EventListener:onMessage: event = " + event.getName());
		} 
		catch (JMSException e) {
			throw new RuntimeException(e);
		}
		catch (ClassCastException e) { 
			throw new IllegalArgumentException("Data in event was: " + (event != null ? event.getClass() : null) + ", expected: " + Event.class);
		}
		onEvent(event);
	}
}
