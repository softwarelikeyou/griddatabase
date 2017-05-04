package com.softwarelikeyou.facade;

import java.util.List;

import com.softwarelikeyou.exception.ZipCodeException;
import com.softwarelikeyou.model.dao.ZipCodeTemperatureDAO;
import com.softwarelikeyou.model.entity.ZipCodeTemperature;

public class ZipCodeTemperatureFacade {

	private ZipCodeTemperatureFacade() { }
	
	public static List<ZipCodeTemperature> findAll() throws ZipCodeException {
		return ZipCodeTemperatureDAO.getInstance().findAll();
	}

	public static ZipCodeTemperature findById(final long id) throws ZipCodeException {
        return ZipCodeTemperatureDAO.getInstance().findById(id);
	}
	
	public static List<ZipCodeTemperature> findByName(final String value) throws ZipCodeException {
		return ZipCodeTemperatureDAO.getInstance().findByName(value);
	}
	
	public static ZipCodeTemperature createOrUpdate(final ZipCodeTemperature entity) throws ZipCodeException {
		// TODO Update the cache for map displaying, throw an event, possible a web service to the GUI - CountyContourCache.setColor(entity);
        return ZipCodeTemperatureDAO.getInstance().createOrUpdate(entity);
	}
}
