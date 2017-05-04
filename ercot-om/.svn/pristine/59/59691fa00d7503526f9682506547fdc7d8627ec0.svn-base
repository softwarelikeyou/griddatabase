package com.softwarelikeyou.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.softwarelikeyou.exception.RTDAMException;
import com.softwarelikeyou.model.dao.RTDAMDAO;
import com.softwarelikeyou.model.entity.RTDAM;

public class RTDAMFacade {
	public static RTDAM findById(final Date intervalDate) throws RTDAMException {
	    return new RTDAMDAO().findById(intervalDate);
	}
	
	public static List<RTDAM> findAll() throws RTDAMException {
	    return new RTDAMDAO().findAll();
	}
	
	public static List<RTDAM> findByGreaterThanDate(final Date date) throws RTDAMException {
	    return new RTDAMDAO().findByGreaterThanDate(date);
	}
	
	public static List<RTDAM> findAllDistinctShortDate() throws RTDAMException {
		return new RTDAMDAO().findAllDistinctShortDate();
	}
	
	public static List<RTDAM> findByBetweenDates(final Date start, final Date end) throws RTDAMException {
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
		return new RTDAMDAO().findByBetweenDates(startCal.getTime(), endCal.getTime());
	}
	
	public static List<RTDAM> findByBetweenDatesSettlementPoint(final Date start, final Date end, final String settlementPoint) throws RTDAMException {
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
		return new RTDAMDAO().findByBetweenDatesSettlementPoint(startCal.getTime(), endCal.getTime(), settlementPoint);
	}
	
	public static RTDAM createOrUpdate(RTDAM entity) throws RTDAMException {
		return new RTDAMDAO().createOrUpdate(entity);
	}
	
	public static RTDAM findPreviousInterval(RTDAM rtdam) throws RTDAMException {
	    Calendar previousInterval = Calendar.getInstance();
	    previousInterval.setTime(rtdam.getIntervalDate());
	    previousInterval.add(Calendar.HOUR, -1);
		return new RTDAMDAO().findPreviousInterval(previousInterval.getTime(), rtdam.getSettlementPoint());
	}
	
	public static List<String> findSettlementPoints() throws RTDAMException {
		return new RTDAMDAO().findSettlementPoints();
	}
	
	public static List<RTDAM> findZeroVelocity(String settlementPoint) throws RTDAMException {
		return new RTDAMDAO().findZeroVelocity(settlementPoint);
	}
	
	public static List<RTDAM> findZeroAcceleration(String settlementPoint) throws RTDAMException {
		return new RTDAMDAO().findZeroAcceleration(settlementPoint);
	}
}
