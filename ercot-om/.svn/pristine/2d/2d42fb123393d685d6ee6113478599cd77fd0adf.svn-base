package com.softwarelikeyou.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.softwarelikeyou.exception.RTLMPException;
import com.softwarelikeyou.model.dao.RTLMPDAO;
import com.softwarelikeyou.model.entity.rtlmp.RTLMP;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU1H;
import com.softwarelikeyou.model.entity.rtlmp.lz.RTLMPLZ1H;
import com.softwarelikeyou.model.entity.rtlmp.lzdc.RTLMPLZDC1H;
import com.softwarelikeyou.model.entity.rtlmp.pccrn.RTLMPPCCRN1H;
import com.softwarelikeyou.model.entity.rtlmp.lccrn.RTLMPLCCRN1H;
import com.softwarelikeyou.model.entity.rtlmp.pun.RTLMPPUN1H;
import com.softwarelikeyou.model.entity.rtlmp.rn.RTLMPRN1H;

public class RTLMPFacade {

	private RTLMPFacade() { }
	
	public static List<String> findUniqueSettlementPoints(final String type) throws RTLMPException {
		if (type == null)
			throw new RTLMPException("Type cannot be empty");
		RTLMPDAO dao = null;
		switch (type) {
		    case "HU":
				dao = new RTLMPDAO(RTLMPHU1H.class);
		        break;
		    case "LZ":
				dao = new RTLMPDAO(RTLMPLZ1H.class);
		    	break;
		    case "LZDC":
				dao = new RTLMPDAO(RTLMPLZDC1H.class);
		    	break;
		    case "PCCRN":
				dao = new RTLMPDAO(RTLMPPCCRN1H.class);
		    	break;
		    case "LCCRN":
				dao = new RTLMPDAO(RTLMPLCCRN1H.class);
		    	break;
		    case "PUN":
				dao = new RTLMPDAO(RTLMPPUN1H.class);
		    	break;
		    case "RN":
				dao = new RTLMPDAO(RTLMPRN1H.class);
		    	break;
		    	
		}
		return dao.findUniqueSettlementPoints();		
	}
	
	/**
	 * @deprecated
	 **/
	public static RTLMP findPreviousInterval(RTLMP entity) throws RTLMPException {
	    Calendar previousInterval = Calendar.getInstance();
	    previousInterval.setTime(entity.getIntervalEnding());
	    	    	    
	    if (entity.getClass().getCanonicalName().endsWith("15M")) 
		    previousInterval.add(Calendar.MINUTE, -15);
	    else if (entity.getClass().getCanonicalName().endsWith("30M")) 
		    previousInterval.add(Calendar.MINUTE, -30);
	    else if (entity.getClass().getCanonicalName().endsWith("1H")) 
		    previousInterval.add(Calendar.HOUR, -1);
	    else if (entity.getClass().getCanonicalName().endsWith("24H")) 
		    previousInterval.add(Calendar.DAY_OF_MONTH, -1);
	    else
		    previousInterval.add(Calendar.MINUTE, -5);
	    
	    RTLMPDAO dao = new RTLMPDAO(entity.getClass());
	    return dao.findPreviousInterval(previousInterval.getTime(), entity.getIntervalId(), entity.getSettlementPoint());
	}
	
	public static List<RTLMP> findByBetweenIntervalEndingDatesSettlementPoint(Class<? extends RTLMP> entity, final Date start, final Date end, final String settlementPoint) throws RTLMPException {
		if (entity == null || start == null || end == null || settlementPoint == null)
			throw new RTLMPException("Type, start date, end date and/or settlementPoint cannot be empty");
		RTLMPDAO dao = new RTLMPDAO(entity);
	    return dao.findByBetweenIntervalEndingDatesSettlementPoint(start, end, settlementPoint);
	}

	public static List<RTLMP> findByBetweenRTDTimestampDatesSettlementPoint(Class<? extends RTLMP> entity, final Date start, final Date end, final String settlementPoint) throws RTLMPException {
		if (entity == null || start == null || end == null || settlementPoint == null)
			throw new RTLMPException("Type, start date, end date and/or settlementPoint cannot be empty");
		RTLMPDAO dao = new RTLMPDAO(entity);
	    return dao.findByBetweenRTDTimestampDatesSettlementPoint(start, end, settlementPoint);
	}
	
	public static RTLMP findById(Class<? extends RTLMP> entity, Date timestamp, Date intervalEnding, String settlementPoint) throws RTLMPException {
		if (entity == null || timestamp == null || intervalEnding == null || settlementPoint == null)
			throw new RTLMPException("Entity, timestamp, intervalEnding, or settlementPoint cannot be empty");
		
		RTLMPDAO dao = new RTLMPDAO(entity);
		return dao.findById(timestamp, intervalEnding, settlementPoint);
	}

	public static List<RTLMP> findByRTDTimestamp(Class<? extends RTLMP> entity, Date timestamp) throws RTLMPException {
		if (timestamp == null)
			throw new RTLMPException("Timestame cannot be empty");
		RTLMPDAO dao = new RTLMPDAO(entity);
		return dao.findByRTDTimestamp(timestamp);
	}
	
	public static void createOrUpdate(RTLMP entity) throws RTLMPException {
		if (entity == null)
			throw new RTLMPException("Entity cannot be empty");
		RTLMPDAO dao = new RTLMPDAO(entity.getClass());
		dao.createOrUpdate(entity);
	}
}
