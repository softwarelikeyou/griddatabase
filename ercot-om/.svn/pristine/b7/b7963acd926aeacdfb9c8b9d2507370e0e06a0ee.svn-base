package com.softwarelikeyou.model.dao.ascpc;

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
import com.softwarelikeyou.model.entity.ascpc.ASCPCDaily;

public class ASCPCDailyDAO extends HibernateDAO<ASCPCDaily, Date> {
	
	private static ASCPCDailyDAO instance = new ASCPCDailyDAO();
	
	private ASCPCDailyDAO() { }
	
	public static ASCPCDailyDAO getInstance() {
		return instance;
	}
	
	public Daily createOrUpdate(ASCPCDaily entity) throws DailyException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (ASCPCDaily) makePersistent(session, entity);
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
			list = (List<Daily>) session.createCriteria(ASCPCDaily.class)
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
		if (start == null || end == null)
			throw new DailyException("Date cannot be empty");
		Session session = null;
		List<Daily> list = null;
		try {
			session = sessionFactory.openSession();
			list = (List<Daily>) session.createCriteria(ASCPCDaily.class)
                                        .add(Restrictions.eq("name", name))
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
}
