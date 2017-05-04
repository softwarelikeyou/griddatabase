package com.softwarelikeyou.facade;

import java.util.Date;
import java.util.List;

import com.softwarelikeyou.exception.SettlementPointException;
import com.softwarelikeyou.model.dao.SettlementPointTemperatureDAO;
import com.softwarelikeyou.model.entity.SettlementPointTemperature;

public class SettlementPointTemperatureFacade {

	private SettlementPointTemperatureFacade() { }
	
	public static List<SettlementPointTemperature> findAll() throws SettlementPointException {
		return SettlementPointTemperatureDAO.getInstance().findAll();
	}
	
	public static List<SettlementPointTemperature> findAllWhereTemperatureIsMinus50(final int resultSize) throws SettlementPointException {
		return SettlementPointTemperatureDAO.getInstance().findAllWhereTemperatureIsMinus50(resultSize);
	}
	
	public static List<SettlementPointTemperature> findByName(final String value) throws SettlementPointException {
		return SettlementPointTemperatureDAO.getInstance().findByName(value);
	}
	
	public static SettlementPointTemperature createOrUpdate(final SettlementPointTemperature entity) throws SettlementPointException {
        return SettlementPointTemperatureDAO.getInstance().createOrUpdate(entity);
	}
	
	public static List<SettlementPointTemperature> findByBetweenDates(final Date start, final Date end) throws SettlementPointException {				
		return SettlementPointTemperatureDAO.getInstance().findByBetweenDates(start, end);		
	}
}
