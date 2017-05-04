package com.softwarelikeyou.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.softwarelikeyou.exception.H48DAMASException;
import com.softwarelikeyou.model.dao.H48DAMASAGOFFNSDAO;
import com.softwarelikeyou.model.entity.H48DAMASAGOFFNS;

public class H48DAMASAGOFFNSFacade {	
	public static List<H48DAMASAGOFFNS> findAll() throws H48DAMASException {
	    return new H48DAMASAGOFFNSDAO().findAll();
	}
	
	public static List<H48DAMASAGOFFNS> findByGreaterThanDate(final Date date) throws H48DAMASException {
	    return new H48DAMASAGOFFNSDAO().findByGreaterThanDate(date);
	}
	
	public static List<H48DAMASAGOFFNS> findAllDistinctShortDate() throws H48DAMASException {
		return new H48DAMASAGOFFNSDAO().findAllDistinctShortDate();
	}
	
	public static List<H48DAMASAGOFFNS> findByBetweenDates(final Date start, final Date end) throws H48DAMASException {
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
		
		return new H48DAMASAGOFFNSDAO().findByBetweenDates(startCal.getTime(), endCal.getTime());
	}
	
	public static H48DAMASAGOFFNS createOrUpdate(H48DAMASAGOFFNS entity) throws H48DAMASException {
		return new H48DAMASAGOFFNSDAO().createOrUpdate(entity);
	}
}
