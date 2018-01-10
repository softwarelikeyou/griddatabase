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
import com.griddatabase.settlementpoints.WeatherBug;
import com.softwarelikeyou.exception.WeatherException;
import com.softwarelikeyou.facade.SettlementPointFacade;
import com.softwarelikeyou.facade.SettlementPointTemperatureFacade;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.softwarelikeyou.model.entity.SettlementPointTemperature;

public class WeatherJob implements Job {
	private static Logger logger = Logger.getLogger(WeatherJob.class);
	
	public WeatherJob() { }
	
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
			
			SettlementPoint settlementPoint = SettlementPointFacade.findById(((SettlementPoint) context.getJobDetail().getJobDataMap().get("settlementPoint")).getName());
			
			if (settlementPoint == null) {
				logger.error("SettlementPoint is null");
				return;
			}
			
			NDFD ndfd = new NDFD();
			ndfd.setSeed(new Date());
			ndfd.setLatitude(settlementPoint.getLatitude());
			ndfd.setLongitude(settlementPoint.getLongitude());
			String temp = "";
			try {
				temp = ndfd.getTemperature(Type.LATLONG);
			}
			catch (WeatherException e) {
			    logger.error("Unable to retrieve temperature for " + settlementPoint.getName() + " - " + e.getMessage(), e);
			}
			
			if (temp.equals("")) {
				try {
					WeatherBug weatherBug = new WeatherBug();
					weatherBug.setLatitude(settlementPoint.getLatitude());
					weatherBug.setLongitude(settlementPoint.getLongitude());
					temp = weatherBug.getTemperature();
				}
				catch (WeatherException e) {
				    logger.error("Unable to retrieve temperature for " + settlementPoint.getName() + " - " + e.getMessage(), e);
				}
			}
			
			if (temp != null && !temp.equals("")) {
				logger.info("Temperature at " + settlementPoint.getName() + " is " + temp);
				settlementPoint.setTemperature(Integer.valueOf(temp));
				SettlementPointFacade.createOrUpdate(settlementPoint);
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
			    calendar.set(Calendar.MINUTE, 0);
			    calendar.set(Calendar.SECOND, 0);
			    calendar.set(Calendar.MILLISECOND, 0);
			    
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
