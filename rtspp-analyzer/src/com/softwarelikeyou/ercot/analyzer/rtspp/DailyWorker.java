package com.softwarelikeyou.ercot.analyzer.rtspp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.softwarelikeyou.analyzer.WorkerHelper;
import com.softwarelikeyou.facade.RTSPPDailyFacade;
import com.softwarelikeyou.facade.RTSPPFacade;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;
import com.softwarelikeyou.model.entity.rtspp.RTSPPDaily;
import com.softwarelikeyou.model.entity.rtspp.hu.RTSPPHU15M;
import com.softwarelikeyou.model.entity.rtspp.lccrn.RTSPPLCCRN15M;
import com.softwarelikeyou.model.entity.rtspp.lz.RTSPPLZ15M;
import com.softwarelikeyou.model.entity.rtspp.lzdc.RTSPPLZDC15M;
import com.softwarelikeyou.model.entity.rtspp.lzdcew.RTSPPLZDCEW15M;
import com.softwarelikeyou.model.entity.rtspp.lzew.RTSPPLZEW15M;
import com.softwarelikeyou.model.entity.rtspp.pccrn.RTSPPPCCRN15M;
import com.softwarelikeyou.model.entity.rtspp.pun.RTSPPPUN15M;
import com.softwarelikeyou.model.entity.rtspp.rn.RTSPPRN15M;
import com.softwarelikeyou.util.Util;

public class DailyWorker {
	private static Logger logger = Logger.getLogger(DailyWorker.class);
	
	private static List<Class<? extends RTSPP>> list = new ArrayList<Class<? extends RTSPP>>();

	static {
        list.add(RTSPPHU15M.class);
        list.add(RTSPPLCCRN15M.class);
        list.add(RTSPPLZ15M.class);
        list.add(RTSPPLZDC15M.class);
        list.add(RTSPPLZDCEW15M.class);
        list.add(RTSPPLZEW15M.class);
        list.add(RTSPPPCCRN15M.class);
        list.add(RTSPPPUN15M.class);
        list.add(RTSPPRN15M.class);
	}
	
	private Date intervalDate;
	
	public void setIntervalDate(Date value) {
		intervalDate = value;
	}
	
	public Date getIntervalDate() {
		return intervalDate;
	}
	
	public boolean execute() {
		if (intervalDate == null)
			return false;
		
        if (!Worker.initialize()) {
	        logger.error("Could not initialize hibernate");
	        return false;
	    }
        
		try {
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(intervalDate);
    		calendar.set(Calendar.HOUR_OF_DAY, 0);
    		calendar.set(Calendar.MINUTE, 0);
    		calendar.set(Calendar.SECOND, 0);
    		
    		Date current = calendar.getTime();
    		calendar.add(Calendar.DATE, -1);
    		Date previous = calendar.getTime();
    		
			for (Class<? extends RTSPP> clazz : list) {
    		    for (String name : RTSPPFacade.findUniqueSettlementPoints(clazz)) {
    			    RTSPPDaily entity = new RTSPPDaily();
    			    entity.setIntervalDate(current);
    			    entity.setName(name);
    			    
    			    entity.setType(RTSPPDaily.Type.All);
    			    entity.setAverage(RTSPPFacade.findDailyAverage(clazz, entity.getIntervalDate(), name));
    			    entity.setLow(RTSPPFacade.findDailyMin(clazz, entity.getIntervalDate(), name));
    			    entity.setHigh(RTSPPFacade.findDailyMax(clazz, entity.getIntervalDate(), name));
    			    entity.setAverageChange(entity.getAverage() - RTSPPFacade.findDailyAverage(clazz, previous, name));
    			    RTSPPDailyFacade.createOrUpdate(entity);
    			    
    			    entity.setType(RTSPPDaily.Type.Peak);
    			    entity.setAverage(RTSPPFacade.findPeakAverage(clazz, entity.getIntervalDate(), name));
    			    entity.setLow(RTSPPFacade.findPeakMin(clazz, entity.getIntervalDate(), name));
    			    entity.setHigh(RTSPPFacade.findPeakMax(clazz, entity.getIntervalDate(), name));
    			    entity.setAverageChange(entity.getAverage() - RTSPPFacade.findPeakAverage(clazz, previous, name));
    			    RTSPPDailyFacade.createOrUpdate(entity);
    			    
    			    entity.setType(RTSPPDaily.Type.OffPeak);
    			    entity.setAverage(RTSPPFacade.findOffPeakAverage(clazz, entity.getIntervalDate(), name));
    			    entity.setLow(RTSPPFacade.findOffPeakMin(clazz, entity.getIntervalDate(), name));
    			    entity.setHigh(RTSPPFacade.findOffPeakMax(clazz, entity.getIntervalDate(), name));
    			    entity.setAverageChange(entity.getAverage() - RTSPPFacade.findOffPeakAverage(clazz, previous, name));
    			    RTSPPDailyFacade.createOrUpdate(entity);
				}
			}
		}
		catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return true;
	}
	
	public static void main(String[] args) {
        if (args.length != 1) {
	        System.out.println("Usage: java Worker date (format: yyyymmdd)");
	        System.exit(1);
	    }
		 
		String date = args[0];
		if (!WorkerHelper.validateDate(date)) {
			System.out.println("Date " + date + " is of incorrect format, must be yyyymmdd");
			System.exit(1);
		}
        	
		try {
		    DailyWorker daily = new DailyWorker();
		    daily.setIntervalDate(Util.stringToDate(date, "yyyyMMdd"));
		    daily.execute();
		}
		catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}
