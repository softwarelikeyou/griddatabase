package com.softwarelikeyou.ercot.analyzer.ascpc;

import java.util.Date;

import org.apache.log4j.Logger;

import com.softwarelikeyou.analyzer.WorkerHelper;
import com.softwarelikeyou.facade.ASCPCFacade;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;

public class PostWorker {

	private static Logger logger = Logger.getLogger(PostWorker.class);
	
	private Date intervalDate;
	
	public void setIntevalDate(final Date value) {
		intervalDate = value;
	}
	
	public Date getIntervalDate() {
		return intervalDate;
	}
	
	public boolean execute() {
		if (intervalDate == null)
			return false;
		
		boolean results = true;
		try {
	        for (ASCPC ascpc : ASCPCFacade.findBetweenDates(intervalDate, intervalDate)) {
	        	
		        ASCPC previous = ASCPCFacade.findPreviousInterval(ascpc.getIntervalDate());
		        if (previous == null)
		        	continue;
		       
			    ascpc.setNSPINVelocity(WorkerHelper.formatFloat(ascpc.getNSPIN() - previous.getNSPIN()));
			    ascpc.setREGDNVelocity(WorkerHelper.formatFloat(ascpc.getREGDN() - previous.getREGDN()));
			    ascpc.setREGUPVelocity(WorkerHelper.formatFloat(ascpc.getREGUP() - previous.getREGUP()));
			    ascpc.setRRSVelocity(WorkerHelper.formatFloat(ascpc.getRRS() - previous.getRRS()));
			    ascpc.setNSPINAcceleration(WorkerHelper.formatFloat(ascpc.getNSPINVelocity() - previous.getNSPINVelocity()));
			    ascpc.setREGDNAcceleration(WorkerHelper.formatFloat(ascpc.getREGDNVelocity() - previous.getREGDNVelocity()));
			    ascpc.setREGUPAcceleration(WorkerHelper.formatFloat(ascpc.getREGUPVelocity() - previous.getREGUPVelocity()));
			    ascpc.setRRSAcceleration(WorkerHelper.formatFloat(ascpc.getRRSVelocity() - previous.getRRSVelocity()));
	        	
			    ASCPCFacade.createOrUpdate(ascpc);
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return false;
		}
		return results;
	}
}
