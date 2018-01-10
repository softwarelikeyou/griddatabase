package com.griddatabase.analyzer.rtspp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.softwarelikeyou.facade.RTSPPDailyFacade;
import com.softwarelikeyou.facade.RTSPPFacade;
import com.softwarelikeyou.model.entity.File;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;
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
import com.softwarelikeyou.util.HibernateUtil;
import com.softwarelikeyou.util.Util;

public class DailyWorker {
	private static Logger logger = Logger.getLogger(DailyWorker.class);
	
	private static List<Class<? extends RTSPP>> list = new ArrayList<Class<? extends RTSPP>>();

	static {
        list.add(RTSPPHU15M.class);
        list.add(RTSPPLCCRN15M.class);
        list.add(RTSPPLZ15M.class);
        list.add(RTSPPLZDC15M.class);
        list.add(RTSPPLZDCEW15M.class);
        list.add(RTSPPLZEW15M.class);
        list.add(RTSPPPCCRN15M.class);
        list.add(RTSPPPUN15M.class);
        list.add(RTSPPRN15M.class);
	}
	
	private Date intervalDate;
	
	public void setIntervalDate(Date value) {
		intervalDate = value;
	}
	
	public Date getIntervalDate() {
		return intervalDate;
	}
	
	public boolean execute() {
		if (intervalDate == null)
			return false;
        
		try {
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(intervalDate);
    		calendar.set(Calendar.HOUR_OF_DAY, 0);
    		calendar.set(Calendar.MINUTE, 0);
    		calendar.set(Calendar.SECOND, 0);
    		
    		Date current = calendar.getTime();
    		calendar.add(Calendar.DATE, -1);
    		Date previous = calendar.getTime();
    		
			for (Class<? extends RTSPP> clazz : list) {
    		    for (String name : RTSPPFacade.findUniqueSettlementPoints(clazz)) {
    			    RTSPPDaily entity = new RTSPPDaily();
    			    entity.setIntervalDate(current);
    			    entity.setName(name);
    			    
    			    entity.setType(RTSPPDaily.Type.All);
    			    entity.setAverage(RTSPPFacade.findDailyAverage(clazz, entity.getIntervalDate(), name));
    			    entity.setLow(RTSPPFacade.findDailyMin(clazz, entity.getIntervalDate(), name));
    			    entity.setHigh(RTSPPFacade.findDailyMax(clazz, entity.getIntervalDate(), name));
    			    entity.setAverageChange(entity.getAverage() - RTSPPFacade.findDailyAverage(clazz, previous, name));
    			    RTSPPDailyFacade.createOrUpdate(entity);
    			    
    			    entity.setType(RTSPPDaily.Type.Peak);
    			    entity.setAverage(RTSPPFacade.findPeakAverage(clazz, entity.getIntervalDate(), name));
    			    entity.setLow(RTSPPFacade.findPeakMin(clazz, entity.getIntervalDate(), name));
    			    entity.setHigh(RTSPPFacade.findPeakMax(clazz, entity.getIntervalDate(), name));
    			    entity.setAverageChange(entity.getAverage() - RTSPPFacade.findPeakAverage(clazz, previous, name));
    			    RTSPPDailyFacade.createOrUpdate(entity);
    			    
    			    entity.setType(RTSPPDaily.Type.OffPeak);
    			    entity.setAverage(RTSPPFacade.findOffPeakAverage(clazz, entity.getIntervalDate(), name));
    			    entity.setLow(RTSPPFacade.findOffPeakMin(clazz, entity.getIntervalDate(), name));
    			    entity.setHigh(RTSPPFacade.findOffPeakMax(clazz, entity.getIntervalDate(), name));
    			    entity.setAverageChange(entity.getAverage() - RTSPPFacade.findOffPeakAverage(clazz, previous, name));
    			    RTSPPDailyFacade.createOrUpdate(entity);
				}
			}
		}
		catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return true;
	}
	
	public static void main(String[] args) {
        if (args.length != 1) {
	        System.out.println("Usage: java DailyWorker date (format: yyyyMMdd)");
	        System.exit(1);
	    }
		 		
		if (!validateDate(args[0])) {
			System.out.println("Date " + args[0] + " is of incorrect format, must be yyyyMMdd");
			System.exit(1);
		}
		try {
			hibernate();
			Date date = Util.stringToDate(args[0], "yyyyMMdd");
			DailyWorker worker = new DailyWorker();
			worker.setIntervalDate(date);
			worker.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			HibernateUtil.shutdown();
		}
	}
	
	private static boolean validateDate(final String date) {
		boolean results = true;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			format.parse(date);
		}
		catch (ParseException e) {
			results = false;
		}
		return results;
	}

	public static void hibernate() {
	    Properties hibernateProperties = new Properties();
	    Configuration hibernateConfiguration = new Configuration();
 	    hibernateProperties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
 	    hibernateProperties.setProperty(Environment.URL, "jdbc:mysql://localhost/ercot");
 	    hibernateProperties.setProperty(Environment.USER, "root");
 	    hibernateProperties.setProperty(Environment.PASS, "dinky01");
 	    hibernateProperties.setProperty(Environment.SHOW_SQL, "false");
 	    hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, "validate");
 	    hibernateProperties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
 	    hibernateConfiguration.addAnnotatedClass(RTSPPDaily.class);
        hibernateConfiguration.addAnnotatedClass(RTSPPAH15M.class);
        hibernateConfiguration.addAnnotatedClass(RTSPPHU15M.class);       
        hibernateConfiguration.addAnnotatedClass(RTSPPLCCRN15M.class);       
        hibernateConfiguration.addAnnotatedClass(RTSPPLZ15M.class);       
        hibernateConfiguration.addAnnotatedClass(RTSPPLZDC15M.class);                
        hibernateConfiguration.addAnnotatedClass(RTSPPLZDCEW15M.class);        
        hibernateConfiguration.addAnnotatedClass(RTSPPLZEW15M.class);        
        hibernateConfiguration.addAnnotatedClass(RTSPPPCCRN15M.class);        
        hibernateConfiguration.addAnnotatedClass(RTSPPPUN15M.class);        
        hibernateConfiguration.addAnnotatedClass(RTSPPRN15M.class);        
        hibernateConfiguration.addAnnotatedClass(RTSPPSH15M.class);    
        hibernateConfiguration.addAnnotatedClass(SettlementPoint.class);
 	    hibernateConfiguration.addAnnotatedClass(File.class);
 	    hibernateConfiguration.setProperties(hibernateProperties);
     	HibernateUtil.getInstance(hibernateConfiguration);
     	
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (session == null)
			System.out.println("session is null");
		session.close();
	}
}
