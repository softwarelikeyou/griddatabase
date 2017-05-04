package com.softwarelikeyou.model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.ZipCodeException;
import com.softwarelikeyou.model.dao.HibernateDAO;
import com.softwarelikeyou.model.entity.ZipCode;

public class ZipCodeDAO extends HibernateDAO<ZipCode, String> {	
	
	private static ZipCodeDAO instance = new ZipCodeDAO();
	
	private ZipCodeDAO() { }
	
	public static ZipCodeDAO getInstance() {
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<ZipCode> findByCounty(final String county) throws ZipCodeException {
		if (county == null)
			throw new ZipCodeException("County cannot be empty");
		Session session = null;
		List<ZipCode> list;
		try {
			session = sessionFactory.openSession();
			list = (List<ZipCode>) session.createCriteria(ZipCode.class)
			                              .add(Restrictions.eq("county", county))
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
	
	public ZipCode findByName(final String name) throws ZipCodeException {
		if (name == null)
			throw new ZipCodeException("Name cannot be empty");
		Session session = null;
		ZipCode result;
		try {
			session = sessionFactory.openSession();
			result = (ZipCode) session.createCriteria(ZipCode.class)
			                              .add(Restrictions.eq("name", name))
			                              .uniqueResult();
		}
		catch (HibernateException e) {
			throw new ZipCodeException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ZipCode> findAll() throws ZipCodeException {
		Session session = null;
		List<ZipCode> entities = null;
		try {
			session = sessionFactory.openSession();
			entities = (List<ZipCode>) session.createQuery("from ZipCode").list();
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
	
	public ZipCode createOrUpdate(ZipCode entity) throws ZipCodeException {
		if (entity == null)
			throw new ZipCodeException("ZipCode cannot be empty");
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (ZipCode) makePersistent(session, entity);
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
