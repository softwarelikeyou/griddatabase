package com.griddatabase.scheduler;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.griddatabase.model.cache.RTLMPCache;
import com.griddatabase.model.cache.RTSPPCache;

public class ResetLMPSPPCache implements Job {

	private static Logger logger = Logger.getLogger(ResetLMPSPPCache.class);
	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			for (JobExecutionContext job : (List<JobExecutionContext>) context.getScheduler().getCurrentlyExecutingJobs()) {
				if (job.getJobDetail().getName().equals(context.getJobDetail().getName()) && !job.getJobInstance().equals(this)) {
					logger.info("Job " + context.getJobDetail().getName() + " is running");
					return;
				}
			}
			
			RTLMPCache.clearMap();
			RTSPPCache.clearMap();
			logger.info("Clearing RTLMP and RTSPP Caches");
		}
		catch (Exception e) {
			logger.error("RTLMP Cache Reset", e);
		}
	}

}
