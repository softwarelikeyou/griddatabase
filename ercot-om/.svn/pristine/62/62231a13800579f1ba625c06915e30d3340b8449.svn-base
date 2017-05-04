package com.softwarelikeyou.facade;

import java.util.Date;
import java.util.List;

import com.softwarelikeyou.exception.DailyException;
import com.softwarelikeyou.model.dao.ascpc.ASCPCDailyDAO;
import com.softwarelikeyou.model.entity.Daily;
import com.softwarelikeyou.model.entity.ascpc.ASCPCDaily;

public class ASCPCDailyFacade {
	
	public static Daily createOrUpdate(final ASCPCDaily entity) throws DailyException {
        return ASCPCDailyDAO.getInstance().createOrUpdate(entity);
	}
	
	public static List<Daily> findByBetweenDates(final Date start, final Date end) throws DailyException {				
		return ASCPCDailyDAO.getInstance().findByBetweenDates(start, end);		
	}
	
	public static List<Daily> findByBetweenDatesName(final Date start, final Date end, final String name) throws DailyException {				
		return ASCPCDailyDAO.getInstance().findByBetweenDatesName(start, end, name);		
	}
}
