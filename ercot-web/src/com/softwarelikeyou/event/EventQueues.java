package com.softwarelikeyou.event;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;

import com.softwarelikeyou.util.ActiveMQUtil;

public class EventQueues {
	protected static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EventQueues.class);
	private static final Map<String,EventQueue> QUEUES = new HashMap<String,EventQueue>();
	private static EventQueue aQueue =  null;
	public static EventQueue lookup(String queueName, boolean autocreate) { 
		if( !QUEUES.containsKey(queueName) && autocreate ) {
			try { 
				QUEUES.put(queueName, new EventQueue(ActiveMQUtil.getSession(), ActiveMQUtil.getSession().createTopic(queueName)));				
			}
			catch(JMSException e) { 		
				throw new RuntimeException(e);				
			}
		}
		aQueue =  QUEUES.get(queueName);
		try {
		   logger.debug("EventQueue:lookup: queue for queueName:" + queueName + "  returned:" + aQueue.topic.getTopicName());
		}
		catch (JMSException e) {
			throw new RuntimeException(e);
		}
	    return aQueue; 
	}
}
