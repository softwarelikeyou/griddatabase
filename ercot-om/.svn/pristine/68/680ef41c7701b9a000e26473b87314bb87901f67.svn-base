package com.softwarelikeyou.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.H48DAMASException;
import com.softwarelikeyou.model.entity.H48DAMASCS;

public class H48DAMASCSDAO extends HibernateDAO<H48DAMASCS, Date> {
	public H48DAMASCS createOrUpdate(H48DAMASCS entity) throws H48DAMASException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (H48DAMASCS) makePersistent(session, entity);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
			    transaction.rollback();
			throw new H48DAMASException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entity;
	}
	
	public H48DAMASCS findById(final Date intervalDate) throws H48DAMASException {
		if (intervalDate == null)
			throw new H48DAMASException("Interval date cannot be empty");
		Session session = null;
		H48DAMASCS result = null;
		try {
			session = sessionFactory.openSession();
		    result = findById(session, intervalDate, false);
		}
		catch (HibernateException e) {
			throw new H48DAMASException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<H48DAMASCS> findAll() throws H48DAMASException {
		Session session = null;
		List<H48DAMASCS> entities = null;
		try {
			session = sessionFactory.openSession();
			entities = session.createQuery("from H48DAMASCS order by intervalDate desc").list();
		}
		catch (HibernateException e) {
			throw new H48DAMASException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entities;		
	}
	
	@SuppressWarnings("unchecked")
	public List<H48DAMASCS> findByBetweenDates(final Date start, final Date end) throws H48DAMASException {
		if (start == null || end == null)
			throw new H48DAMASException("Date cannot be empty");
		Session session = null;
		List<H48DAMASCS> list = null;
		try {
			session = sessionFactory.openSession();
			list = (List<H48DAMASCS>) session.createCriteria(H48DAMASCS.class)
					                         .add(Restrictions.between("intervalDate", start, end))
					                         .list();
		}
		catch (HibernateException e) {
			throw new H48DAMASException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<H48DAMASCS> findByGreaterThanDate(final Date date) throws H48DAMASException {
		if (date == null)
			throw new H48DAMASException("Date cannot be empty");
		Session session = null;
		List<H48DAMASCS> list;
		try {
			session = sessionFactory.openSession();
			list = (List<H48DAMASCS>) session.createCriteria(H48DAMASCS.class)
					                         .add(Restrictions.gt("intervalDate", date))
					                         .list();
		}
		catch (HibernateException e) {
			throw new H48DAMASException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<H48DAMASCS> findAllDistinctShortDate() throws H48DAMASException {
		Session session = null;
		List<H48DAMASCS> results = new ArrayList<H48DAMASCS>();
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT DISTINCT CAST(intervalDate AS DATE) AS intervalDate " +
					     "FROM H48DAMASCS " +
			             "WHERE CAST(intervalDate AS TIME) != '00:00:00' " +
					     "GROUP BY intervalDate " +
			             "ORDER BY intervalDate DESC";
			List<Date> list = (List<Date>) session.createSQLQuery(sql)
					                              .list();
			for (Date date : list) {
				H48DAMASCS ascpc = new H48DAMASCS();
				ascpc.setIntervalDate(date);
				results.add(ascpc);
			}
		}
		catch (HibernateException e) {
			throw new H48DAMASException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return results;
	}
}
