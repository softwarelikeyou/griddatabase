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

import com.softwarelikeyou.model.entity.CountyContour;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.softwarelikeyou.model.entity.ZipCode;
import com.softwarelikeyou.model.entity.ZipCodeTemperature;
import com.softwarelikeyou.scheduler.Jobs;
import com.softwarelikeyou.util.ResourceUtil;
import com.softwarelikeyou.util.HibernateUtil;

public class Initialization implements ServletContextListener {
	private static Logger logger = Logger.getLogger(Initialization.class);
	
	private static SchedulerFactory weatherFactory;
	public static Scheduler weather;

	public void contextDestroyed(ServletContextEvent sce) {
	    try {	
	        if (weatherFactory.getScheduler().isStarted())
	        	weatherFactory.getScheduler().shutdown();
			if (!HibernateUtil.getSessionFactory().isClosed())
			    HibernateUtil.shutdown();
		} 
	    catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		try { 
			if (!Boolean.valueOf(ResourceUtil.get().getString("weather.scheduler.start")))
				return;
			
		    Properties hibernateProperties = new Properties();
	        hibernateProperties.setProperty(Environment.DRIVER, ResourceUtil.get().getString("hibernate.connection.driver_class"));
	        hibernateProperties.setProperty(Environment.URL, ResourceUtil.get().getString("hibernate.connection.url"));
	        hibernateProperties.setProperty(Environment.USER, ResourceUtil.get().getString("hibernate.connection.username"));
		    hibernateProperties.setProperty(Environment.PASS, ResourceUtil.get().getString("hibernate.connection.password"));
		    hibernateProperties.setProperty(Environment.DIALECT, ResourceUtil.get().getString("hibernate.dialect"));
		    hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, ResourceUtil.get().getString("hibernate.hbm2ddl.auto"));
		    hibernateProperties.setProperty(Environment.SHOW_SQL, ResourceUtil.get().getString("hibernate.show_sql"));

	        Configuration configuration = new Configuration()
            .addAnnotatedClass(SettlementPoint.class)
            .addAnnotatedClass(ZipCode.class)
            .addAnnotatedClass(ZipCodeTemperature.class)
            .addAnnotatedClass(CountyContour.class)
            .setProperties(hibernateProperties);

		    logger.info("Initializing Hibernate");
            HibernateUtil.getInstance(configuration);
            if (!HibernateUtil.test())
        	    logger.error("Hibernate could not be initialized");
			
            logger.info("Initializing Weather Service");
            Properties weatherProperties = new Properties();
            weatherProperties.setProperty(StdSchedulerFactory.PROP_SCHED_INSTANCE_NAME, ResourceUtil.get().getString("weather.scheduler.instance.name"));
            weatherProperties.setProperty(StdSchedulerFactory.DEFAULT_INSTANCE_ID, ResourceUtil.get().getString("weather.scheduler.instance.id"));
            weatherProperties.setProperty(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, "org.quartz.simpl.SimpleThreadPool");
            weatherProperties.setProperty("org.quartz.threadPool.threadCount", ResourceUtil.get().getString("weather.scheduler.thread.count"));
            weatherProperties.setProperty(StdSchedulerFactory.PROP_JOB_STORE_CLASS, "org.quartz.simpl.RAMJobStore");
            
            weatherFactory = new StdSchedulerFactory(weatherProperties);
            weather = weatherFactory.getScheduler();
            weather.start();
		    Jobs.getSettlementPoints(weather);
            Jobs.getStations(weather);
            Jobs.getZipCodes(weather);
            		    
		    if (weather.isStarted()) {
		        String groupName = "weather";

		        for (String jobName: weather.getJobNames(groupName)) {
		        	for (Trigger jobTrigger: weather.getTriggersOfJob(jobName, groupName))
		        		logger.info(jobName + " is scheduled to run at " + jobTrigger.getNextFireTime());
		        }	
		    }
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
