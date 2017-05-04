package com.softwarelikeyou.model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.HenryHubException;
import com.softwarelikeyou.model.dao.HibernateDAO;
import com.softwarelikeyou.model.entity.HenryHub;

public class HenryHubDAO extends HibernateDAO<HenryHub, Date> {	
	
	@SuppressWarnings("unchecked")
	public List<HenryHub> findAll() throws HenryHubException {
		Session session = null;
		List<HenryHub> entities = null;
		try {
			session = sessionFactory.openSession();
			entities = (List<HenryHub>) session.createQuery("from HenryHub").list();
		}
		catch (HibernateException e) {
			throw new HenryHubException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entities;		
	}
	
	public HenryHub findById(final Date intervalDate) throws HenryHubException {
		if (intervalDate == null)
			throw new HenryHubException("Interval date cannot be empty");
		Session session = null;
		HenryHub result = null;
		try {
			session = sessionFactory.openSession();
		    result = findById(session, intervalDate, false);
		}
		catch (HibernateException e) {
			throw new HenryHubException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<HenryHub> findByBetweenDates(final Date start, final Date end) throws HenryHubException {
		if (start == null || end == null)
			throw new HenryHubException("Date cannot be empty");
		Session session = null;
		List<HenryHub> list = null;
		try {
			session = sessionFactory.openSession();
			list = (List<HenryHub>) session.createCriteria(HenryHub.class)
					                       .add(Restrictions.between("intervalDate", start, end))
					                       .list();
		}
		catch (HibernateException e) {
			throw new HenryHubException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	public HenryHub createOrUpdate(HenryHub entity) throws HenryHubException {
		if (entity == null)
			throw new HenryHubException("HenryHub cannot be empty");
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (HenryHub) makePersistent(session, entity);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
			   transaction.rollback();
			throw new HenryHubException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entity;
	}
}
