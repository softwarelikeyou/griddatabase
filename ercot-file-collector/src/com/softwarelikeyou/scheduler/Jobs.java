package com.softwarelikeyou.scheduler;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import java.text.ParseException;

import com.softwarelikeyou.util.ResourceUtil;
import com.softwarelikeyou.worker.FileWorker;

public class Jobs {	
	
	public static void get(Scheduler scheduler) throws ParseException, SchedulerException, ClassNotFoundException {
				   
		String[] list = ResourceUtil.get().getString("scheduler.list").split(",");
		
		if (list.length == 0)
			return;
		
		for (String job : list) {
			if (job.length() == 0)
				continue;
			JobDetail jobDetail = new JobDetail();
			jobDetail.setName(job);
			jobDetail.setGroup("ercot");
			jobDetail.setJobClass(FileWorker.class);
			CronTrigger trigger;
		    trigger = new CronTrigger();
		    trigger.setName(jobDetail.getName());
		    trigger.setJobName(jobDetail.getName());
		    trigger.setJobGroup(jobDetail.getGroup());
		    trigger.setCronExpression(ResourceUtil.get().getString(job + ".cron"));
	        scheduler.scheduleJob(jobDetail, trigger);
		}
	}
}