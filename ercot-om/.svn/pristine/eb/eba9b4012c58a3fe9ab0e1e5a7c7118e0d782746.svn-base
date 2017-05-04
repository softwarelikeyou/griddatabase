package com.softwarelikeyou.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.softwarelikeyou.exception.H48DAMASException;
import com.softwarelikeyou.model.dao.H48DAMASCSDAO;
import com.softwarelikeyou.model.entity.H48DAMASCS;

public class H48DAMASCSFacade {
	public static H48DAMASCS findById(final Date intervalDate) throws H48DAMASException {
	    return new H48DAMASCSDAO().findById(intervalDate);
	}
	
	public static List<H48DAMASCS> findAll() throws H48DAMASException {
	    return new H48DAMASCSDAO().findAll();
	}
	
	public static List<H48DAMASCS> findByGreaterThanDate(final Date date) throws H48DAMASException {
	    return new H48DAMASCSDAO().findByGreaterThanDate(date);
	}
	
	public static List<H48DAMASCS> findAllDistinctShortDate() throws H48DAMASException {
		return new H48DAMASCSDAO().findAllDistinctShortDate();
	}
	
	public static List<H48DAMASCS> findByBetweenDates(final Date start, final Date end) throws H48DAMASException {
		Calendar startCal = new GregorianCalendar();
		startCal.setTime(start);
		startCal.set(Calendar.HOUR_OF_DAY, 0);
		startCal.set(Calendar.MINUTE, 0);
		startCal.set(Calendar.SECOND, 0);
		
		Calendar endCal = new GregorianCalendar();
		endCal.setTime(end);
		endCal.set(Calendar.HOUR_OF_DAY, 23);
		endCal.set(Calendar.MINUTE, 59);
		endCal.set(Calendar.SECOND, 59);
		
		return new H48DAMASCSDAO().findByBetweenDates(startCal.getTime(), endCal.getTime());
	}
	
	public static H48DAMASCS createOrUpdate(H48DAMASCS entity) throws H48DAMASException {
		return new H48DAMASCSDAO().createOrUpdate(entity);
	}
}
