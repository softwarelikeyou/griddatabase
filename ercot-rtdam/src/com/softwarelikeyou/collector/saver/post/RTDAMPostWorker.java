package com.softwarelikeyou.collector.saver.post;

import java.util.List;

import org.apache.log4j.Logger;

import com.softwarelikeyou.collector.saver.Post;
import com.softwarelikeyou.collector.saver.Saver;
import com.softwarelikeyou.facade.RTDAMFacade;
import com.softwarelikeyou.model.entity.RTDAM;

public class RTDAMPostWorker extends Post {

	private static Logger logger = Logger.getLogger(RTDAMPostWorker.class);

	public boolean execute() {
		try {
			List<String> settlementPoints = RTDAMFacade.findSettlementPoints();
			
			for (String settlementPoint : settlementPoints) {
		        for (RTDAM rtdam : RTDAMFacade.findZeroVelocity(settlementPoint)) {
			        RTDAM previous = RTDAMFacade.findPreviousInterval(rtdam);
			        if (previous != null) {
				        rtdam.setVelocity(Saver.formatFloat(rtdam.getSettlementPointPrice() - previous.getSettlementPointPrice()));
				        RTDAMFacade.createOrUpdate(rtdam);
			        }
			    }
		    }
			
			for (String settlementPoint : settlementPoints) {
		        for (RTDAM rtdam : RTDAMFacade.findZeroAcceleration(settlementPoint)) {
			        RTDAM previous = RTDAMFacade.findPreviousInterval(rtdam);
			        if (previous != null) {
			            rtdam.setAcceleration(Saver.formatFloat(rtdam.getVelocity() - previous.getVelocity()));
				        RTDAMFacade.createOrUpdate(rtdam);
			        }
			    }
		    }			
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return false;
		}
		return true;
	}

}
