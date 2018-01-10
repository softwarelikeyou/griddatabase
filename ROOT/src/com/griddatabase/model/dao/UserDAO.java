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

public class UserDAO extends HibernateDAO<User, Long> {	
	public UserDAO() {
		super();
	}
	
	public UserDAO(final SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public User createOrUpdate(User entity) throws UserException  {
		if (entity == null)
			throw new UserException("User cannot be empty");
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (User) makePersistent(session, entity);
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
	
	@SuppressWarnings("unchecked")
	public List<User> findAll() throws UserException {
		Session session = null;
		List<User> entities = null;
		try {
			session = sessionFactory.openSession();
			entities = session.createQuery("from User").list();
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
	
	public User findById(Long id) throws UserException {
		if (id == null)
			throw new UserException("User Id cannot be empty");
		Session session = null;
		User entity = null;
		try {
			session = sessionFactory.openSession();
			entity = (User) findById(session, id, false);
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
	
	public User findByUsername(final String username) throws UserException {
		if (username == null)
			throw new UserException("Username cannot be empty");
		Session session = null;
		User entity = null;
		try {
			session = sessionFactory.openSession();
			entity = (User) session.createCriteria(User.class)
			                       .add(Restrictions.eq("userName", username))
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
	
	public void remove(User entity) throws UserException {
		if (entity == null)
			throw new UserException("User cannot be empty");
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