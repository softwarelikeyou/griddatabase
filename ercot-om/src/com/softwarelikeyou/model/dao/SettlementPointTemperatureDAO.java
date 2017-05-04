package com.softwarelikeyou.model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.SettlementPointException;
import com.softwarelikeyou.model.dao.HibernateDAO;
import com.softwarelikeyou.model.entity.SettlementPointTemperature;

public class SettlementPointTemperatureDAO extends HibernateDAO<SettlementPointTemperature, String> {	
	
	private static SettlementPointTemperatureDAO instance = new SettlementPointTemperatureDAO();
	
	private SettlementPointTemperatureDAO() { }
	
	public static SettlementPointTemperatureDAO getInstance() {
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<SettlementPointTemperature> findByName(final String name) throws SettlementPointException {
		if (name == null)
			throw new SettlementPointException("Name cannot be empty");
		Session session = null;
		List<SettlementPointTemperature> list;
		try {
			session = sessionFactory.openSession();
			list = (List<SettlementPointTemperature>) session.createCriteria(SettlementPointTemperature.class)
			                                                 .add(Restrictions.eq("name", name))
			                                                 .list();
		}
		catch (HibernateException e) {
			throw new SettlementPointException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<SettlementPointTemperature> findAllWhereTemperatureIsMinus50(final int resultSize) throws SettlementPointException {
		Session session = null;
		List<SettlementPointTemperature> list;
		int size = 100000;
		if (resultSize <= size) {
			size = resultSize;
		}
		try {
			session = sessionFactory.openSession();
			list = (List<SettlementPointTemperature>) session.createCriteria(SettlementPointTemperature.class)
			                                                 .add(Restrictions.eq("temperature", -50))
			                                                 .setFirstResult(1)
			                                                 .setMaxResults(size)
			                                                 .list();
		}
		catch (HibernateException e) {
			throw new SettlementPointException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<SettlementPointTemperature> findAll() throws SettlementPointException {
		Session session = null;
		List<SettlementPointTemperature> entities = null;
		try {
			session = sessionFactory.openSession();
			entities = (List<SettlementPointTemperature>) session.createQuery("from SettlementPointTemperature").list();
		}
		catch (HibernateException e) {
			throw new SettlementPointException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entities;		
	}
	
	public SettlementPointTemperature createOrUpdate(SettlementPointTemperature entity) throws SettlementPointException {
		if (entity == null)
			throw new SettlementPointException("SettlementPoint cannot be empty");
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (SettlementPointTemperature) makePersistent(session, entity);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
			   transaction.rollback();
			throw new SettlementPointException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<SettlementPointTemperature> findByBetweenDates(final Date start, final Date end) throws SettlementPointException {
		if (start == null || end == null)
			throw new SettlementPointException("Date cannot be empty");
		Session session = null;
		List<SettlementPointTemperature> list = null;
		try {
			session = sessionFactory.openSession();
			list = (List<SettlementPointTemperature>) session.createCriteria(SettlementPointTemperature.class)
					                                         .add(Restrictions.between("dateTime", start, end))
					                                         .add(Restrictions.gt("temperature", -50))
					                                         .addOrder(Order.asc("dateTime"))
					                                         .addOrder(Order.asc("settlementPoint"))
					                                         .list();
		}
		catch (HibernateException e) {
			throw new SettlementPointException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
}
