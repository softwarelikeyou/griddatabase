package com.softwarelikeyou.facade;

import java.util.List;

import com.softwarelikeyou.exception.SettlementPointException;
import com.softwarelikeyou.model.dao.SettlementPointDAO;
import com.softwarelikeyou.model.entity.SettlementPoint;

public class SettlementPointFacade {
	public static SettlementPoint findById(final String name) throws SettlementPointException {
	    return new SettlementPointDAO().findById(name);
	}
	
	public static List<SettlementPoint> findAll() throws SettlementPointException {
	    return new SettlementPointDAO().findAll();
	}
	
	public static SettlementPoint findNextTemperature() throws SettlementPointException {
	    return new SettlementPointDAO().findNextTemperature();
	}
	
	public static SettlementPoint createOrUpdate(SettlementPoint entity) throws SettlementPointException {
		return new SettlementPointDAO().createOrUpdate(entity);
	}
	
	public static List<String> findNames() throws SettlementPointException {
	    return new SettlementPointDAO().findNames();
	}
}
