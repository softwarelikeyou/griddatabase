package com.softwarelikeyou.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.H48DAMHPException;
import com.softwarelikeyou.model.entity.H48DAMHP;

public class H48DAMHPDAO extends HibernateDAO<H48DAMHP, Date> {
	public H48DAMHP createOrUpdate(H48DAMHP entity) throws H48DAMHPException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (H48DAMHP) makePersistent(session, entity);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
			    transaction.rollback();
			throw new H48DAMHPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entity;
	}
	
	public H48DAMHP findById(final Date intervalDate) throws H48DAMHPException {
		if (intervalDate == null)
			throw new H48DAMHPException("Interval date cannot be empty");
		Session session = null;
		H48DAMHP result = null;
		try {
			session = sessionFactory.openSession();
		    result = findById(session, intervalDate, false);
		}
		catch (HibernateException e) {
			throw new H48DAMHPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<H48DAMHP> findAll() throws H48DAMHPException {
		Session session = null;
		List<H48DAMHP> entities = null;
		try {
			session = sessionFactory.openSession();
			entities = session.createQuery("from h48damhp order by intervalDate desc").list();
		}
		catch (HibernateException e) {
			throw new H48DAMHPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entities;		
	}
	
	@SuppressWarnings("unchecked")
	public List<H48DAMHP> findByBetweenDates(final Date start, final Date end) throws H48DAMHPException {
		if (start == null || end == null)
			throw new H48DAMHPException("Date cannot be empty");
		Session session = null;
		List<H48DAMHP> list = null;
		try {
			session = sessionFactory.openSession();
			list = (List<H48DAMHP>) session.createCriteria(H48DAMHP.class)
					                       .add(Restrictions.between("intervalDate", start, end))
					                       .list();
		}
		catch (HibernateException e) {
			throw new H48DAMHPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<H48DAMHP> findByBetweenDatesResourceName(final Date start, final Date end, final String resourceName) throws H48DAMHPException {
		if (start == null || end == null)
			throw new H48DAMHPException("Date cannot be empty");
		Session session = null;
		List<H48DAMHP> list = null;
		try {
			session = sessionFactory.openSession();
			list = (List<H48DAMHP>) session.createCriteria(H48DAMHP.class)
					                     .add(Restrictions.between("intervalDate", start, end))
					                     .add(Restrictions.eq("resourceName", resourceName))
					                     .list();
		}
		catch (HibernateException e) {
			throw new H48DAMHPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<H48DAMHP> findByGreaterThanDate(final Date date) throws H48DAMHPException {
		if (date == null)
			throw new H48DAMHPException("Date cannot be empty");
		Session session = null;
		List<H48DAMHP> list;
		try {
			session = sessionFactory.openSession();
			list = (List<H48DAMHP>) session.createCriteria(H48DAMHP.class)
					                    .add(Restrictions.gt("intervalDate", date))
					                    .list();
		}
		catch (HibernateException e) {
			throw new H48DAMHPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<H48DAMHP> findAllDistinctShortDate() throws H48DAMHPException {
		Session session = null;
		List<H48DAMHP> results = new ArrayList<H48DAMHP>();
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT DISTINCT CAST(intervalDate AS DATE) AS intervalDate " +
					     "FROM h48damhp " +
			             "WHERE CAST(intervalDate AS TIME) != '00:00:00' " +
					     "GROUP BY intervalDate " +
			             "ORDER BY intervalDate DESC";
			List<Date> list = (List<Date>) session.createSQLQuery(sql)
					                              .list();
			for (Date date : list) {
				H48DAMHP ascpc = new H48DAMHP();
				ascpc.setIntervalDate(date);
				results.add(ascpc);
			}
		}
		catch (HibernateException e) {
			throw new H48DAMHPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findResourceNames() throws H48DAMHPException {
		Session session = null;
		List<String> list = null;;
		try {
			session = sessionFactory.openSession();
			list = (List<String>) session.createCriteria(H48DAMHP.class)
					                     .setProjection(Projections.distinct(Projections.property("resourceName")))
					                     .list();
		}
		catch (HibernateException e) {
			throw new H48DAMHPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
}
