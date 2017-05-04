package com.softwarelikeyou.model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.ZipCodeException;
import com.softwarelikeyou.model.dao.HibernateDAO;
import com.softwarelikeyou.model.entity.ZipCodeTemperature;

public class ZipCodeTemperatureDAO extends HibernateDAO<ZipCodeTemperature, Long> {	
	
	private static ZipCodeTemperatureDAO instance = new ZipCodeTemperatureDAO();
	
	private ZipCodeTemperatureDAO() { }
	
	public static ZipCodeTemperatureDAO getInstance() {
		return instance;
	}
	
	public ZipCodeTemperature findById(final long id ) throws ZipCodeException {
		Session session = null;
		ZipCodeTemperature entity;
		try {
			session = sessionFactory.openSession();
			entity = (ZipCodeTemperature) findById(id);
		}
		catch (HibernateException e) {
			throw new ZipCodeException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<ZipCodeTemperature> findByName(final String name) throws ZipCodeException {
		if (name == null)
			throw new ZipCodeException("Name cannot be empty");
		Session session = null;
		List<ZipCodeTemperature> list;
		try {
			session = sessionFactory.openSession();
			list = (List<ZipCodeTemperature>) session.createCriteria(ZipCodeTemperature.class)
			                                 .add(Restrictions.eq("name", name))
			                                 .list();
		}
		catch (HibernateException e) {
			throw new ZipCodeException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<ZipCodeTemperature> findAll() throws ZipCodeException {
		Session session = null;
		List<ZipCodeTemperature> entities = null;
		try {
			session = sessionFactory.openSession();
			entities = (List<ZipCodeTemperature>) session.createQuery("from ZipCodeTemperature").list();
		}
		catch (HibernateException e) {
			throw new ZipCodeException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entities;		
	}
	
	public ZipCodeTemperature createOrUpdate(ZipCodeTemperature entity) throws ZipCodeException {
		if (entity == null)
			throw new ZipCodeException("ZipCode cannot be empty");
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (ZipCodeTemperature) makePersistent(session, entity);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
			   transaction.rollback();
			throw new ZipCodeException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entity;
	}
}
