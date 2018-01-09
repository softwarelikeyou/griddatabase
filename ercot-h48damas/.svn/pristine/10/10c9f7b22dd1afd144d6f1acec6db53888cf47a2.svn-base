package com.softwarelikeyou.scheduler;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import java.text.ParseException;

import com.softwarelikeyou.util.ResourceUtil;

public class Jobs {	
	
	private static Logger logger = Logger.getLogger(Jobs.class);
	
	public static void get(Scheduler scheduler) throws SchedulerException {
		   
		String[] list = ResourceUtil.get().getString("scheduler.list").split(",");
		
		if (list.length == 0)
			return;
		
		for (String job : list) {
			if (job.trim().length() == 0)
				continue;
			JobDetail jobDetail = new JobDetail();
			jobDetail.setName(job);
			jobDetail.setGroup("ercot");
			try {
			    jobDetail.setJobClass(Class.forName(String.valueOf(ResourceUtil.get().getString(job.trim() + ".worker"))));
			}
			catch (ClassNotFoundException e) {
				logger.error("Worker cannot be found");
				continue;
			}
			CronTrigger trigger;
		    trigger = new CronTrigger();
		    trigger.setName(jobDetail.getName());
		    trigger.setJobName(jobDetail.getName());
		    trigger.setJobGroup(jobDetail.getGroup());
		    try {
		        trigger.setCronExpression(ResourceUtil.get().getString(job.trim() + ".cron"));
		    }
		    catch (ParseException e) {
		    	logger.error("Problem with cron entry for job " + job);
		    	continue;
		    }
		    scheduler.scheduleJob(jobDetail, trigger);
		}
	}
}