package com.softwarelikeyou.model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.SettlementPointException;
import com.softwarelikeyou.model.entity.SettlementPoint;

public class SettlementPointDAO extends HibernateDAO<SettlementPoint, String> {
	
	public SettlementPoint createOrUpdate(SettlementPoint entity) throws SettlementPointException {
		if (entity == null)
			throw new SettlementPointException("Entity cannot be empty");
		
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (SettlementPoint) makePersistent(session, entity);
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
	
	public SettlementPoint findById(final String name) throws SettlementPointException {
		if (name == null)
			throw new SettlementPointException("Name cannot be empty");
		Session session = null;
		SettlementPoint result = null;
		try {
			session = sessionFactory.openSession();
		    result = findById(session, name, false);
		}
		catch (HibernateException e) {
			throw new SettlementPointException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<SettlementPoint> findAll() throws SettlementPointException {
		Session session = null;
		List<SettlementPoint> entities = null;
		try {
			session = sessionFactory.openSession();
			entities = session.createQuery("from SettlementPoint order by name desc").list();
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
	
	@SuppressWarnings("unchecked")
	public SettlementPoint findNextTemperature() throws SettlementPointException {
		Session session = null;
		List<SettlementPoint> list = null;
		try {
			session = sessionFactory.openSession();
			list = (List<SettlementPoint>) session.createCriteria(SettlementPoint.class)
                                                  .add(Restrictions.eq("temperature", -50))
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
		if (list != null)
		   return list.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findNames() throws SettlementPointException {
		Session session = null;
		List<String> list = null;
		try {
			session = sessionFactory.openSession();
			list = (List<String>) session.createCriteria(SettlementPoint.class)
                    .setProjection(Projections.property("name"))
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
