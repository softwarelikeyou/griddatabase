package com.softwarelikeyou.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.H48DAMASException;
import com.softwarelikeyou.model.entity.H48DAMASAGREGDN;

public class H48DAMASAGREGDNDAO extends HibernateDAO<H48DAMASAGREGDN, Date> {
	public H48DAMASAGREGDN createOrUpdate(H48DAMASAGREGDN entity) throws H48DAMASException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (H48DAMASAGREGDN) makePersistent(session, entity);
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
	
	@SuppressWarnings("unchecked")
	public List<H48DAMASAGREGDN> findAll() throws H48DAMASException {
		Session session = null;
		List<H48DAMASAGREGDN> entities = null;
		try {
			session = sessionFactory.openSession();
			entities = session.createQuery("from H48DAMASAGREGDN order by intervalDate desc").list();
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
	public List<H48DAMASAGREGDN> findByBetweenDates(final Date start, final Date end) throws H48DAMASException {
		if (start == null || end == null)
			throw new H48DAMASException("Date cannot be empty");
		Session session = null;
		List<H48DAMASAGREGDN> list = null;
		try {
			session = sessionFactory.openSession();
			list = (List<H48DAMASAGREGDN>) session.createCriteria(H48DAMASAGREGDN.class)
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
	public List<H48DAMASAGREGDN> findByGreaterThanDate(final Date date) throws H48DAMASException {
		if (date == null)
			throw new H48DAMASException("Date cannot be empty");
		Session session = null;
		List<H48DAMASAGREGDN> list;
		try {
			session = sessionFactory.openSession();
			list = (List<H48DAMASAGREGDN>) session.createCriteria(H48DAMASAGREGDN.class)
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
	public List<H48DAMASAGREGDN> findAllDistinctShortDate() throws H48DAMASException {
		Session session = null;
		List<H48DAMASAGREGDN> results = new ArrayList<H48DAMASAGREGDN>();
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT DISTINCT CAST(intervalDate AS DATE) AS intervalDate " +
					     "FROM H48DAMASAGREGDN " +
			             "WHERE CAST(intervalDate AS TIME) != '00:00:00' " +
					     "GROUP BY intervalDate " +
			             "ORDER BY intervalDate DESC";
			List<Date> list = (List<Date>) session.createSQLQuery(sql)
					                              .list();
			for (Date date : list) {
				H48DAMASAGREGDN ascpc = new H48DAMASAGREGDN();
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
