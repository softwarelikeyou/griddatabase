package com.softwarelikeyou.facade;

import java.util.Date;
import java.util.List;

import com.softwarelikeyou.exception.DailyException;
import com.softwarelikeyou.model.dao.rtspp.RTSPPDailyDAO;
import com.softwarelikeyou.model.entity.Daily;
import com.softwarelikeyou.model.entity.rtspp.RTSPPDaily;

public class RTSPPDailyFacade {

	public static RTSPPDaily createOrUpdate(RTSPPDaily entity) throws DailyException {
		return RTSPPDailyDAO.getInstance().createOrUpdate(entity);
	}
	
	public static List<Daily> findByBetweenDates(final Date start, final Date end) throws DailyException {
		return RTSPPDailyDAO.getInstance().findByBetweenDates(start, end);
	}
	
	public static List<Daily> findByBetweenDatesName(final Date start, final Date end, final String name) throws DailyException {
		return RTSPPDailyDAO.getInstance().findByBetweenDatesName(start, end, name);
	}
}
