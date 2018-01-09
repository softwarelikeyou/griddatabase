package com.griddatabase.collector.worker;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.griddatabase.collector.weather.NDFD;
import com.griddatabase.collector.weather.NDFD.Type;
import com.griddatabase.collector.weather.WeatherBug;
import com.softwarelikeyou.exception.WeatherException;
import com.softwarelikeyou.facade.SettlementPointFacade;
import com.softwarelikeyou.model.entity.SettlementPoint;

public class WeatherWorker implements Job {
	private static Logger logger = Logger.getLogger(WeatherWorker.class);
	
	public WeatherWorker() { }
	
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
			}
		}
		catch (Exception e) {
			logger.error(e.getCause(), e);
		}
	}
}
