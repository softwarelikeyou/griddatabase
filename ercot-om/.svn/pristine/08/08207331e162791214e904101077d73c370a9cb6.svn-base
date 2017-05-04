package com.softwarelikeyou.facade;

import java.util.Date;
import java.util.List;

import com.softwarelikeyou.exception.HenryHubException;
import com.softwarelikeyou.model.dao.HenryHubDAO;
import com.softwarelikeyou.model.entity.HenryHub;

public class HenryHubFacade {			
	public static List<HenryHub> findAll() throws HenryHubException {
		return new HenryHubDAO().findAll();
	}
	
	public static HenryHub findById(final Date date) throws HenryHubException {
		return new HenryHubDAO().findById(date);
	}
	
	public static List<HenryHub> findByBetweenDates(final Date start, final Date end) throws HenryHubException {
		return new HenryHubDAO().findByBetweenDates(start, end);
	}
	
	public static HenryHub createOrUpdate(final HenryHub HenryHub) throws HenryHubException {
		return new HenryHubDAO().createOrUpdate(HenryHub);
	}
}
