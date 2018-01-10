package com.griddatabase.analyzer.ascpc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.softwarelikeyou.analyzer.WorkerHelper;
import com.softwarelikeyou.exception.ASCPCException;
import com.softwarelikeyou.exception.DailyException;
import com.softwarelikeyou.facade.ASCPCDailyFacade;
import com.softwarelikeyou.facade.ASCPCFacade;
import com.softwarelikeyou.model.entity.Daily;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;
import com.softwarelikeyou.model.entity.ascpc.ASCPCDaily;
import com.softwarelikeyou.util.HibernateUtil;
import com.softwarelikeyou.util.Util;

public class DailyWorker {
	private Date intervalDate;
	private Date previousDate;
	
	private List<String> prices = Arrays.asList("NSPIN","REGDN","REGUP", "RRS");
	
	public void setIntervalDate(final Date value) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(value);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		intervalDate = calendar.getTime();
		calendar.add(Calendar.DATE, -1);
		previousDate = calendar.getTime();
	}
	
	public void execute() throws ASCPCException, DailyException {
	    if (intervalDate == null)
	    	return;
		
	    for (String price : prices) {
		    for (Daily.Type type : Daily.Type.values()) {
		        ASCPCDaily entity = new ASCPCDaily();
			    entity.setIntervalDate(intervalDate);
			    entity.setType(type);
			    entity.setName(price);
		    	switch (type) {
		    	    case All:
		    	    	entity.setAverage(ASCPCFacade.findDailyAverage(intervalDate, price));
		    	    	entity.setAverageChange(WorkerHelper.formatFloat(entity.getAverage() - ASCPCFacade.findDailyAverage(previousDate, price)));
		    	    	entity.setHigh(ASCPCFacade.findDailyMax(intervalDate, price));
		    	    	entity.setLow(ASCPCFacade.findDailyMin(intervalDate, price));
		    			ASCPCDailyFacade.createOrUpdate(entity);
		    	    	break;
		    	    case Peak:
		    	    	entity.setAverage(ASCPCFacade.findPeakAverage(intervalDate, price));
		    	    	entity.setAverageChange(WorkerHelper.formatFloat(entity.getAverage() - ASCPCFacade.findPeakAverage(previousDate, price)));
		    	    	entity.setHigh(ASCPCFacade.findPeakMax(intervalDate, price));
		    	    	entity.setLow(ASCPCFacade.findPeakMin(intervalDate, price));
		    			ASCPCDailyFacade.createOrUpdate(entity);
		    	    	break;
		    	    case OffPeak:
		    	    	entity.setAverage(ASCPCFacade.findOffPeakAverage(intervalDate, price));
		    	    	entity.setAverageChange(WorkerHelper.formatFloat(entity.getAverage() - ASCPCFacade.findOffPeakAverage(previousDate, price)));
		    	    	entity.setHigh(ASCPCFacade.findOffPeakMax(intervalDate, price));
		    	    	entity.setLow(ASCPCFacade.findOffPeakMin(intervalDate, price));
		    			ASCPCDailyFacade.createOrUpdate(entity);
		    	    	break;
		    		default:
		    			break;
		    	}
		    }
	    }
	}
	
	public static void main(String[] args) {
        if (args.length != 1) {
	        System.out.println("Usage: java DailyWorker yyyyMMdd");
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
 	    hibernateConfiguration.addAnnotatedClass(ASCPC.class);
 	    hibernateConfiguration.addAnnotatedClass(ASCPCDaily.class);
 	    hibernateConfiguration.setProperties(hibernateProperties);
     	HibernateUtil.getInstance(hibernateConfiguration);
     	
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (session == null)
			System.out.println("session is null");
		session.close();
	}
}
