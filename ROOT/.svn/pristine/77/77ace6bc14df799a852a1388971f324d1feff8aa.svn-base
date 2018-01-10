package com.griddatabase.settlementpoints;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import java.text.ParseException;

import com.griddatabase.scheduler.HenryHubJob;
import com.griddatabase.scheduler.SettlementPointJob;
import com.griddatabase.scheduler.SettlementPointTemperatureBackfillJob;
import com.griddatabase.scheduler.WeatherJob;
import com.griddatabase.scheduler.ZipCodeTemperatureJob;
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
        	sechduleStation(settlementPoint, scheduler);
	    }
	}
	
	public static void sechduleStation(final SettlementPoint settlementPoint, final Scheduler scheduler) throws  ParseException, SchedulerException, ClassNotFoundException {
        
		JobDetail jobDetail = new JobDetail();
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("settlementPoint", settlementPoint);
        jobDetail.setJobDataMap(jobDataMap);
		jobDetail.setName(settlementPoint.getName());
		jobDetail.setGroup("SettlmenetPoints");
		jobDetail.setJobClass(WeatherJob.class);
		CronTrigger trigger;
	    trigger = new CronTrigger();
	    trigger.setName(jobDetail.getName());
	    trigger.setJobName(jobDetail.getName());
	    trigger.setJobGroup(jobDetail.getGroup());
	    trigger.setCronExpression(sec + " " + min + " * * * ?");
        scheduler.scheduleJob(jobDetail, trigger);
        
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
		jobDetail.setGroup("SettlmenetPoints");
		jobDetail.setJobClass(SettlementPointJob.class);
		CronTrigger trigger;
		trigger = new CronTrigger();
		trigger.setName(jobDetail.getName());
		trigger.setJobName(jobDetail.getName());
		trigger.setJobGroup(jobDetail.getGroup());
		trigger.setCronExpression("0 0/30 * * * ?");
		scheduler.scheduleJob(jobDetail, trigger);
	}
	
	public static void getSettlementPointTemperatureBackfill(Scheduler scheduler) throws ParseException, SchedulerException, ClassNotFoundException {

		JobDetail jobDetail = new JobDetail();
		jobDetail.setName("settlementPointTemperatureBackfill");
		jobDetail.setGroup("SettlmenetPointTemperatureBackfill");
		jobDetail.setJobClass(SettlementPointTemperatureBackfillJob.class);
		CronTrigger trigger;
		trigger = new CronTrigger();
		trigger.setName(jobDetail.getName());
		trigger.setJobName(jobDetail.getName());
		trigger.setJobGroup(jobDetail.getGroup());
		trigger.setCronExpression("0 45 22 * * ?");
		//scheduler.scheduleJob(jobDetail, trigger);
	}
	
	public static void getHenryHub(Scheduler scheduler) throws ParseException, SchedulerException {
		JobDetail jobDetail = new JobDetail();
		jobDetail.setName("henryHub");
		jobDetail.setGroup("henryHub");
		jobDetail.setJobClass(HenryHubJob.class);
		CronTrigger trigger;
		trigger = new CronTrigger();
		trigger.setName(jobDetail.getName());
		trigger.setJobName(jobDetail.getName());
		trigger.setJobGroup(jobDetail.getGroup());
		trigger.setCronExpression("0 0 20 * * ?");
		scheduler.scheduleJob(jobDetail, trigger);
	}
	
	public static void getZipCodes(Scheduler scheduler) throws ParseException, SchedulerException, ClassNotFoundException, ZipCodeException {

		sec = 15;
        for (ZipCode zipCode : ZipCodeFacade.findAll())
        	sechduleZipCode(zipCode, scheduler);
	}
	
	public static void sechduleZipCode(final ZipCode zipCode, final Scheduler scheduler) throws  ParseException, SchedulerException, ClassNotFoundException {
        
		JobDetail jobDetail = new JobDetail();
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("zipCode", zipCode);
        jobDetail.setJobDataMap(jobDataMap);
		jobDetail.setName(zipCode.getName());
		jobDetail.setGroup("SettlmenetPoints");
		jobDetail.setJobClass(ZipCodeTemperatureJob.class);
		CronTrigger trigger;
	    trigger = new CronTrigger();
	    trigger.setName(jobDetail.getName());
	    trigger.setJobName(jobDetail.getName());
	    trigger.setJobGroup(jobDetail.getGroup());
	    trigger.setCronExpression(sec + " " + min + " * * * ?");
        scheduler.scheduleJob(jobDetail, trigger);
        
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