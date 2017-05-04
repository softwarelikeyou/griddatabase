package com.softwarelikeyou.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.softwarelikeyou.exception.H48DAMHPException;
import com.softwarelikeyou.model.dao.H48DAMHPDAO;
import com.softwarelikeyou.model.entity.H48DAMHP;

public class H48DAMHPFacade {
	public static H48DAMHP findById(final Date intervalDate) throws H48DAMHPException {
	    return new H48DAMHPDAO().findById(intervalDate);
	}
	
	public static List<H48DAMHP> findAll() throws H48DAMHPException {
	    return new H48DAMHPDAO().findAll();
	}
	
	public static List<H48DAMHP> findByGreaterThanDate(final Date date) throws H48DAMHPException {
	    return new H48DAMHPDAO().findByGreaterThanDate(date);
	}
	
	public static List<H48DAMHP> findAllDistinctShortDate() throws H48DAMHPException {
		return new H48DAMHPDAO().findAllDistinctShortDate();
	}
	
	public static List<H48DAMHP> findByBetweenDates(final Date start, final Date end) throws H48DAMHPException {
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
		
		return new H48DAMHPDAO().findByBetweenDates(startCal.getTime(), endCal.getTime());
	}
	
	public static H48DAMHP createOrUpdate(H48DAMHP entity) throws H48DAMHPException {
		return new H48DAMHPDAO().createOrUpdate(entity);
	}
	
	public static List<String> findResourceNames() throws H48DAMHPException {
		return new H48DAMHPDAO().findResourceNames();
	}
}
