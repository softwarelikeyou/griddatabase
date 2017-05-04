package com.softwarelikeyou.model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.CountyContourException;
import com.softwarelikeyou.model.dao.HibernateDAO;
import com.softwarelikeyou.model.entity.CountyContour;

public class CountyContourDAO extends HibernateDAO<CountyContour, String> {	
	
	private static CountyContourDAO instance = new CountyContourDAO();
	
	private CountyContourDAO() { }
	
	public static CountyContourDAO getInstance() {
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<CountyContour> findByName(final String name) throws CountyContourException {
		if (name == null)
			throw new CountyContourException("Name cannot be empty");
		Session session = null;
		List<CountyContour> list;
		try {
			session = sessionFactory.openSession();
			list = (List<CountyContour>) session.createCriteria(CountyContour.class)
			                                    .add(Restrictions.eq("name", name))
			                                    .addOrder(Order.desc("id") )
			                                    .list();
		}
		catch (HibernateException e) {
			throw new CountyContourException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<CountyContour> findByNamePart(final String namePart) throws CountyContourException {
		if (namePart == null)
			throw new CountyContourException("Name cannot be empty");
		Session session = null;
		List<CountyContour> list;
		try {
			session = sessionFactory.openSession();
			list = (List<CountyContour>) session.createCriteria(CountyContour.class)
			                                    .add(Restrictions.eq("namePart", namePart))
			                                    .addOrder(Order.desc("id") )
			                                    .list();
		}
		catch (HibernateException e) {
			throw new CountyContourException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findNameParts() throws CountyContourException {
		Session session = null;
		List<String> list = null;;
		try {
			session = sessionFactory.openSession();
			list = (List<String>) session.createCriteria(CountyContour.class)
					                     .setProjection(Projections.distinct(Projections.property("namePart")))
					                     .addOrder(Order.desc("namePart"))
					                     .list();
		}
		catch (HibernateException e) {
			throw new CountyContourException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	public CountyContour createOrUpdate(CountyContour entity) throws CountyContourException {
		if (entity == null)
			throw new CountyContourException("CountyContour cannot be empty");
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (CountyContour) makePersistent(session, entity);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
			   transaction.rollback();
			throw new CountyContourException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entity;
	}
}
