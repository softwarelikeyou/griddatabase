package com.softwarelikeyou.facade;

import java.util.List;

import com.softwarelikeyou.exception.ZipCodeException;
import com.softwarelikeyou.model.dao.ZipCodeDAO;
import com.softwarelikeyou.model.entity.ZipCode;

public class ZipCodeFacade {

	private ZipCodeFacade() { }
	
	public static List<ZipCode> findAll() throws ZipCodeException {
		return ZipCodeDAO.getInstance().findAll();
	}

	public static List<ZipCode> findByCounty(final String value) throws ZipCodeException {
		return ZipCodeDAO.getInstance().findByCounty(value);
	}
	
	public static ZipCode findByName(final String value) throws ZipCodeException {
		return ZipCodeDAO.getInstance().findByName(value);
	}
	public static ZipCode createOrUpdate(ZipCode entity) throws ZipCodeException {
        return ZipCodeDAO.getInstance().createOrUpdate(entity);
	}
}
