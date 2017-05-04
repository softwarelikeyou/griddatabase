package com.softwarelikeyou.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.H48DAMASException;
import com.softwarelikeyou.model.entity.H48DAMASAGRRSGN;

public class H48DAMASAGRRSGNDAO extends HibernateDAO<H48DAMASAGRRSGN, Date> {
	public H48DAMASAGRRSGN createOrUpdate(H48DAMASAGRRSGN entity) throws H48DAMASException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (H48DAMASAGRRSGN) makePersistent(session, entity);
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
	public List<H48DAMASAGRRSGN> findAll() throws H48DAMASException {
		Session session = null;
		List<H48DAMASAGRRSGN> entities = null;
		try {
			session = sessionFactory.openSession();
			entities = session.createQuery("from H48DAMASAGRRSGN order by intervalDate desc").list();
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
	public List<H48DAMASAGRRSGN> findByBetweenDates(final Date start, final Date end) throws H48DAMASException {
		if (start == null || end == null)
			throw new H48DAMASException("Date cannot be empty");
		Session session = null;
		List<H48DAMASAGRRSGN> list = null;
		try {
			session = sessionFactory.openSession();
			list = (List<H48DAMASAGRRSGN>) session.createCriteria(H48DAMASAGRRSGN.class)
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
	public List<H48DAMASAGRRSGN> findByGreaterThanDate(final Date date) throws H48DAMASException {
		if (date == null)
			throw new H48DAMASException("Date cannot be empty");
		Session session = null;
		List<H48DAMASAGRRSGN> list;
		try {
			session = sessionFactory.openSession();
			list = (List<H48DAMASAGRRSGN>) session.createCriteria(H48DAMASAGRRSGN.class)
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
	public List<H48DAMASAGRRSGN> findAllDistinctShortDate() throws H48DAMASException {
		Session session = null;
		List<H48DAMASAGRRSGN> results = new ArrayList<H48DAMASAGRRSGN>();
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT DISTINCT CAST(intervalDate AS DATE) AS intervalDate " +
					     "FROM H48DAMASAGRRSGN " +
			             "WHERE CAST(intervalDate AS TIME) != '00:00:00' " +
					     "GROUP BY intervalDate " +
			             "ORDER BY intervalDate DESC";
			List<Date> list = (List<Date>) session.createSQLQuery(sql)
					                              .list();
			for (Date date : list) {
				H48DAMASAGRRSGN ascpc = new H48DAMASAGRRSGN();
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
