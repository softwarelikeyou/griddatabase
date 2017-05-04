package com.softwarelikeyou.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.RTDAMException;
import com.softwarelikeyou.model.entity.RTDAM;

public class RTDAMDAO extends HibernateDAO<RTDAM, Date> {
	public RTDAM createOrUpdate(RTDAM entity) throws RTDAMException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (RTDAM) makePersistent(session, entity);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
			    transaction.rollback();
			throw new RTDAMException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entity;
	}
	
	public RTDAM findById(final Date intervalDate) throws RTDAMException {
		if (intervalDate == null)
			throw new RTDAMException("Interval date cannot be empty");
		Session session = null;
		RTDAM result = null;
		try {
			session = sessionFactory.openSession();
		    result = findById(session, intervalDate, false);
		}
		catch (HibernateException e) {
			throw new RTDAMException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<RTDAM> findAll() throws RTDAMException {
		Session session = null;
		List<RTDAM> entities = null;
		try {
			session = sessionFactory.openSession();
			entities = session.createQuery("from rtdam order by intervalDate desc").list();
		}
		catch (HibernateException e) {
			throw new RTDAMException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entities;		
	}
	
	@SuppressWarnings("unchecked")
	public List<RTDAM> findByBetweenDates(final Date start, final Date end) throws RTDAMException {
		if (start == null || end == null)
			throw new RTDAMException("Date cannot be empty");
		Session session = null;
		List<RTDAM> list = null;
		try {
			session = sessionFactory.openSession();
			list = (List<RTDAM>) session.createCriteria(RTDAM.class)
					                    .add(Restrictions.between("intervalDate", start, end))
					                    .list();
		}
		catch (HibernateException e) {
			throw new RTDAMException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<RTDAM> findByBetweenDatesSettlementPoint(final Date start, final Date end, final String settlementPoint) throws RTDAMException {
		if (start == null || end == null)
			throw new RTDAMException("Date cannot be empty");
		Session session = null;
		List<RTDAM> list = null;
		try {
			session = sessionFactory.openSession();
			list = (List<RTDAM>) session.createCriteria(RTDAM.class)
					                    .add(Restrictions.between("intervalDate", start, end))
					                    .add(Restrictions.eq("settlementPoint", settlementPoint))
					                    .list();
		}
		catch (HibernateException e) {
			throw new RTDAMException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<RTDAM> findByGreaterThanDate(final Date date) throws RTDAMException {
		if (date == null)
			throw new RTDAMException("Date cannot be empty");
		Session session = null;
		List<RTDAM> list;
		try {
			session = sessionFactory.openSession();
			list = (List<RTDAM>) session.createCriteria(RTDAM.class)
					                    .add(Restrictions.gt("intervalDate", date))
					                    .list();
		}
		catch (HibernateException e) {
			throw new RTDAMException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<RTDAM> findAllDistinctShortDate() throws RTDAMException {
		Session session = null;
		List<RTDAM> results = new ArrayList<RTDAM>();
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT DISTINCT CAST(intervalDate AS DATE) AS intervalDate " +
					     "FROM rtdam " +
			             "WHERE CAST(intervalDate AS TIME) != '00:00:00' " +
					     "GROUP BY intervalDate " +
			             "ORDER BY intervalDate DESC";
			
			List<Date> list = (List<Date>) session.createSQLQuery(sql)
					                              .list();
			for (Date date: list) {
		    	RTDAM result = new RTDAM();
		    	result.setIntervalDate(date);
				results.add(result);
			}
		}
		catch (HibernateException e) {
			throw new RTDAMException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return results;
	}
	
	public RTDAM findPreviousInterval(Date date, String settlementPoint) throws RTDAMException {
		if (date == null || settlementPoint == null)
			throw new RTDAMException("Date or settelmentPoint cannot be empty");
		Session session = null;
		RTDAM results = null;
		try {
			session = sessionFactory.openSession();
			results = (RTDAM) session.createCriteria(RTDAM.class)
                                     .add(Restrictions.eq("intervalDate", date))
                                     .add(Restrictions.eq("settlementPoint", settlementPoint))
                                     .uniqueResult();
		}
		catch (HibernateException e) {
			throw new RTDAMException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<RTDAM> findZeroVelocity(String settlementPoint) throws RTDAMException {
		Session session = null;
		List<RTDAM> results = null;
		try {
			session = sessionFactory.openSession();
			results = (List<RTDAM>) session.createCriteria(RTDAM.class)
                                           .add(Restrictions.and(Property.forName("settlementPoint").eq(settlementPoint), Property.forName("velocity").eq(0.0f)))
                                           .list();
		}
		catch (HibernateException e) {
			throw new RTDAMException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<RTDAM> findZeroAcceleration(String settlementPoint) throws RTDAMException {
		Session session = null;
		List<RTDAM> results = null;
		try {
			session = sessionFactory.openSession();
			results = (List<RTDAM>) session.createCriteria(RTDAM.class)
                                           .add(Restrictions.and(Property.forName("settlementPoint").eq(settlementPoint), Property.forName("acceleration").eq(0.0f)))
                                           .list();
		}
		catch (HibernateException e) {
			throw new RTDAMException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findSettlementPoints() throws RTDAMException {
		Session session = null;
		List<String> list = null;;
		try {
			session = sessionFactory.openSession();
			list = (List<String>) session.createCriteria(RTDAM.class)
					                     .setProjection(Projections.distinct(Projections.property("settlementPoint")))
					                     .list();
		}
		catch (HibernateException e) {
			throw new RTDAMException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
}
