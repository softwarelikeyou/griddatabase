package com.softwarelikeyou.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.softwarelikeyou.exception.H48DAMASException;
import com.softwarelikeyou.model.dao.H48DAMASAGRRSLDDAO;
import com.softwarelikeyou.model.entity.H48DAMASAGRRSLD;

public class H48DAMASAGRRSLDFacade {	
	public static List<H48DAMASAGRRSLD> findAll() throws H48DAMASException {
	    return new H48DAMASAGRRSLDDAO().findAll();
	}
	
	public static List<H48DAMASAGRRSLD> findByGreaterThanDate(final Date date) throws H48DAMASException {
	    return new H48DAMASAGRRSLDDAO().findByGreaterThanDate(date);
	}
	
	public static List<H48DAMASAGRRSLD> findAllDistinctShortDate() throws H48DAMASException {
		return new H48DAMASAGRRSLDDAO().findAllDistinctShortDate();
	}
	
	public static List<H48DAMASAGRRSLD> findByBetweenDates(final Date start, final Date end) throws H48DAMASException {
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
		
		return new H48DAMASAGRRSLDDAO().findByBetweenDates(startCal.getTime(), endCal.getTime());
	}
	
	public static H48DAMASAGRRSLD createOrUpdate(H48DAMASAGRRSLD entity) throws H48DAMASException {
		return new H48DAMASAGRRSLDDAO().createOrUpdate(entity);
	}
}
