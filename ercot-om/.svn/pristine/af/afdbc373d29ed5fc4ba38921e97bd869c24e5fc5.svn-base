package com.softwarelikeyou.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.softwarelikeyou.exception.H48DAMASException;
import com.softwarelikeyou.model.dao.H48DAMASAGRRSNCDAO;
import com.softwarelikeyou.model.entity.H48DAMASAGRRSNC;

public class H48DAMASAGRRSNCFacade {	
	public static List<H48DAMASAGRRSNC> findAll() throws H48DAMASException {
	    return new H48DAMASAGRRSNCDAO().findAll();
	}
	
	public static List<H48DAMASAGRRSNC> findByGreaterThanDate(final Date date) throws H48DAMASException {
	    return new H48DAMASAGRRSNCDAO().findByGreaterThanDate(date);
	}
	
	public static List<H48DAMASAGRRSNC> findAllDistinctShortDate() throws H48DAMASException {
		return new H48DAMASAGRRSNCDAO().findAllDistinctShortDate();
	}
	
	public static List<H48DAMASAGRRSNC> findByBetweenDates(final Date start, final Date end) throws H48DAMASException {
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
		
		return new H48DAMASAGRRSNCDAO().findByBetweenDates(startCal.getTime(), endCal.getTime());
	}
	
	public static H48DAMASAGRRSNC createOrUpdate(H48DAMASAGRRSNC entity) throws H48DAMASException {
		return new H48DAMASAGRRSNCDAO().createOrUpdate(entity);
	}
}
