package com.griddatabase.scheduler;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.griddatabase.settlementpoints.NDFD;
import com.griddatabase.settlementpoints.NDFD.Type;
import com.softwarelikeyou.exception.WeatherException;
import com.softwarelikeyou.facade.ZipCodeTemperatureFacade;
import com.softwarelikeyou.model.entity.ZipCode;
import com.softwarelikeyou.model.entity.ZipCodeTemperature;

public class ZipCodeTemperatureJob implements Job {
	private static Logger logger = Logger.getLogger(ZipCodeTemperatureJob.class);
	
	public ZipCodeTemperatureJob() { }
	
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
			
			ZipCode zipCode  = (ZipCode) context.getJobDetail().getJobDataMap().get("zipCode");

			if (zipCode == null) {
				logger.error("Zip code is null");
				return;
			}
			
			NDFD ndfd = new NDFD();
			ndfd.setSeed(new Date());
			ndfd.setZipCode(zipCode.getName());
			String temp = "";
			try {
				temp = ndfd.getTemperature(Type.ZIPCODE);
			}
			catch (WeatherException e) {
			    logger.error("Unable to retrieve temperature for " + zipCode.getName() + " - " + e.getMessage(), e);
			}
			
			if (temp != null && !temp.equals("")) {
				logger.info("Temperature at " + zipCode.getName() + " is " + temp);
				ZipCodeTemperature entity = new ZipCodeTemperature();
				entity.setDatetime(new Date());
				entity.setName(zipCode.getName());
				entity.setTemperature(Integer.valueOf(temp));
				ZipCodeTemperatureFacade.createOrUpdate(entity);
			}
		}
		catch (Exception e) {
			logger.error(e.getCause(), e);
		}
	}
}
