package com.griddatabase.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.griddatabase.settlementpoints.NDFD;
import com.griddatabase.settlementpoints.NDFD.Type;
import com.softwarelikeyou.exception.WeatherException;
import com.softwarelikeyou.facade.SettlementPointTemperatureFacade;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.softwarelikeyou.model.entity.SettlementPointTemperature;

public class SettlementPointTemperatureJob implements Job {
	private static Logger logger = Logger.getLogger(SettlementPointTemperatureJob.class);
	
	public SettlementPointTemperatureJob() { }
	
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
			
			SettlementPoint settlementPoint  = (SettlementPoint) context.getJobDetail().getJobDataMap().get("settlementPoint");

			if (settlementPoint == null) {
				logger.error("Settlement Point is null");
				return;
			}
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
		    calendar.set(Calendar.MINUTE, 0);
		    calendar.set(Calendar.SECOND, 0);
		    calendar.set(Calendar.MILLISECOND, 0);
		    
			NDFD ndfd = new NDFD();
			ndfd.setSeed(calendar.getTime());
			ndfd.setLatitude(settlementPoint.getLatitude());
			ndfd.setLongitude(settlementPoint.getLongitude());
			
			String temp = "";
			try {
				temp = ndfd.getTemperature(Type.LATLONG);
			}
			catch (WeatherException e) {
			    logger.error("Unable to retrieve temperature for " + settlementPoint.getName() + " - " + e.getMessage(), e);
			}
			
			if (temp != null && !temp.equals("")) {
				logger.info("Temperature at " + settlementPoint.getName() + " is " + temp);
				SettlementPointTemperature entity = new SettlementPointTemperature();
				entity.setDateTime(calendar.getTime());
				entity.setSettlementPoint(settlementPoint.getName());
				entity.setTemperature(Integer.valueOf(temp));
				SettlementPointTemperatureFacade.createOrUpdate(entity);
			}
		}
		catch (Exception e) {
			logger.error(e.getCause(), e);
		}
	}
}
