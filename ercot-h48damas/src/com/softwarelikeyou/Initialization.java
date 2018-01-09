package com.softwarelikeyou;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.softwarelikeyou.util.HibernateUtil;
import com.softwarelikeyou.model.entity.File;
import com.softwarelikeyou.model.entity.H48DAMASAGOFFNS;
import com.softwarelikeyou.model.entity.H48DAMASAGONNS;
import com.softwarelikeyou.model.entity.H48DAMASAGREGDN;
import com.softwarelikeyou.model.entity.H48DAMASAGREGUP;
import com.softwarelikeyou.model.entity.H48DAMASAGRRSGN;
import com.softwarelikeyou.model.entity.H48DAMASAGRRSLD;
import com.softwarelikeyou.model.entity.H48DAMASAGRRSNC;
import com.softwarelikeyou.model.entity.H48DAMASCS;
import com.softwarelikeyou.scheduler.Jobs;
import com.softwarelikeyou.util.ResourceUtil;

public class Initialization implements ServletContextListener {
	private static Logger logger = Logger.getLogger(Initialization.class);
	
	private static SchedulerFactory schedulerFactory;
	public static Scheduler scheduler;

	public void contextDestroyed(ServletContextEvent sce) {
	    try {	
	        if (schedulerFactory.getScheduler().isStarted())
	        	schedulerFactory.getScheduler().shutdown();
			if (!HibernateUtil.getSessionFactory().isClosed())
			    HibernateUtil.shutdown();
		} 
	    catch (Exception e) {
			logger.error(e);
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {   		  
		    Properties hibernateProperties = new Properties();
	        hibernateProperties.setProperty(Environment.DRIVER, ResourceUtil.get().getString("hibernate.connection.driver_class"));
	        hibernateProperties.setProperty(Environment.URL, ResourceUtil.get().getString("hibernate.connection.url"));
	        hibernateProperties.setProperty(Environment.USER, ResourceUtil.get().getString("hibernate.connection.username"));
		    hibernateProperties.setProperty(Environment.PASS, ResourceUtil.get().getString("hibernate.connection.password"));
		    hibernateProperties.setProperty(Environment.DIALECT, ResourceUtil.get().getString("hibernate.dialect"));
		    hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, ResourceUtil.get().getString("hibernate.hbm2ddl.auto"));
		    hibernateProperties.setProperty(Environment.SHOW_SQL, ResourceUtil.get().getString("hibernate.show_sql"));

	        Configuration hibernateConfiguration = new Configuration()
	        .addAnnotatedClass(File.class)
            .addAnnotatedClass(H48DAMASAGOFFNS.class)
            .addAnnotatedClass(H48DAMASAGONNS.class)
            .addAnnotatedClass(H48DAMASAGREGDN.class)
            .addAnnotatedClass(H48DAMASAGREGUP.class)
            .addAnnotatedClass(H48DAMASAGRRSGN.class)
            .addAnnotatedClass(H48DAMASAGRRSLD.class)
            .addAnnotatedClass(H48DAMASAGRRSNC.class)
            .addAnnotatedClass(H48DAMASCS.class)
            .setProperties(hibernateProperties);
	        
		    logger.info("Initializing Hibernate");
            HibernateUtil.getInstance(hibernateConfiguration);
            if (!HibernateUtil.test())
        	    logger.error("Hibernate could not be initialized");
			
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
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
