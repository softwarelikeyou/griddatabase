package com.softwarelikeyou.ercot.analyzer.ascpc;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.softwarelikeyou.analyzer.WorkerHelper;
import com.softwarelikeyou.exception.ASCPCException;
import com.softwarelikeyou.exception.DailyException;
import com.softwarelikeyou.facade.ASCPCDailyFacade;
import com.softwarelikeyou.facade.ASCPCFacade;
import com.softwarelikeyou.model.entity.Daily;
import com.softwarelikeyou.model.entity.ascpc.ASCPCDaily;

public class DailyWorker {
	private static Logger logger = Logger.getLogger(DailyWorker.class);

	private Date intervalDate;
	private Date previousDate;
	
	private List<String> prices = Arrays.asList("NSPIN","REGDN","REGUP", "RRS");
	
	public void setIntervalDate(final Date value) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(value);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		intervalDate = calendar.getTime();
		calendar.add(Calendar.DATE, -1);
		previousDate = calendar.getTime();
	}
	
	public void execute() throws ASCPCException, DailyException {
	    if (intervalDate == null)
	    	return;
		
	    for (String price : prices) {
		    for (Daily.Type type : Daily.Type.values()) {
		        ASCPCDaily entity = new ASCPCDaily();
			    entity.setIntervalDate(intervalDate);
			    entity.setType(type);
			    entity.setName(price);
		    	switch (type) {
		    	    case All:
		    	    	entity.setAverage(ASCPCFacade.findDailyAverage(intervalDate, price));
		    	    	entity.setAverageChange(WorkerHelper.formatFloat(entity.getAverage() - ASCPCFacade.findDailyAverage(previousDate, price)));
		    	    	entity.setHigh(ASCPCFacade.findDailyMax(intervalDate, price));
		    	    	entity.setLow(ASCPCFacade.findDailyMin(intervalDate, price));
		    			ASCPCDailyFacade.createOrUpdate(entity);
		    	    	break;
		    	    case Peak:
		    	    	entity.setAverage(ASCPCFacade.findPeakAverage(intervalDate, price));
		    	    	entity.setAverageChange(WorkerHelper.formatFloat(entity.getAverage() - ASCPCFacade.findPeakAverage(previousDate, price)));
		    	    	entity.setHigh(ASCPCFacade.findPeakMax(intervalDate, price));
		    	    	entity.setLow(ASCPCFacade.findPeakMin(intervalDate, price));
		    			ASCPCDailyFacade.createOrUpdate(entity);
		    	    	break;
		    	    case OffPeak:
		    	    	entity.setAverage(ASCPCFacade.findOffPeakAverage(intervalDate, price));
		    	    	entity.setAverageChange(WorkerHelper.formatFloat(entity.getAverage() - ASCPCFacade.findOffPeakAverage(previousDate, price)));
		    	    	entity.setHigh(ASCPCFacade.findOffPeakMax(intervalDate, price));
		    	    	entity.setLow(ASCPCFacade.findOffPeakMin(intervalDate, price));
		    			ASCPCDailyFacade.createOrUpdate(entity);
		    	    	break;
		    		default:
		    			break;
		    	}
		    }
	    }
	}
	
	public static void main(String[] args) {
		 
        if (!Worker.initialize()) {
	        logger.error("Could not initialize hibernate");
	        System.exit(1);
	    }
        		
    	DailyWorker daily = new DailyWorker();

    	try {
    		List<Date> list = ASCPCFacade.findAllDistinctShortDate();
    		for (Date date : list) {
    	        daily.setIntervalDate(date);
    	        daily.execute();
    		}
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
	}
}
