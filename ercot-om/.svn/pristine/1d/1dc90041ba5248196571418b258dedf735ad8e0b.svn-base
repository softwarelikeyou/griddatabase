package com.softwarelikeyou.facade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.softwarelikeyou.exception.RTSPPException;
import com.softwarelikeyou.model.dao.rtspp.RTSPPDAO;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;
import com.softwarelikeyou.model.entity.rtspp.ah.RTSPPAH15M;
import com.softwarelikeyou.model.entity.rtspp.hu.RTSPPHU15M;
import com.softwarelikeyou.model.entity.rtspp.hu.RTSPPHU1H;
import com.softwarelikeyou.model.entity.rtspp.lccrn.RTSPPLCCRN15M;
import com.softwarelikeyou.model.entity.rtspp.lccrn.RTSPPLCCRN1H;
import com.softwarelikeyou.model.entity.rtspp.lz.RTSPPLZ15M;
import com.softwarelikeyou.model.entity.rtspp.lz.RTSPPLZ1H;
import com.softwarelikeyou.model.entity.rtspp.lzdc.RTSPPLZDC15M;
import com.softwarelikeyou.model.entity.rtspp.lzdc.RTSPPLZDC1H;
import com.softwarelikeyou.model.entity.rtspp.lzdcew.RTSPPLZDCEW15M;
import com.softwarelikeyou.model.entity.rtspp.lzdcew.RTSPPLZDCEW1H;
import com.softwarelikeyou.model.entity.rtspp.lzew.RTSPPLZEW15M;
import com.softwarelikeyou.model.entity.rtspp.pccrn.RTSPPPCCRN15M;
import com.softwarelikeyou.model.entity.rtspp.pccrn.RTSPPPCCRN1H;
import com.softwarelikeyou.model.entity.rtspp.pun.RTSPPPUN15M;
import com.softwarelikeyou.model.entity.rtspp.pun.RTSPPPUN1H;
import com.softwarelikeyou.model.entity.rtspp.rn.RTSPPRN15M;
import com.softwarelikeyou.model.entity.rtspp.rn.RTSPPRN1H;
import com.softwarelikeyou.model.entity.rtspp.sh.RTSPPSH15M;

public class RTSPPFacade {
	
	public static List<RTSPP> findBetweenDates(final Date start, final Date end) throws RTSPPException {
		if (start == null || end == null)
			throw new RTSPPException("Entity, start date, end date cannot be empty");
		List<RTSPP> list = new ArrayList<RTSPP>();
		list.addAll(findByIntervalDates(RTSPPAH15M.class, start, end));
		list.addAll(findByIntervalDates(RTSPPHU15M.class, start, end));
		list.addAll(findByIntervalDates(RTSPPLCCRN15M.class, start, end));
		list.addAll(findByIntervalDates(RTSPPLZ15M.class, start, end));
		list.addAll(findByIntervalDates(RTSPPLZDC15M.class, start, end));
		list.addAll(findByIntervalDates(RTSPPLZDCEW15M.class, start, end));
		list.addAll(findByIntervalDates(RTSPPLZEW15M.class, start, end));
		list.addAll(findByIntervalDates(RTSPPPCCRN15M.class, start, end));
		list.addAll(findByIntervalDates(RTSPPPUN15M.class, start, end));
		list.addAll(findByIntervalDates(RTSPPRN15M.class, start, end));
		list.addAll(findByIntervalDates(RTSPPSH15M.class, start, end));
		return list;
	}
	
	public static List<RTSPP> findByIntervalDate(final Class<? extends RTSPP> entity, final Date timestamp) throws RTSPPException {
		if (timestamp == null)
			throw new RTSPPException("Interval date cannot be empty");
		RTSPPDAO dao = new RTSPPDAO(entity);
		return dao.findByIntervalDate(timestamp);
	}
	
	public static RTSPP findById(final Class<? extends RTSPP> entity, final Date intervalDate, final String settlementPoint) throws RTSPPException {
		if (entity == null || intervalDate == null || settlementPoint == null)
			throw new RTSPPException("Entity, intervalDate, or settlementPoint cannot be empty");
		
		RTSPPDAO dao = new RTSPPDAO(entity);
		return dao.findById(intervalDate, settlementPoint);
	}
	
	public static List<RTSPP> findByIntervalDates(final Class<? extends RTSPP> entity, final Date start, final Date end) throws RTSPPException {
		if (entity == null || start == null || end == null)
			throw new RTSPPException("Entity, start date, end date cannot be empty");
		
		RTSPPDAO dao = new RTSPPDAO(entity);
	    return dao.findByIntervalDates(start, end);
	}

	public static List<RTSPP> findByIntervalDatesSettlementPoint(final Class<? extends RTSPP> entity, final Date start, final Date end, final String settlementPointName) throws RTSPPException {
		if (entity == null || start == null || end == null || settlementPointName == null)
			throw new RTSPPException("Entity, start date, end date, settlement point cannot be empty");
		
		RTSPPDAO dao = new RTSPPDAO(entity);
	    return dao.findByIntervalDatesSettlementPoint(start, end, settlementPointName);
	}
	
	public static void createOrUpdate(final RTSPP entity) throws RTSPPException {
		if (entity == null)
			throw new RTSPPException("Entity cannot be empty");
		RTSPPDAO dao = new RTSPPDAO(entity.getClass());
		dao.createOrUpdate(entity);
	}
	
	public static List<String> findUniqueSettlementPoints(final String type) throws RTSPPException {
		if (type == null)
			throw new RTSPPException("Type cannot be empty");
		RTSPPDAO dao = null;
		Class<? extends RTSPP> entity = null;
		switch (type) {
		    case "HU":
				entity = RTSPPHU1H.class;
		        break;
		    case "LZ":
				entity = RTSPPLZ1H.class;
		    	break;
		    case "LZDC":
				entity = RTSPPLZDC1H.class;
		    	break;
		    case "LZDCEW":
				entity = RTSPPLZDCEW1H.class;
		    	break;
		    case "PCCRN":
				entity = RTSPPPCCRN1H.class;
		    	break;
		    case "LCCRN":
				entity = RTSPPLCCRN1H.class;
		    	break;
		    case "PUN":
				entity = RTSPPPUN1H.class;
		    	break;
		    case "RN":
				entity = RTSPPRN1H.class;
		      	break;
		}
		dao = new RTSPPDAO(entity);
		return dao.findUniqueSettlementPoints();
	}
	
	public static List<String> findUniqueSettlementPoints(final Class<? extends RTSPP> entity) throws RTSPPException {
		if (entity == null)
			throw new RTSPPException("Entity cannot be empty");
		RTSPPDAO dao = new RTSPPDAO(entity);
		return dao.findUniqueSettlementPoints();
	}
	
    public static List<RTSPP> findList(final Class<? extends RTSPP> entity, final Date date, final String settlementPoint) throws RTSPPException {
        if (entity == null || date == null || settlementPoint == null)
                throw new RTSPPException("Entity, date, type, or name cannot be empty");

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
        
		RTSPPDAO dao = new RTSPPDAO(entity);
		return dao.findList(start.getTime(), end.getTime(), settlementPoint);
    }
    
    public static Float findDailyAverage(final Class<? extends RTSPP> entity, final Date date, final String settlementPoint) throws RTSPPException {
        if (entity == null || date == null || settlementPoint == null)
                throw new RTSPPException("Entity, date, type, or name cannot be empty");

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
        
		RTSPPDAO dao = new RTSPPDAO(entity);
		return dao.findAverage(start.getTime(), end.getTime(), settlementPoint);
    }
    
    public static Float findDailyMin(final Class<? extends RTSPP> entity, final Date date, final String settlementPoint) throws RTSPPException {
        if (entity == null || date == null || settlementPoint == null)
                throw new RTSPPException("Entity, date, type, or name cannot be empty");

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
        
		RTSPPDAO dao = new RTSPPDAO(entity);
		return dao.findMin(start.getTime(), end.getTime(), settlementPoint);
    }
    
    public static Float findDailyMax(final Class<? extends RTSPP> entity, final Date date, final String settlementPoint) throws RTSPPException {
        if (entity == null || date == null || settlementPoint == null)
                throw new RTSPPException("Entity, date, type, or name cannot be empty");

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
        
		RTSPPDAO dao = new RTSPPDAO(entity);
		return dao.findMax(start.getTime(), end.getTime(), settlementPoint);
    }
    
    public static Float findPeakAverage(final Class<? extends RTSPP> entity, final Date date, final String settlementPoint) throws RTSPPException {
        if (entity == null || date == null || settlementPoint == null)
                throw new RTSPPException("Entity, date, type, or name cannot be empty");

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
        
		RTSPPDAO dao = new RTSPPDAO(entity);
		return dao.findAverage(start.getTime(), end.getTime(), settlementPoint);
    }
    
    public static Float findPeakMin(final Class<? extends RTSPP> entity, final Date date, final String settlementPoint) throws RTSPPException {
        if (entity == null || date == null || settlementPoint == null)
                throw new RTSPPException("Entity, date, type, or name cannot be empty");

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
        
		RTSPPDAO dao = new RTSPPDAO(entity);
		return dao.findMin(start.getTime(), end.getTime(), settlementPoint);
    }
    
    public static Float findPeakMax(final Class<? extends RTSPP> entity, final Date date, final String settlementPoint) throws RTSPPException {
        if (entity == null || date == null || settlementPoint == null)
                throw new RTSPPException("Entity, date, type, or name cannot be empty");

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
        
		RTSPPDAO dao = new RTSPPDAO(entity);
		return dao.findMax(start.getTime(), end.getTime(), settlementPoint);
    }
    
    public static Float findOffPeakAverage(final Class<? extends RTSPP> entity, final Date date, final String settlementPoint) throws RTSPPException {
        if (entity == null || date == null || settlementPoint == null)
                throw new RTSPPException("Entity, date, type, or name cannot be empty");

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
        
    	RTSPPDAO dao = new RTSPPDAO(entity);
    	return dao.findOffPeakAverage(nightStart.getTime(), 
    			                      nightEnd.getTime(), 
    			                      morningStart.getTime(), 
    			                      morningEnd.getTime(), 
    			                      settlementPoint);
    }
    
    public static List<RTSPP> findOffPeakList(final Class<? extends RTSPP> entity, final Date date, final String settlementPoint) throws RTSPPException {
        if (entity == null || date == null || settlementPoint == null)
                throw new RTSPPException("Entity, date, type, or name cannot be empty");

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
        
    	RTSPPDAO dao = new RTSPPDAO(entity);
    	return dao.findOffPeakList(nightStart.getTime(), 
    			                   nightEnd.getTime(), 
    			                   morningStart.getTime(), 
    			                   morningEnd.getTime(), 
    			                   settlementPoint);
    }
    
    public static Float findOffPeakMin(final Class<? extends RTSPP> entity, final Date date, final String settlementPoint) throws RTSPPException {
        if (entity == null || date == null || settlementPoint == null)
                throw new RTSPPException("Entity, date, type, or name cannot be empty");

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
        
    	RTSPPDAO dao = new RTSPPDAO(entity);
    	return dao.findOffPeakMin(nightStart.getTime(), 
    			                  nightEnd.getTime(), 
    			                  morningStart.getTime(), 
    			                  morningEnd.getTime(), 
    			                  settlementPoint);
    }
    
    public static Float findOffPeakMax(final Class<? extends RTSPP> entity, final Date date, final String settlementPoint) throws RTSPPException {
        if (entity == null || date == null || settlementPoint == null)
                throw new RTSPPException("Entity, date, type, or name cannot be empty");

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
        
    	RTSPPDAO dao = new RTSPPDAO(entity);
    	return dao.findOffPeakMax(nightStart.getTime(), 
    			                  nightEnd.getTime(), 
    			                  morningStart.getTime(), 
    			                  morningEnd.getTime(), 
    			                  settlementPoint);
    }
    
    public static Map<Date, Double> findPriceChangeBetweenSettlementPoints(final Class<? extends RTSPP> entity, final Date start, final Date end, final String settlementPointA, final String settlementPointB) throws RTSPPException {
        if (entity == null || start == null || end == null || settlementPointA == null || settlementPointB == null)
            throw new RTSPPException("Entity, dates, or names cannot be empty");
        RTSPPDAO dao = new RTSPPDAO(entity);
        return dao.findPriceChangeBetweenSettlementPoints(start, end, settlementPointA, settlementPointB);
    }
}
