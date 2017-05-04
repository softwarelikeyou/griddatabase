package com.softwarelikeyou.model.dao.ascpc;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.ASCPCException;
import com.softwarelikeyou.model.dao.HibernateDAO;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;

public class ASCPCDAO extends HibernateDAO<ASCPC, Date> {
	private static ASCPCDAO instance = new ASCPCDAO();
	
	private ASCPCDAO() { }
	
	public static ASCPCDAO getInstance() {
		return instance;
	}
	
	public ASCPC createOrUpdate(ASCPC entity) throws ASCPCException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (ASCPC) makePersistent(session, entity);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
			    transaction.rollback();
			throw new ASCPCException(e);
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
	public List<ASCPC> findBetweenDates(final Date start, final Date end) throws ASCPCException {
		if (start == null || end == null)
			throw new ASCPCException("Date cannot be empty");
		Session session = null;
		List<ASCPC> results = null;
		try {
			session = sessionFactory.openSession();
			results = (List<ASCPC>) session.createCriteria(ASCPC.class)
					                       .add(Restrictions.between("intervalDate", start, end))
					                       .addOrder(Order.asc("intervalDate"))
					                       .list();
		}
		catch (HibernateException e) {
			throw new ASCPCException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Date> findAllDistinctShortDate() throws ASCPCException {
		Session session = null;
		List<Date> list = null;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT DISTINCT CAST(intervalDate AS DATE) AS intervalDate " +
					     "FROM ascpc " +
			             "WHERE CAST(intervalDate AS TIME) != '00:00:00' " +
					     "GROUP BY intervalDate " +
			             "ORDER BY intervalDate ASC";
			list = (List<Date>) session.createSQLQuery(sql)
					                   .list();
		}
		catch (HibernateException e) {
			throw new ASCPCException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	public ASCPC findByIntervalDate(final Date date) throws ASCPCException {
		if (date == null)
			throw new ASCPCException("Date cannot be empty");
		Session session = null;
		ASCPC results = null;
		try {
			session = sessionFactory.openSession();
			results = (ASCPC) session.createCriteria(ASCPC.class)
                                     .add(Restrictions.eq("intervalDate", date))
                                     .uniqueResult();
		}
		catch (HibernateException e) {
			throw new ASCPCException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return results;
	}
	
	public Float findAverage(final Date start, final Date end, final String price) throws ASCPCException {
		if (start == null || end == null)
			throw new ASCPCException("Start date, end date, or name cannot be empty");
		Session session = null;
		Float result = 0f;
		try {
			session = sessionFactory.openSession();
			Double value = (Double) session.createCriteria(ASCPC.class)
							               .setProjection(Projections.avg(price))
					                       .add(Restrictions.between("intervalDate", start, end))
					                       .uniqueResult();
			if (value != null)
			    result = value.floatValue();
		}
		catch (HibernateException e) {
			throw new ASCPCException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	public Float findOffPeakAverage(final Date nightStart, final Date nightEnd, final Date morningStart, final Date morningEnd, final String price) throws ASCPCException {
		if (nightStart == null || nightEnd == null || morningStart == null || morningEnd == null || price == null)
			throw new ASCPCException("Dates, and/or price cannot be empty");
		Session session = null;
		Float result = 0f;
		try {
			session = sessionFactory.openSession();
			Double value = (Double) session.createCriteria(ASCPC.class)
							               .setProjection(Projections.avg(price))
							               .add(Restrictions.or(Restrictions.between("intervalDate", nightStart, nightEnd), 
							                        		    Restrictions.between("intervalDate", morningStart, morningEnd)))
					                       .uniqueResult();
			if (value != null)
			    result = value.floatValue();
		}
		catch (HibernateException e) {
			throw new ASCPCException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	public Float findMin(final Date start, final Date end, final String price) throws ASCPCException {
		if (start == null || end == null)
			throw new ASCPCException("Start date, end date, or name cannot be empty");
		Session session = null;
		Float result = 0f;
		try {
			session = sessionFactory.openSession();
			Float value = (Float) session.createCriteria(ASCPC.class)
							             .setProjection(Projections.min(price))
					                     .add(Restrictions.between("intervalDate", start, end))
					                     .uniqueResult();
			if (value != null)
			    result = value;
		}
		catch (HibernateException e) {
			throw new ASCPCException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	public Float findOffPeakMin(final Date nightStart, final Date nightEnd, final Date morningStart, final Date morningEnd, final String price) throws ASCPCException {
		if (nightStart == null || nightEnd == null || morningStart == null || morningEnd == null || price == null)
			throw new ASCPCException("Dates, and/or price cannot be empty");
		Session session = null;
		Float result = 0f;
		try {
			session = sessionFactory.openSession();
			Float value = (Float) session.createCriteria(ASCPC.class)
							             .setProjection(Projections.min(price))
							             .add(Restrictions.or(Restrictions.between("intervalDate", nightStart, nightEnd), 
							                        		  Restrictions.between("intervalDate", morningStart, morningEnd)))
					                     .uniqueResult();
			if (value != null)
			    result = value;
		}
		catch (HibernateException e) {
			throw new ASCPCException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	public Float findMax(final Date start, final Date end, final String price) throws ASCPCException {
		if (start == null || end == null)
			throw new ASCPCException("Start date, end date, or name cannot be empty");
		Session session = null;
		Float result = 0f;
		try {
			session = sessionFactory.openSession();
			Float value = (Float) session.createCriteria(ASCPC.class)
							             .setProjection(Projections.max(price))
					                     .add(Restrictions.between("intervalDate", start, end))
					                     .uniqueResult();
			if (value != null)
			    result = value;
		}
		catch (HibernateException e) {
			throw new ASCPCException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	public Float findOffPeakMax(final Date nightStart, final Date nightEnd, final Date morningStart, final Date morningEnd, final String price) throws ASCPCException {
		if (nightStart == null || nightEnd == null || morningStart == null || morningEnd == null || price == null)
			throw new ASCPCException("Dates, and/or price cannot be empty");
		Session session = null;
		Float result = 0f;
		try {
			session = sessionFactory.openSession();
			Float value = (Float) session.createCriteria(ASCPC.class)
							             .setProjection(Projections.max(price))
							             .add(Restrictions.or(Restrictions.between("intervalDate", nightStart, nightEnd), 
							                        		  Restrictions.between("intervalDate", morningStart, morningEnd)))
					                     .uniqueResult();
			if (value != null)
			    result = value;
		}
		catch (HibernateException e) {
			throw new ASCPCException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
}
