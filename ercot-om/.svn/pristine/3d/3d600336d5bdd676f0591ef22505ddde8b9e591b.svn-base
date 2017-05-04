package com.softwarelikeyou.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.H48DAMASException;
import com.softwarelikeyou.model.entity.H48DAMASAGRRSLD;

public class H48DAMASAGRRSLDDAO extends HibernateDAO<H48DAMASAGRRSLD, Date> {
	public H48DAMASAGRRSLD createOrUpdate(H48DAMASAGRRSLD entity) throws H48DAMASException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (H48DAMASAGRRSLD) makePersistent(session, entity);
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
	public List<H48DAMASAGRRSLD> findAll() throws H48DAMASException {
		Session session = null;
		List<H48DAMASAGRRSLD> entities = null;
		try {
			session = sessionFactory.openSession();
			entities = session.createQuery("from H48DAMASAGRRSLD order by intervalDate desc").list();
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
	public List<H48DAMASAGRRSLD> findByBetweenDates(final Date start, final Date end) throws H48DAMASException {
		if (start == null || end == null)
			throw new H48DAMASException("Date cannot be empty");
		Session session = null;
		List<H48DAMASAGRRSLD> list = null;
		try {
			session = sessionFactory.openSession();
			list = (List<H48DAMASAGRRSLD>) session.createCriteria(H48DAMASAGRRSLD.class)
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
	public List<H48DAMASAGRRSLD> findByGreaterThanDate(final Date date) throws H48DAMASException {
		if (date == null)
			throw new H48DAMASException("Date cannot be empty");
		Session session = null;
		List<H48DAMASAGRRSLD> list;
		try {
			session = sessionFactory.openSession();
			list = (List<H48DAMASAGRRSLD>) session.createCriteria(H48DAMASAGRRSLD.class)
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
	public List<H48DAMASAGRRSLD> findAllDistinctShortDate() throws H48DAMASException {
		Session session = null;
		List<H48DAMASAGRRSLD> results = new ArrayList<H48DAMASAGRRSLD>();
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT DISTINCT CAST(intervalDate AS DATE) AS intervalDate " +
					     "FROM H48DAMASAGRRSLD " +
			             "WHERE CAST(intervalDate AS TIME) != '00:00:00' " +
					     "GROUP BY intervalDate " +
			             "ORDER BY intervalDate DESC";
			List<Date> list = (List<Date>) session.createSQLQuery(sql)
					                              .list();
			for (Date date : list) {
				H48DAMASAGRRSLD ascpc = new H48DAMASAGRRSLD();
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
