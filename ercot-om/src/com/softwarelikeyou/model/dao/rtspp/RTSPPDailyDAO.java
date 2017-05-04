package com.softwarelikeyou.model.dao.rtspp;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.DailyException;
import com.softwarelikeyou.model.dao.HibernateDAO;
import com.softwarelikeyou.model.entity.Daily;
import com.softwarelikeyou.model.entity.rtspp.RTSPPDaily;

public class RTSPPDailyDAO extends HibernateDAO<RTSPPDaily, Date> {

	private static RTSPPDailyDAO instance = new RTSPPDailyDAO();
	
	private RTSPPDailyDAO() { }
	
	public static RTSPPDailyDAO getInstance() {
		return instance;
	}
	
	public RTSPPDaily createOrUpdate(RTSPPDaily entity) throws DailyException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (RTSPPDaily) makePersistent(session, entity);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
			    transaction.rollback();
			throw new DailyException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			transaction = null;
			session = null;
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<Daily> findByBetweenDates(final Date start, final Date end) throws DailyException {
		if (start == null || end == null)
			throw new DailyException("Date cannot be empty");
		Session session = null;
		List<Daily> list = null;
		try {
			session = sessionFactory.openSession();
			list = (List<Daily>) session.createCriteria(RTSPPDaily.class)
					                    .add(Restrictions.between("intervalDate", start, end))
					                    .addOrder(Order.asc("intervalDate"))
					                    .addOrder(Order.asc("name"))
					                    .addOrder(Order.asc("type"))
					                    .list();
		}
		catch (HibernateException e) {
			throw new DailyException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Daily> findByBetweenDatesName(final Date start, final Date end, final String name) throws DailyException {
		if (start == null || end == null | name == null)
			throw new DailyException("Dates or name cannot be empty");
		Session session = null;
		List<Daily> list = null;
		try {
			session = sessionFactory.openSession();
			list = (List<Daily>) session.createCriteria(RTSPPDaily.class)
					                    .add(Restrictions.between("intervalDate", start, end))
					                    .add(Restrictions.eq("name", name))
					                    .addOrder(Order.asc("intervalDate"))
					                    .addOrder(Order.asc("name"))
					                    .addOrder(Order.asc("type"))
					                    .list();
		}
		catch (HibernateException e) {
			throw new DailyException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
}
