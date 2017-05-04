package com.softwarelikeyou.model.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import com.softwarelikeyou.util.HibernateUtil;

public abstract class HibernateDAO<T, ID extends Serializable> {
	private Class<T> persistentClass;

	protected static SessionFactory sessionFactory;
	
	public static void setSessionFactory(final SessionFactory factory) throws HibernateException {
		sessionFactory = factory;
	}
	
	@SuppressWarnings("unchecked")
	public HibernateDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	protected HibernateDAO(final SessionFactory sessionFactory) throws HibernateException {
		this.sessionFactory = sessionFactory;
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	@SuppressWarnings("unchecked")
	protected T findById(final Session session, final ID id, final boolean lock) throws HibernateException {
		T entity;
		if (lock)
			entity = (T) session.load(getPersistentClass(), id, LockOptions.UPGRADE);
		else
			entity = (T) session.get(getPersistentClass(), id);
		return entity;
	}
	
	protected List<T> findAll(final Session session) throws HibernateException {
		return findByCriteria(session);
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> findByExample(final Session session, final T exampleProperty, final String... excludeProperty) throws HibernateException {
		Criteria criteria = session.createCriteria(getPersistentClass());
		Example example = Example.create(exampleProperty).ignoreCase().enableLike(MatchMode.ANYWHERE);
		for (String exclude : excludeProperty)
		    example.excludeProperty(exclude);
		criteria.add(example);
		criteria.uniqueResult();
		return criteria.list();
	}
	
	protected T makePersistent(final Session session, T entity) throws HibernateException {
		session.saveOrUpdate(entity);
		return entity;
	}
	
	protected void makeTransient(final Session session, final T entity) throws HibernateException {
        session.delete(entity);
	}
	
	protected void flush(final Session session) throws HibernateException {
		session.flush();
	}
	
	protected void clear(final Session session)  throws HibernateException {
		session.clear();
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(final Session session, final Criterion... criterion) throws HibernateException {
		Criteria criteria = session.createCriteria(getPersistentClass());
		for (Criterion c: criterion)
		    criteria.add(c);
		return criteria.list();
	}
}
