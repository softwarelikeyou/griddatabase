package com.griddatabase.scheduler;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import java.text.ParseException;

import com.griddatabase.Initialization;
import com.softwarelikeyou.exception.SettlementPointException;
import com.softwarelikeyou.exception.ZipCodeException;
import com.softwarelikeyou.facade.SettlementPointFacade;
import com.softwarelikeyou.facade.ZipCodeFacade;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.softwarelikeyou.model.entity.ZipCode;

public class Jobs {	
		
	private static int sec = 0;
	private static int min = 0;
	
	public static void getStations(Scheduler scheduler) throws ParseException, SchedulerException, ClassNotFoundException, SettlementPointException {

		sec = 0;
        for (SettlementPoint settlementPoint : SettlementPointFacade.findAll()) {
        	sechduleStation(settlementPoint);
	    }
	}
	
	public static void sechduleStation(final SettlementPoint settlementPoint) throws  ParseException, SchedulerException, ClassNotFoundException {
        
		JobDetail jobDetail = new JobDetail();
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("settlementPoint", settlementPoint);
        jobDetail.setJobDataMap(jobDataMap);
		jobDetail.setName(settlementPoint.getName());
		jobDetail.setGroup("weather");
		jobDetail.setJobClass(com.griddatabase.collector.worker.WeatherWorker.class);
		CronTrigger trigger;
	    trigger = new CronTrigger();
	    trigger.setName(jobDetail.getName());
	    trigger.setJobName(jobDetail.getName());
	    trigger.setJobGroup(jobDetail.getGroup());
	    trigger.setCronExpression(sec + " " + min + " * * * ?");
        Initialization.weather.scheduleJob(jobDetail, trigger);
        
        min++;
        
        if (min == 59) {
        	min = 0;
        	if (sec == 0)
        	    sec = 30;
        	else
        		sec = 0;
        }
	}
	
	public static void getSettlementPoints(Scheduler scheduler) throws ParseException, SchedulerException, ClassNotFoundException {

		JobDetail jobDetail = new JobDetail();
		jobDetail.setName("settlementPoints");
		jobDetail.setGroup("weather");
		jobDetail.setJobClass(com.griddatabase.collector.worker.SettlementPointWorker.class);
		CronTrigger trigger;
		trigger = new CronTrigger();
		trigger.setName(jobDetail.getName());
		trigger.setJobName(jobDetail.getName());
		trigger.setJobGroup(jobDetail.getGroup());
		trigger.setCronExpression("0 0/30 * * * ?");
		scheduler.scheduleJob(jobDetail, trigger);
	}
	
	public static void getZipCodes(Scheduler scheduler) throws ParseException, SchedulerException, ClassNotFoundException, ZipCodeException {

		sec = 15;
        for (ZipCode zipCode : ZipCodeFacade.findAll())
        	sechduleZipCode(zipCode);
	}
	
	public static void sechduleZipCode(final ZipCode zipCode) throws  ParseException, SchedulerException, ClassNotFoundException {
        
		JobDetail jobDetail = new JobDetail();
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("zipCode", zipCode);
        jobDetail.setJobDataMap(jobDataMap);
		jobDetail.setName(zipCode.getName());
		jobDetail.setGroup("weather");
		jobDetail.setJobClass(com.griddatabase.collector.worker.ZipCodeTemperatureWorker.class);
		CronTrigger trigger;
	    trigger = new CronTrigger();
	    trigger.setName(jobDetail.getName());
	    trigger.setJobName(jobDetail.getName());
	    trigger.setJobGroup(jobDetail.getGroup());
	    trigger.setCronExpression(sec + " " + min + " * * * ?");
        Initialization.weather.scheduleJob(jobDetail, trigger);
        
        min++;
        
        if (min == 59) {
        	min = 0;
        	if (sec == 15)
        	    sec = 45;
        	else
        		sec = 15;
        }
	}
}