package com.softwarelikeyou.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.softwarelikeyou.exception.H48DAMASException;
import com.softwarelikeyou.model.dao.H48DAMASAGONNSDAO;
import com.softwarelikeyou.model.entity.H48DAMASAGONNS;

public class H48DAMASAGONNSFacade {	
	public static List<H48DAMASAGONNS> findAll() throws H48DAMASException {
	    return new H48DAMASAGONNSDAO().findAll();
	}
	
	public static List<H48DAMASAGONNS> findByGreaterThanDate(final Date date) throws H48DAMASException {
	    return new H48DAMASAGONNSDAO().findByGreaterThanDate(date);
	}
	
	public static List<H48DAMASAGONNS> findAllDistinctShortDate() throws H48DAMASException {
		return new H48DAMASAGONNSDAO().findAllDistinctShortDate();
	}
	
	public static List<H48DAMASAGONNS> findByBetweenDates(final Date start, final Date end) throws H48DAMASException {
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
		
		return new H48DAMASAGONNSDAO().findByBetweenDates(startCal.getTime(), endCal.getTime());
	}
	
	public static H48DAMASAGONNS createOrUpdate(H48DAMASAGONNS entity) throws H48DAMASException {
		return new H48DAMASAGONNSDAO().createOrUpdate(entity);
	}
}
