package com.softwarelikeyou.model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.RTLMPException;
import com.softwarelikeyou.model.entity.rtlmp.RTLMP;
import com.softwarelikeyou.util.HibernateUtil;

public class RTLMPDAO {
	
	private Class<? extends RTLMP> persistentClass;
	
	private SessionFactory sessionFactory;
	
	public RTLMPDAO(Class<? extends RTLMP> value) { 
		persistentClass = value;
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	private Class<? extends RTLMP> getPersistentClass() {
		return persistentClass;
	}
		
	public RTLMP findById(Date timestamp, Date intervalEnding, String settlementPoint) throws RTLMPException {
		if (timestamp == null || intervalEnding == null || settlementPoint == null)
			throw new RTLMPException("Timestamp, intervalEnding, or settlementPoint cannot be empty");
		Session session = null;
		RTLMP results = null;
		try {
			session = sessionFactory.openSession();
			results = (RTLMP) session.createCriteria(getPersistentClass())
					                 .add(Restrictions.eq("RTDTimestamp", timestamp))
					                 .add(Restrictions.eq("intervalEnding", intervalEnding))
					                 .add(Restrictions.eq("settlementPoint", settlementPoint))
					                 .uniqueResult();
		}
		catch (HibernateException e) {
			throw new RTLMPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<RTLMP> findByRTDTimestamp(Date timestamp) throws RTLMPException {
		if (timestamp == null)
			throw new RTLMPException("Timestamp cannot be empty");
		Session session = null;
		List<RTLMP> list;
		try {
			session = sessionFactory.openSession();
			list = (List<RTLMP>) session.createCriteria(getPersistentClass())
					                     .add(Restrictions.eq("RTDTimestamp", timestamp))
					                     .addOrder(Order.asc("settlementPoint"))
					                     .addOrder(Order.asc("intervalId"))
					                     .list();
		}
		catch (HibernateException e) {
			throw new RTLMPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	public void createOrUpdate(RTLMP entity) throws RTLMPException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(entity);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
			    transaction.rollback();
			throw new RTLMPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			transaction = null;
			session = null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RTLMP> findByBetweenIntervalEndingDatesSettlementPoint(final Date start, final Date end, final String settlementPoint) throws RTLMPException {
		if (start == null || end == null || settlementPoint == null)
			throw new RTLMPException("Date(s) or settlement point cannot be empty");
		Session session = null;
		List<RTLMP> list;
		try {
			session = sessionFactory.openSession();
			list = (List<RTLMP>) session.createCriteria(getPersistentClass())
					                    .add(Restrictions.between("intervalEnding", start, end))
					                    .add(Restrictions.eq("settlementPoint", settlementPoint))
					                    .addOrder( Property.forName("intervalEnding").asc())
					                    .addOrder(Property.forName("intervalId").asc())
					                    .list();
		}
		catch (HibernateException e) {
			throw new RTLMPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<RTLMP> findByBetweenRTDTimestampDatesSettlementPoint(final Date start, final Date end, final String settlementPoint) throws RTLMPException {
		if (start == null || end == null || settlementPoint == null)
			throw new RTLMPException("Date(s) or settlement point cannot be empty");
		Session session = null;
		List<RTLMP> list;
		try {
			session = sessionFactory.openSession();
			list = (List<RTLMP>) session.createCriteria(getPersistentClass())
					                    .add(Restrictions.between("RTDTimestamp", start, end))
					                    .add(Restrictions.eq("settlementPoint", settlementPoint))
					                    .addOrder(Property.forName("RTDTimestamp").asc())
					                    .addOrder(Property.forName("intervalId").asc())
					                    .list();
		}
		catch (HibernateException e) {
			throw new RTLMPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	public RTLMP findPreviousInterval(Date date, Integer intervalId, String settlementPoint) throws RTLMPException {
		if (date == null || settlementPoint == null)
			throw new RTLMPException("Date or settelmentPoint cannot be empty");
		Session session = null;
		RTLMP results = null;
		try {
			session = sessionFactory.openSession();
			results = (RTLMP) session.createCriteria(getPersistentClass())
                                     .add(Restrictions.eq("intervalEnding", date))
                                     .add(Restrictions.eq("intervalId", intervalId))
                                     .add(Restrictions.eq("settlementPoint", settlementPoint))
                                     .uniqueResult();
		}
		catch (HibernateException e) {
			throw new RTLMPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findUniqueSettlementPoints() throws RTLMPException {
		Session session = null;
		List<String> list = null;;
		try {
			session = sessionFactory.openSession();
			list = (List<String>) session.createCriteria(getPersistentClass())
					                     .setProjection(Projections.distinct(Projections.property("settlementPoint")))
					                     .list();
		}
		catch (HibernateException e) {
			throw new RTLMPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
}
