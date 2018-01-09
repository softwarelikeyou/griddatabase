package com.softwarelikeyou;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.softwarelikeyou.ercot.analyzer.rtlmp.Worker;

public class Initialization implements ServletContextListener {
	private static Logger logger = Logger.getLogger(Initialization.class);
	
	private static SchedulerFactory RTLMPSchedulerFactory;
	public static Scheduler RTLMPScheduler;

	public void contextDestroyed(ServletContextEvent sce) {
	    try {	
	        if (RTLMPSchedulerFactory.getScheduler().isStarted())
	        	RTLMPSchedulerFactory.getScheduler().shutdown();
		} 
	    catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {             		
            logger.info("Initializing Weather Service");
            Properties schedulerProperties = new Properties();
            schedulerProperties.setProperty(StdSchedulerFactory.PROP_SCHED_INSTANCE_NAME, "RTMPScheduer");
            schedulerProperties.setProperty(StdSchedulerFactory.DEFAULT_INSTANCE_ID, "5");
            schedulerProperties.setProperty(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, "org.quartz.simpl.SimpleThreadPool");
            schedulerProperties.setProperty("org.quartz.threadPool.threadCount", "1");
            schedulerProperties.setProperty(StdSchedulerFactory.PROP_JOB_STORE_CLASS, "org.quartz.simpl.RAMJobStore");
            
            RTLMPSchedulerFactory = new StdSchedulerFactory(schedulerProperties);
            RTLMPScheduler = RTLMPSchedulerFactory.getScheduler();
            RTLMPScheduler.start();

			JobDetail jobDetail = new JobDetail();
			jobDetail.setName("RTLMP");
			jobDetail.setGroup("ERCOT");
			jobDetail.setJobClass(Worker.class);
			CronTrigger trigger;
		    trigger = new CronTrigger();
		    trigger.setName(jobDetail.getName());
		    trigger.setJobName(jobDetail.getName());
		    trigger.setJobGroup(jobDetail.getGroup());
		    trigger.setCronExpression("0 /5 * ? * *");
		    RTLMPScheduler.scheduleJob(jobDetail, trigger);
		
		    if (RTLMPScheduler.isStarted()) {
		        String groupName = "ERCOT";

		        for (String jobName: RTLMPScheduler.getJobNames(groupName)) {
		        	for (Trigger jobTrigger: RTLMPScheduler.getTriggersOfJob(jobName, groupName))
		        		logger.info(jobName + " is scheduled to run at " + jobTrigger.getNextFireTime());
		        }	
		    }
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
