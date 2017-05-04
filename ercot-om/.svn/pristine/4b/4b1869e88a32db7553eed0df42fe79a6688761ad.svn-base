package com.softwarelikeyou.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.softwarelikeyou.exception.ASCPCException;
import com.softwarelikeyou.model.dao.ascpc.ASCPCDAO;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;

public class ASCPCFacade {

	public static ASCPC createOrUpdate(ASCPC entity) throws ASCPCException {
		return ASCPCDAO.getInstance().createOrUpdate(entity);
	}
	
	public static List<Date> findAllDistinctShortDate() throws ASCPCException {
		return ASCPCDAO.getInstance().findAllDistinctShortDate();
	}
	
	public static List<ASCPC> findBetweenDates(final Date start, final Date end) throws ASCPCException {	
		return ASCPCDAO.getInstance().findBetweenDates(start, end);
	}
	
	public static ASCPC findPreviousInterval(Date date) throws ASCPCException {
	    Calendar previousInterval = Calendar.getInstance();
	    previousInterval.setTime(date);
	    previousInterval.add(Calendar.HOUR, -1);
	    return ASCPCDAO.getInstance().findByIntervalDate(previousInterval.getTime());
	}
	
    public static Float findDailyAverage(final Date date, final String price) throws ASCPCException {
        if (date == null)
            throw new ASCPCException("Date cannot be empty");

        Calendar start = Calendar.getInstance();
        start.setTime(date);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);

        Calendar end = Calendar.getInstance();
        end.setTime(date);
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        
        return ASCPCDAO.getInstance().findAverage(start.getTime(), end.getTime(), price);
    }
    
    public static Float findPeakAverage(final Date date, final String price) throws ASCPCException {
        if (date == null)
            throw new ASCPCException("Date cannot be empty");

        Calendar start = Calendar.getInstance();
        start.setTime(date);
        start.set(Calendar.HOUR_OF_DAY, 7);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);

        Calendar end = Calendar.getInstance();
        end.setTime(date);
        end.set(Calendar.HOUR_OF_DAY, 22);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        
        return ASCPCDAO.getInstance().findAverage(start.getTime(), end.getTime(), price);
    }
    
    public static Float findOffPeakAverage(final Date date, final String price) throws ASCPCException {
        if (date == null)
            throw new ASCPCException("Date cannot be empty");

        Calendar nightStart = Calendar.getInstance();
        nightStart.setTime(date);
        nightStart.set(Calendar.HOUR_OF_DAY, 23);
        nightStart.set(Calendar.MINUTE, 0);
        nightStart.set(Calendar.SECOND, 0);

        Calendar nightEnd = Calendar.getInstance();
        nightEnd.setTime(date);
        nightEnd.set(Calendar.HOUR_OF_DAY, 23);
        nightEnd.set(Calendar.MINUTE, 59);
        nightEnd.set(Calendar.SECOND, 59);

        Calendar morningStart = Calendar.getInstance();
        morningStart.setTime(date);
        morningStart.set(Calendar.HOUR_OF_DAY, 0);
        morningStart.set(Calendar.MINUTE, 0);
        morningStart.set(Calendar.SECOND, 0);

        Calendar morningEnd = Calendar.getInstance();
        morningEnd.setTime(date);
        morningEnd.set(Calendar.HOUR_OF_DAY, 6);
        morningEnd.set(Calendar.MINUTE, 59);
        morningEnd.set(Calendar.SECOND, 59);
        
        return ASCPCDAO.getInstance().findOffPeakAverage(nightStart.getTime(), nightEnd.getTime(), morningStart.getTime(), morningEnd.getTime(), price);
    }
    
    public static Float findDailyMin(final Date date, final String price) throws ASCPCException {
        if (date == null)
            throw new ASCPCException("Date cannot be empty");

        Calendar start = Calendar.getInstance();
        start.setTime(date);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);

        Calendar end = Calendar.getInstance();
        end.setTime(date);
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        
        return ASCPCDAO.getInstance().findMin(start.getTime(), end.getTime(), price);
    }
    
    public static Float findPeakMin(final Date date, final String price) throws ASCPCException {
        if (date == null)
            throw new ASCPCException("Date cannot be empty");

        Calendar start = Calendar.getInstance();
        start.setTime(date);
        start.set(Calendar.HOUR_OF_DAY, 7);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);

        Calendar end = Calendar.getInstance();
        end.setTime(date);
        end.set(Calendar.HOUR_OF_DAY, 22);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        
        return ASCPCDAO.getInstance().findMin(start.getTime(), end.getTime(), price);
    }
    
    public static Float findOffPeakMin(final Date date, final String price) throws ASCPCException {
        if (date == null)
            throw new ASCPCException("Date cannot be empty");

        Calendar nightStart = Calendar.getInstance();
        nightStart.setTime(date);
        nightStart.set(Calendar.HOUR_OF_DAY, 23);
        nightStart.set(Calendar.MINUTE, 0);
        nightStart.set(Calendar.SECOND, 0);

        Calendar nightEnd = Calendar.getInstance();
        nightEnd.setTime(date);
        nightEnd.set(Calendar.HOUR_OF_DAY, 23);
        nightEnd.set(Calendar.MINUTE, 59);
        nightEnd.set(Calendar.SECOND, 59);

        Calendar morningStart = Calendar.getInstance();
        morningStart.setTime(date);
        morningStart.set(Calendar.HOUR_OF_DAY, 0);
        morningStart.set(Calendar.MINUTE, 0);
        morningStart.set(Calendar.SECOND, 0);

        Calendar morningEnd = Calendar.getInstance();
        morningEnd.setTime(date);
        morningEnd.set(Calendar.HOUR_OF_DAY, 6);
        morningEnd.set(Calendar.MINUTE, 59);
        morningEnd.set(Calendar.SECOND, 59);
        
        return ASCPCDAO.getInstance().findOffPeakMin(nightStart.getTime(), 
        		                                     nightEnd.getTime(), 
        		                                     morningStart.getTime(), 
        		                                     morningEnd.getTime(), 
        		                                     price);
    }
    
    public static Float findDailyMax(final Date date, final String price) throws ASCPCException {
        if (date == null)
            throw new ASCPCException("Date cannot be empty");

        Calendar start = Calendar.getInstance();
        start.setTime(date);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);

        Calendar end = Calendar.getInstance();
        end.setTime(date);
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        
        return ASCPCDAO.getInstance().findMax(start.getTime(), end.getTime(), price);
    }
    
    public static Float findPeakMax(final Date date, final String price) throws ASCPCException {
        if (date == null)
            throw new ASCPCException("Date cannot be empty");

        Calendar start = Calendar.getInstance();
        start.setTime(date);
        start.set(Calendar.HOUR_OF_DAY, 7);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);

        Calendar end = Calendar.getInstance();
        end.setTime(date);
        end.set(Calendar.HOUR_OF_DAY, 22);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        
        return ASCPCDAO.getInstance().findMax(start.getTime(), end.getTime(), price);
    }
    
    public static Float findOffPeakMax(final Date date, final String price) throws ASCPCException {
        if (date == null)
            throw new ASCPCException("Date cannot be empty");

        Calendar nightStart = Calendar.getInstance();
        nightStart.setTime(date);
        nightStart.set(Calendar.HOUR_OF_DAY, 23);
        nightStart.set(Calendar.MINUTE, 0);
        nightStart.set(Calendar.SECOND, 0);

        Calendar nightEnd = Calendar.getInstance();
        nightEnd.setTime(date);
        nightEnd.set(Calendar.HOUR_OF_DAY, 23);
        nightEnd.set(Calendar.MINUTE, 59);
        nightEnd.set(Calendar.SECOND, 59);

        Calendar morningStart = Calendar.getInstance();
        morningStart.setTime(date);
        morningStart.set(Calendar.HOUR_OF_DAY, 0);
        morningStart.set(Calendar.MINUTE, 0);
        morningStart.set(Calendar.SECOND, 0);

        Calendar morningEnd = Calendar.getInstance();
        morningEnd.setTime(date);
        morningEnd.set(Calendar.HOUR_OF_DAY, 6);
        morningEnd.set(Calendar.MINUTE, 59);
        morningEnd.set(Calendar.SECOND, 59);
        
        return ASCPCDAO.getInstance().findOffPeakMax(nightStart.getTime(), 
        		                                     nightEnd.getTime(), 
        		                                     morningStart.getTime(), 
        		                                     morningEnd.getTime(), 
        		                                     price);
    }
}