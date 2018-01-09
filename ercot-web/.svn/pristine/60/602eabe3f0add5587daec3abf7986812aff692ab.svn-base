package com.softwarelikeyou.util;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.log4j.Logger;

public class ActiveMQUtil {
	private static Logger logger = Logger.getLogger(ActiveMQUtil.class);
	private static ActiveMQUtil instance = null;
	public static final String BROKER_URI  = "vm://localhost:61616?create=false&marshal=false";
	private static BrokerService broker;
	private static ActiveMQConnectionFactory connectionFactory;
    private static Connection connection;
	private static Session session;
	private static boolean start = false;
	
	private ActiveMQUtil() { }
	
	public static boolean isStarted() {
		return start;
	}
	
	public static ActiveMQUtil getInstance() {
	  	if (instance == null || !start) {
	  		instance = new ActiveMQUtil();
	  		start();
	  	}
	  	return instance;
	}
	
	public static Session getSession() {
		return session;
	}
	
	public static void start() {
	    try {
	  	    broker = new BrokerService();
	  	    broker.addConnector(BROKER_URI);
			broker.setPersistent(false);
		    broker.setUseJmx(false);
			broker.autoStart();
		    if (broker.isStarted())
		        start = true;
			connectionFactory = new ActiveMQConnectionFactory(BROKER_URI);
			connection = connectionFactory.createConnection();
			connection.start();	
		    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		}
	    catch (JMSException e) {
		    logger.error(e);
		} 
		catch (Exception e) {
		    logger.error(e.getCause().getLocalizedMessage(), e);
		    throw new RuntimeException(e);
		}
	}

	public static void stop() {
		try {
            session.close();
		    connection.close();
		    connection.stop();
	        broker.stop();
		    start = false;
		}
		catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}
}
