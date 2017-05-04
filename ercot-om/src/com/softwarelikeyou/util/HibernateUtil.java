package com.softwarelikeyou.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.softwarelikeyou.model.dao.HibernateDAO;

public class HibernateUtil {
	private static HibernateUtil instance = null;
	private static SessionFactory sessionFactory;
	
	private HibernateUtil() { }
	
	public static HibernateUtil getInstance(final Configuration configuration) throws HibernateException {
		if (configuration == null)
			throw new HibernateException("Configuration is empty");
		if (instance == null) {
			try {
		        sessionFactory = configuration.buildSessionFactory();
		        if (sessionFactory == null)
		        	throw new HibernateException("Session Factory is empty");
		        HibernateDAO.setSessionFactory(sessionFactory);
			}
			catch (HibernateException e) {
				throw new HibernateException(e);
			}
		}
		return instance;
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
		
	public static void shutdown() throws HibernateException {
		if (HibernateUtil.getSessionFactory() != null)
		    getSessionFactory().close();
	}
	
	public static boolean test() throws HibernateException {
		boolean result = false;
		Session session = null;
		try {
		    session = sessionFactory.openSession();
		    if (session != null) {
		        if (session.isOpen())
		    	    result = true;
		    }
		}
		finally {
			if (session != null) {
			    if (session.isOpen())
				    session.close();
			}
		}
		return result;
	}
}
