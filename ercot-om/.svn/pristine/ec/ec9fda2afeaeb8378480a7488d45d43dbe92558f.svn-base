package com.softwarelikeyou.model.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.softwarelikeyou.exception.FileException;
import com.softwarelikeyou.model.dao.HibernateDAO;
import com.softwarelikeyou.model.entity.File;
import com.softwarelikeyou.model.entity.File.FileType;

public class FileDAO extends HibernateDAO<File, Long> {	
	public File findByName(final String name) throws FileException {
		if (name == null)
			throw new FileException("Name cannot be empty");
		Session session = null;
		File entity = new File();
		try {
			session = sessionFactory.openSession();
			List<?> list = session.createCriteria(File.class)
			               .add(Restrictions.eq("name", name))
			               .list();
			Iterator<?> iterator = list.iterator();
			if (list.size() > 1)
				throw new FileException("Found multiple file records for " + name);
            entity = iterator.hasNext() ? (File) iterator.next() : null;
		}
		catch (HibernateException e) {
			throw new FileException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<String> findNamesByType(final FileType type) throws FileException {
		if (type == null)
			throw new FileException("Type cannot be empty");
		Session session = null;
		List<String> list;
		try {
			session = sessionFactory.openSession();
			list = (List<String>) session.createCriteria(File.class)
			                            .add(Restrictions.eq("type", type))
			                            .setProjection(Projections.property("name"))
			                            .list();
		}
		catch (HibernateException e) {
			throw new FileException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<File> findByType(final FileType type) throws FileException {
		if (type == null)
			throw new FileException("Type cannot be empty");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return (List<File>) session.createCriteria(File.class)
			                           .add(Restrictions.eq("type", type))
			                           .list();
		}
		catch (HibernateException e) {
			throw new FileException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<File> findAll() throws FileException {
		Session session = null;
		List<File> entities = null;
		try {
			session = sessionFactory.openSession();
			entities = (List<File>) session.createQuery("from File").list();
		}
		catch (HibernateException e) {
			throw new FileException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entities;		
	}
	
	public File createOrUpdate(File entity) throws FileException {
		if (entity == null)
			throw new FileException("File cannot be empty");
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			entity = (File) makePersistent(session, entity);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
			   transaction.rollback();
			throw new FileException(e);
		}
		finally {
			if (session.isOpen())
			    session.close();
			session = null;
		}
		return entity;
	}
}
