package com.griddatabase.model.event;

import java.util.HashSet;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.log4j.Logger;

public class EventQueue {
	protected static final Logger logger = Logger.getLogger(EventQueue.class);
	protected Topic topic;
	protected Session session;
	protected MessageProducer producer;
	
	protected Set<MessageConsumer> consumers = new HashSet<MessageConsumer>();
	
	public EventQueue(Session session, Topic topic) {
		
		this.session = session;
		this.topic = topic;
		
		try {
			producer = session.createProducer(topic);
		} 
		catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void subscribe(MessageListener listener) {
		try { 
			MessageConsumer consumer = session.createConsumer(topic);
			consumer.setMessageListener(listener);
			consumers.add(consumer);
		}
		catch(JMSException e) { 	
			throw new RuntimeException(e);
		}
	}
	
	public void unsubscribe(MessageListener listener) {
		
		try { 			
			for( MessageConsumer consumer : consumers ) { 
				if( consumer.getMessageListener().equals(listener) ) {
					consumers.remove(consumer);
					consumer.close();
					break;
				}
			}
		}
		catch(JMSException e) { 
			throw new RuntimeException(e);
		}
	}	
	
	public void publish(Event event) { 
		
		try {
			logger.debug("EventQueue:publish: producer = " + producer.getDestination() + "  event = " + event.getName());
			producer.send(session.createObjectMessage(event));		
			logger.debug("EventQueue:publish: successful");
			
		} 
		catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}
