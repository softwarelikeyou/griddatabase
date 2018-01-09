package com.softwarelikeyou;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.softwarelikeyou.scheduler.Jobs;
import com.softwarelikeyou.util.FileUtil;
import com.softwarelikeyou.util.ResourceUtil;

public class Initialization implements ServletContextListener {
	private static Logger logger = Logger.getLogger(Initialization.class);
	
	private static SchedulerFactory schedulerFactory;
	public static Scheduler scheduler;

	public void contextDestroyed(ServletContextEvent sce) {
	    try {	
	        if (schedulerFactory.getScheduler().isStarted())
	        	schedulerFactory.getScheduler().shutdown();
		} 
	    catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {    
            logger.info("Initializing Scheduler");
            Properties quartzProperties = new Properties();
            quartzProperties.setProperty(StdSchedulerFactory.PROP_SCHED_INSTANCE_NAME, ResourceUtil.get().getString("quartz.scheduler.instance.name"));
            quartzProperties.setProperty(StdSchedulerFactory.DEFAULT_INSTANCE_ID, ResourceUtil.get().getString("quartz.scheduler.instance.id"));
            quartzProperties.setProperty(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, ResourceUtil.get().getString("quartz.scheduler.thread.pool.class"));
            quartzProperties.setProperty("org.quartz.threadPool.threadCount", ResourceUtil.get().getString("quartz.scheduler.thread.count"));
            quartzProperties.setProperty(StdSchedulerFactory.PROP_JOB_STORE_CLASS, ResourceUtil.get().getString("org.quartz.jobStore.class"));
           
            schedulerFactory = new StdSchedulerFactory(quartzProperties);
            scheduler = schedulerFactory.getScheduler();
            scheduler.start();
		    Jobs.get(scheduler);
		    
		    if (scheduler.isStarted()) {
		        String groupName = "ercot";

		        for (String jobName: scheduler.getJobNames(groupName)) {
		        	for (Trigger jobTrigger: scheduler.getTriggersOfJob(jobName, groupName))
		        		logger.info(jobName + " is scheduled to run at " + jobTrigger.getNextFireTime());
		        }	
		    }
		    
		    FileUtil.initialize();
		}
		catch (RuntimeException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
