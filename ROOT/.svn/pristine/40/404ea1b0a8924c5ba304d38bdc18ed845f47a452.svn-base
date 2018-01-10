package com.griddatabase;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.griddatabase.model.cache.CountyContourCache;
import com.griddatabase.model.cache.RTLMPCache;
import com.griddatabase.model.cache.RTSPPCache;
import com.griddatabase.model.entity.User;
import com.griddatabase.model.facade.PasswordEncoder;
import com.griddatabase.model.facade.UserFacade;
import com.softwarelikeyou.model.entity.CountyContour;
import com.softwarelikeyou.model.entity.File;
import com.softwarelikeyou.model.entity.HenryHub;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.softwarelikeyou.model.entity.SettlementPointTemperature;
import com.softwarelikeyou.model.entity.UserData;
import com.softwarelikeyou.model.entity.ZipCode;
import com.softwarelikeyou.model.entity.ZipCodeTemperature;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;
import com.softwarelikeyou.model.entity.ascpc.ASCPCDaily;
import com.softwarelikeyou.model.entity.rtspp.RTSPPDaily;
import com.softwarelikeyou.model.entity.rtspp.ah.RTSPPAH15M;
import com.softwarelikeyou.model.entity.rtspp.hu.RTSPPHU15M;
import com.softwarelikeyou.model.entity.rtspp.lccrn.RTSPPLCCRN15M;
import com.softwarelikeyou.model.entity.rtspp.lz.RTSPPLZ15M;
import com.softwarelikeyou.model.entity.rtspp.lzdc.RTSPPLZDC15M;
import com.softwarelikeyou.model.entity.rtspp.lzdcew.RTSPPLZDCEW15M;
import com.softwarelikeyou.model.entity.rtspp.lzew.RTSPPLZEW15M;
import com.softwarelikeyou.model.entity.rtspp.pccrn.RTSPPPCCRN15M;
import com.softwarelikeyou.model.entity.rtspp.pun.RTSPPPUN15M;
import com.softwarelikeyou.model.entity.rtspp.rn.RTSPPRN15M;
import com.softwarelikeyou.model.entity.rtspp.sh.RTSPPSH15M;
import com.griddatabase.scheduler.ASCPCJob;
import com.griddatabase.scheduler.RTSPPJob;
import com.griddatabase.scheduler.ResetLMPSPPCache;
import com.griddatabase.settlementpoints.Jobs;
import com.griddatabase.util.ActiveMQUtil;
import com.softwarelikeyou.util.HibernateUtil;
import com.softwarelikeyou.util.ResourceUtil;

public class Initialization implements ServletContextListener {
	private static Logger logger = Logger.getLogger(Initialization.class);
	
	private static SchedulerFactory schedulerFactory;
	private static Scheduler scheduler;
	
	private static SchedulerFactory settlmentPointSchedulerFactory;
	public static Scheduler settlementPointScheduler;
	
	public void contextDestroyed(ServletContextEvent sce) {
	    try {	
	 	    if (ActiveMQUtil.isStarted())
		        ActiveMQUtil.stop();
			if (!HibernateUtil.getSessionFactory().isClosed())
			    HibernateUtil.shutdown();
			if (RTLMPCache.isStarted())
				RTLMPCache.stop();
			if (RTSPPCache.isStarted())
				RTSPPCache.stop();
	        if (schedulerFactory.getScheduler().isStarted())
	        	schedulerFactory.getScheduler().shutdown();
	        if (settlmentPointSchedulerFactory.getScheduler().isStarted())
	        	settlmentPointSchedulerFactory.getScheduler().shutdown();
		} 
	    catch (Exception e) {
			logger.error(e.getCause(), e);
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
		    hibernateProperties.setProperty("hibernate.connection.autoreconnect", "true");
			hibernateProperties.setProperty("hibernate.connection.autoreconnectforpools", "true");
			hibernateProperties.setProperty("hibernate.c3p0.idle_test_period", "30");
			hibernateProperties.setProperty(Environment.C3P0_MIN_SIZE, "10");
			hibernateProperties.setProperty("hibernate.connection.provider_class", "org.hibernate.connection.C3P0ConnectionProvider");
		    
	        Configuration hibernateConfiguration = new Configuration()
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(UserData.class)
            
            .addAnnotatedClass(ASCPCDaily.class)
            .addAnnotatedClass(ASCPC.class)
            
            .addAnnotatedClass(RTSPPDaily.class)
            .addAnnotatedClass(RTSPPAH15M.class)
            .addAnnotatedClass(RTSPPHU15M.class)        
            .addAnnotatedClass(RTSPPLCCRN15M.class)       
            .addAnnotatedClass(RTSPPLZ15M.class)       
            .addAnnotatedClass(RTSPPLZDC15M.class)                
            .addAnnotatedClass(RTSPPLZDCEW15M.class)        
            .addAnnotatedClass(RTSPPLZEW15M.class)        
            .addAnnotatedClass(RTSPPPCCRN15M.class)        
            .addAnnotatedClass(RTSPPPUN15M.class)        
            .addAnnotatedClass(RTSPPRN15M.class)        
            .addAnnotatedClass(RTSPPSH15M.class)    
            
            .addAnnotatedClass(SettlementPoint.class)
            .addAnnotatedClass(SettlementPointTemperature.class)

            .addAnnotatedClass(File.class)
	      
            .addAnnotatedClass(ZipCode.class)
            .addAnnotatedClass(ZipCodeTemperature.class)
            .addAnnotatedClass(CountyContour.class)
	        
            .addAnnotatedClass(HenryHub.class)
            
            .setProperties(hibernateProperties);
	        
            HibernateUtil.getInstance(hibernateConfiguration);
            if (!HibernateUtil.test())
        	    logger.error("Hibernate could not be initialized");
            else
                logger.info("Initialized Hibernate");


			User user = UserFacade.findByUsername("admin");
			if (user == null) {
				logger.info("Creating Administrator (admin) Account");
				user = new User();
			    user.setUsername("admin");
			    user.setDisplay("Administrator Account");
			    user.setPassword(new PasswordEncoder().encodePassword("admin", null));
			    user.setType(User.UserType.ADMINISTRATOR);
			    user = UserFacade.createOrUpdate(user);
			}
			
			ActiveMQUtil.getInstance();
			if (ActiveMQUtil.isStarted())
                logger.info("Initialized ActiveMQ");
			
			RTLMPCache.getInstance();
			
			RTSPPCache.getInstance();
			
			CountyContourCache.getInstance();
			
	        logger.info("Initializing Scheduler");
	        Properties schedulerProperties = new Properties();
	        schedulerProperties.setProperty(StdSchedulerFactory.PROP_SCHED_INSTANCE_NAME, "Scheduer");
	        schedulerProperties.setProperty(StdSchedulerFactory.DEFAULT_INSTANCE_ID, "5");
	        schedulerProperties.setProperty(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, "org.quartz.simpl.SimpleThreadPool");
	        schedulerProperties.setProperty("org.quartz.threadPool.threadCount", "2");
	        schedulerProperties.setProperty(StdSchedulerFactory.PROP_JOB_STORE_CLASS, "org.quartz.simpl.RAMJobStore");

            schedulerFactory = new StdSchedulerFactory(schedulerProperties);
            scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            
            setJobs();
            
		    if (scheduler.isStarted()) {
		        String groupName = "ERCOT";

		        for (String jobName: scheduler.getJobNames(groupName)) {
		        	for (Trigger jobTrigger: scheduler.getTriggersOfJob(jobName, groupName))
		        		logger.info(jobName + " is scheduled to run at " + jobTrigger.getNextFireTime());
		        }	
		    }
		   
	        logger.info("Initializing SettlementPoint Scheduler");
	        Properties settlementPointSchedulerProperties = new Properties();
	        settlementPointSchedulerProperties.setProperty(StdSchedulerFactory.PROP_SCHED_INSTANCE_NAME, "SettlmentPointScheduer");
	        settlementPointSchedulerProperties.setProperty(StdSchedulerFactory.DEFAULT_INSTANCE_ID, "10");
	        settlementPointSchedulerProperties.setProperty(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, "org.quartz.simpl.SimpleThreadPool");
	        settlementPointSchedulerProperties.setProperty("org.quartz.threadPool.threadCount", "4");
	        settlementPointSchedulerProperties.setProperty(StdSchedulerFactory.PROP_JOB_STORE_CLASS, "org.quartz.simpl.RAMJobStore");

	        settlmentPointSchedulerFactory = new StdSchedulerFactory(schedulerProperties);
	        settlementPointScheduler = settlmentPointSchedulerFactory.getScheduler();
	        settlementPointScheduler.start();
		    Jobs.getSettlementPoints(settlementPointScheduler);
		    Jobs.getSettlementPointTemperatureBackfill(settlementPointScheduler);
            Jobs.getStations(settlementPointScheduler);
            Jobs.getHenryHub(scheduler);
		}
		catch (Exception e) {
		    logger.error(e.getCause(), e);
		}
	}
	
	private void setJobs() {
		
		try {
			JobDetail jobDetail = new JobDetail();
			jobDetail.setName("ResetLMPSPPCache");
			jobDetail.setGroup("ERCOT");
			jobDetail.setJobClass(ResetLMPSPPCache.class);
			CronTrigger trigger;
		    trigger = new CronTrigger();
		    trigger.setName(jobDetail.getName());
		    trigger.setJobName(jobDetail.getName());
		    trigger.setJobGroup(jobDetail.getGroup());
		    trigger.setCronExpression("0 01 0 ? * *");
		    scheduler.scheduleJob(jobDetail, trigger);
		    
			jobDetail = new JobDetail();
			jobDetail.setName("ASCPCAnalyzer");
			jobDetail.setGroup("ERCOT");
			jobDetail.setJobClass(ASCPCJob.class);
		    trigger = new CronTrigger();
		    trigger.setName(jobDetail.getName());
		    trigger.setJobName(jobDetail.getName());
		    trigger.setJobGroup(jobDetail.getGroup());
		    trigger.setCronExpression("0 0 /1 ? * *");
		    scheduler.scheduleJob(jobDetail, trigger);
		    
			jobDetail = new JobDetail();
			jobDetail.setName("RTSPPAnalyzer");
			jobDetail.setGroup("ERCOT");
			jobDetail.setJobClass(RTSPPJob.class);
		    trigger = new CronTrigger();
		    trigger.setName(jobDetail.getName());
		    trigger.setJobName(jobDetail.getName());
		    trigger.setJobGroup(jobDetail.getGroup());
		    trigger.setCronExpression("0 /15 * ? * *");
		    scheduler.scheduleJob(jobDetail, trigger);
		    
		}
		catch (Exception e) {
			logger.error("Scheduler Job", e);
		}
	}
}
