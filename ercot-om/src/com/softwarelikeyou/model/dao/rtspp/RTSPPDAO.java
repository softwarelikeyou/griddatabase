package com.softwarelikeyou.model.dao.rtspp;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.RTSPPException;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;
import com.softwarelikeyou.util.HibernateUtil;

public class RTSPPDAO {
	
	private Class<? extends RTSPP> persistentClass;
	
	private SessionFactory sessionFactory;
	
	public RTSPPDAO(Class<? extends RTSPP> value) { 
		persistentClass = value;
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	private Class<? extends RTSPP> getPersistentClass() {
		return persistentClass;
	}
	
	public RTSPP findById(final Date intervalDate, final String settlementPointName) throws RTSPPException {
		if (intervalDate == null || settlementPointName == null)
			throw new RTSPPException("IntervalDate or settlementPointName cannot be empty");
		Session session = null;
		RTSPP results = null;
		try {
			session = sessionFactory.openSession();
			results = (RTSPP) session.createCriteria(getPersistentClass())
					                 .add(Restrictions.eq("intervalDate", intervalDate))
					                 .add(Restrictions.eq("settlementPointName", settlementPointName))
					                 .uniqueResult();
		}
		catch (HibernateException e) {
			throw new RTSPPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<RTSPP> findByIntervalDate(final Date intervalDate) throws RTSPPException {
		if (intervalDate == null)
			throw new RTSPPException("IntervalDate cannot be empty");
		Session session = null;
		List<RTSPP> list;
		try {
			session = sessionFactory.openSession();
			list = (List<RTSPP>) session.createCriteria(getPersistentClass())
					                    .add(Restrictions.eq("intervalDate", intervalDate))
					                    .addOrder(Order.asc("settlementPointName"))
					                    .list();
		}
		catch (HibernateException e) {
			throw new RTSPPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<RTSPP> findByIntervalDates(final Date start, final Date end) throws RTSPPException {
		if (start == null || end == null)
			throw new RTSPPException("Date(s) cannot be empty");
		Session session = null;
		List<RTSPP> list;
		try {
			session = sessionFactory.openSession();
			list = (List<RTSPP>) session.createCriteria(getPersistentClass())
					                    .add(Restrictions.between("intervalDate", start, end))
					                    .addOrder( Property.forName("intervalDate").asc())
					                    .setMaxResults(10000)
					                    .list();
		}
		catch (HibernateException e) {
			throw new RTSPPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<RTSPP> findByIntervalDatesSettlementPoint(final Date start, final Date end, final String settlementPointName) throws RTSPPException {
		if (start == null || end == null)
			throw new RTSPPException("Date(s) cannot be empty");
		Session session = null;
		List<RTSPP> list;
		try {
			session = sessionFactory.openSession();
			list = (List<RTSPP>) session.createCriteria(getPersistentClass())
					                    .add(Restrictions.between("intervalDate", start, end))
					                    .add(Restrictions.eq("settlementPointName", settlementPointName))
					                    .addOrder( Property.forName("intervalDate").asc())
					                    .setMaxResults(10000)
					                    .list();
		}
		catch (HibernateException e) {
			throw new RTSPPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findUniqueSettlementPoints() throws RTSPPException {
		Session session = null;
		List<String> list = null;;
		try {
			session = sessionFactory.openSession();
			list = (List<String>) session.createCriteria(getPersistentClass())
					                     .setProjection(Projections.distinct(Projections.property("settlementPointName")))
					                     .list();
		}
		catch (HibernateException e) {
			throw new RTSPPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	public void createOrUpdate(final RTSPP entity) throws RTSPPException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(entity);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
			    transaction.rollback();
			throw new RTSPPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			transaction = null;
			session = null;
		}
	}
	
	public Float findAverage(final Date start, final Date end, final String settlementPointName) throws RTSPPException {
		if (start == null || end == null || settlementPointName == null)
			throw new RTSPPException("Start date, end date, or name cannot be empty");
		Session session = null;
		Float result = 0f;
		try {
			session = sessionFactory.openSession();
			Double value = (Double) session.createCriteria(getPersistentClass())
							               .setProjection(Projections.avg("settlementPointPrice"))
					                       .add(Restrictions.between("intervalDate", start, end))
					                       .add(Restrictions.eq("settlementPointName", settlementPointName))
					                       .uniqueResult();
			if (value != null)
			    result = value.floatValue();
		}
		catch (HibernateException e) {
			throw new RTSPPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<RTSPP> findList(final Date start, final Date end, final String settlementPointName) throws RTSPPException {
		if (start == null || end == null || settlementPointName == null)
			throw new RTSPPException("Start date, end date, or name cannot be empty");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return (List<RTSPP>) session.createCriteria(getPersistentClass())
					                       .add(Restrictions.between("intervalDate", start, end))
					                       .add(Restrictions.eq("settlementPointName", settlementPointName))
					                       .list();
		}
		catch (HibernateException e) {
			throw new RTSPPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
	}
	
	public Float findMin(final Date start, final Date end, final String settlementPointName) throws RTSPPException {
		if (start == null || end == null || settlementPointName == null)
			throw new RTSPPException("Start date, end date, or name cannot be empty");
		Session session = null;
		Float result = 0f;
		try {
			session = sessionFactory.openSession();
			Float value = (Float) session.createCriteria(getPersistentClass())
							             .setProjection(Projections.min("settlementPointPrice"))
					                     .add(Restrictions.between("intervalDate", start, end))
					                     .add(Restrictions.eq("settlementPointName", settlementPointName))
					                     .uniqueResult();
			if (value != null)
				result = value;
		}
		catch (HibernateException e) {
			throw new RTSPPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	public Float findMax(final Date start, final Date end, final String settlementPointName) throws RTSPPException {
		if (start == null || end == null || settlementPointName == null)
			throw new RTSPPException("Start date, end date, or name cannot be empty");
		Session session = null;
		Float result = 0f;
		try {
			session = sessionFactory.openSession();
			Float value = (Float) session.createCriteria(getPersistentClass())
							              .setProjection(Projections.max("settlementPointPrice"))
					                      .add(Restrictions.between("intervalDate", start, end))
					                      .add(Restrictions.eq("settlementPointName", settlementPointName))
					                      .uniqueResult();
			if (value != null)
				result = value;
		}
		catch (HibernateException e) {
			throw new RTSPPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	public Float findOffPeakAverage(final Date nightStart, final Date nightEnd, final Date morningStart, final Date morningEnd, final String settlementPointName) throws RTSPPException {
		if (nightStart == null || nightEnd == null || morningStart == null || morningEnd == null || settlementPointName == null)
			throw new RTSPPException("Dates, and/or settlement point cannot be empty");
		Session session = null;
		Float result = 0f;
		try {
			session = sessionFactory.openSession();
			Double value = (Double) session.createCriteria(getPersistentClass())
							               .setProjection(Projections.avg("settlementPointPrice"))
							               .add(Restrictions.or(Restrictions.between("intervalDate", nightStart, nightEnd), 
							                        		    Restrictions.between("intervalDate", morningStart, morningEnd)))
					                       .add(Restrictions.eq("settlementPointName", settlementPointName))
					                       .uniqueResult();
			if (value != null)
			    result = value.floatValue();
		}
		catch (HibernateException e) {
			throw new RTSPPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<RTSPP> findOffPeakList(final Date nightStart, final Date nightEnd, final Date morningStart, final Date morningEnd, final String settlementPointName) throws RTSPPException {
		if (nightStart == null || nightEnd == null || morningStart == null || morningEnd == null || settlementPointName == null)
			throw new RTSPPException("Dates, and/or settlement point cannot be empty");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return (List<RTSPP>) session.createCriteria(getPersistentClass())
							               .add(Restrictions.or(Restrictions.between("intervalDate", nightStart, nightEnd), 
							                        		    Restrictions.between("intervalDate", morningStart, morningEnd)))
					                       .add(Restrictions.eq("settlementPointName", settlementPointName))
					                       .list();
		}
		catch (HibernateException e) {
			throw new RTSPPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
	}
	
	public Float findOffPeakMin(final Date nightStart, final Date nightEnd, final Date morningStart, final Date morningEnd, final String settlementPointName) throws RTSPPException {
		if (nightStart == null || nightEnd == null || morningStart == null || morningEnd == null || settlementPointName == null)
			throw new RTSPPException("Dates, and/or settlement point cannot be empty");
		Session session = null;
		Float result = 0f;
		try {
			session = sessionFactory.openSession();
			Float value = (Float) session.createCriteria(getPersistentClass())
							             .setProjection(Projections.min("settlementPointPrice"))
							             .add(Restrictions.or(Restrictions.between("intervalDate", nightStart, nightEnd), 
							                        		  Restrictions.between("intervalDate", morningStart, morningEnd)))
					                     .add(Restrictions.eq("settlementPointName", settlementPointName))
					                     .uniqueResult();
			if (value != null)
				result = value;
		}
		catch (HibernateException e) {
			throw new RTSPPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	public Float findOffPeakMax(final Date nightStart, final Date nightEnd, final Date morningStart, final Date morningEnd, final String settlementPointName) throws RTSPPException {
		if (nightStart == null || nightEnd == null || morningStart == null || morningEnd == null || settlementPointName == null)
			throw new RTSPPException("Dates, and/or settlement point cannot be empty");
		Session session = null;
		Float result = 0f;
		try {
			session = sessionFactory.openSession();
			Float value = (Float) session.createCriteria(getPersistentClass())
							             .setProjection(Projections.max("settlementPointPrice"))
							             .add(Restrictions.or(Restrictions.between("intervalDate", nightStart, nightEnd), 
							                        		    Restrictions.between("intervalDate", morningStart, morningEnd)))
					                     .add(Restrictions.eq("settlementPointName", settlementPointName))
					                     .uniqueResult();
			if (value != null)
				result = value;
		}
		catch (HibernateException e) {
			throw new RTSPPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Map<Date, Double> findPriceChangeBetweenSettlementPoints(final Date start, final Date end, final String settlementPointA, final String settlementPointB) throws RTSPPException {
        if (start == null || end == null || settlementPointA == null || settlementPointB == null)
            throw new RTSPPException("Entity, dates, or names cannot be empty");
		Session session = null;
        Map<Date, Double> results = new HashMap<Date, Double>();
        // TODO rewrite using Hibernate criteria
	    String sql = "select a.settlementPointPrice - b.settlementPointPrice, a.intervalDate from (select intervalDate, settlementPointPrice from " + getPersistentClass().getSimpleName().toLowerCase()  + " where settlementPointName = 'HB_HOUSTON' and intervalDate like '2012-11-11%') as a, (select intervalDate, settlementPointPrice from " + getPersistentClass().getSimpleName().toLowerCase()  + " where settlementPointName = 'HB_NORTH' and intervalDate like '2012-11-11%') as b where a.intervalDate = b.intervalDate order by a.intervalDate asc;";
        try {
			session = sessionFactory.openSession();
			List<Object> list = (List<Object>) session.createSQLQuery(sql).list();
			Iterator<Object> iter = list.iterator();
			while (iter.hasNext()) {
				Object[] object = (Object[]) iter.next();
				results.put((Date)object[1], (Double) object[0]);
			}
        }
		catch (HibernateException e) {
			throw new RTSPPException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
        return results;
	}

}
