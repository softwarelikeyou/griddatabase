package com.griddatabase.model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.UserException;
import com.griddatabase.model.entity.User;
import com.softwarelikeyou.model.dao.HibernateDAO;
import com.softwarelikeyou.model.entity.UserData;

public class UserDataDAO extends HibernateDAO<UserData, Long> {	
	public UserDataDAO() {
		super();
	}
	
	public UserDataDAO(final SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public UserData createOrUpdate(UserData entity) throws UserException {
		if (entity == null)
			throw new UserException("User data cannot be empty");
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (UserData) makePersistent(session, entity);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
			    transaction.rollback();
			throw new UserException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entity;
	}
	
	public UserData findProperty(final User user, final String property) throws UserException {
		if (user == null)
			throw new UserException("User cannot be empty");
		if (property == null)
			throw new UserException("User property cannot be empty");
		Session session = null;
		UserData entity = null;
		try {
			session = sessionFactory.openSession();
			entity= (UserData) session.createCriteria(UserData.class)
			                                      .add(Restrictions.eq("userId", user.getId()))
			                                      .add(Restrictions.eq("property", property))
			                                      .uniqueResult();
		}
		catch (HibernateException e) {
			throw new UserException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserData> findAll(final User user) throws UserException {
		if (user == null)
			throw new UserException("User cannot be emtpy");
		Session session = null;
		List<UserData> entities = null;
		try {
			session = sessionFactory.openSession();
			entities = (List<UserData>) session.createCriteria(UserData.class)
			                                   .add(Restrictions.eq("userId", user.getId()))
			                                   .list();
		}
		catch (HibernateException e) {
			throw new UserException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entities;
	}
	
	public void remove(UserData entity) throws UserException {
		if (entity == null)
			throw new UserException("User data cannot be empty");
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			makeTransient(session, entity);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
			    transaction.rollback();
			throw new UserException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
	}
}