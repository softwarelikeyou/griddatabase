package com.softwarelikeyou.facade;

import java.util.List;

import com.softwarelikeyou.exception.CountyContourException;
import com.softwarelikeyou.model.dao.CountyContourDAO;
import com.softwarelikeyou.model.entity.CountyContour;

public class CountyContourFacade {

	private CountyContourFacade() { }

	public static List<CountyContour> findByName(final String value) throws CountyContourException {
		return CountyContourDAO.getInstance().findByName(value);
	}
	
	public static List<CountyContour> findByNamePart(final String namePart) throws CountyContourException {
		return CountyContourDAO.getInstance().findByNamePart(namePart);
	}
	
	public static List<String> findNameParts() throws CountyContourException {
		return CountyContourDAO.getInstance().findNameParts();
	}
	
	public static CountyContour createOrUpdate(CountyContour entity) throws CountyContourException {
        return CountyContourDAO.getInstance().createOrUpdate(entity);
	}
}
