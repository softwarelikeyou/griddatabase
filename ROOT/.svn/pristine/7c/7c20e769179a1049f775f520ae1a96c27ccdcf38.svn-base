package com.griddatabase.analyzer.ascpc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.softwarelikeyou.analyzer.WorkerHelper;
import com.softwarelikeyou.facade.ASCPCFacade;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;
import com.softwarelikeyou.util.HibernateUtil;
import com.softwarelikeyou.util.Util;

public class PostWorker {

	private static Logger logger = Logger.getLogger(PostWorker.class);
	
	private Date intervalDate;
	
	public void setIntervalDate(final Date value) {
		intervalDate = value;
	}
	
	public Date getIntervalDate() {
		return intervalDate;
	}
	
	public boolean execute() {
		if (intervalDate == null)
			return false;
		
		boolean results = true;
		try {
	        for (ASCPC ascpc : ASCPCFacade.findBetweenDates(intervalDate, intervalDate)) {
	        	
		        ASCPC previous = ASCPCFacade.findPreviousInterval(ascpc.getIntervalDate());
		        if (previous == null)
		        	continue;
		       
			    ascpc.setNSPINVelocity(WorkerHelper.formatFloat(ascpc.getNSPIN() - previous.getNSPIN()));
			    ascpc.setREGDNVelocity(WorkerHelper.formatFloat(ascpc.getREGDN() - previous.getREGDN()));
			    ascpc.setREGUPVelocity(WorkerHelper.formatFloat(ascpc.getREGUP() - previous.getREGUP()));
			    ascpc.setRRSVelocity(WorkerHelper.formatFloat(ascpc.getRRS() - previous.getRRS()));
			    ascpc.setNSPINAcceleration(WorkerHelper.formatFloat(ascpc.getNSPINVelocity() - previous.getNSPINVelocity()));
			    ascpc.setREGDNAcceleration(WorkerHelper.formatFloat(ascpc.getREGDNVelocity() - previous.getREGDNVelocity()));
			    ascpc.setREGUPAcceleration(WorkerHelper.formatFloat(ascpc.getREGUPVelocity() - previous.getREGUPVelocity()));
			    ascpc.setRRSAcceleration(WorkerHelper.formatFloat(ascpc.getRRSVelocity() - previous.getRRSVelocity()));
	        	
			    ASCPCFacade.createOrUpdate(ascpc);
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return false;
		}
		return results;
	}
	
	
	public static void main(String[] args) {
        if (args.length != 1) {
	        System.out.println("Usage: java PostWorker yyyyMMdd");
	        System.exit(1);
	    }
        
		if (!validateDate(args[0])) {
			System.out.println("Date " + args[0] + " is of incorrect format, must be yyyyMMdd");
			System.exit(1);
		}
		
		try {
			hibernate();
			Date date = Util.stringToDate(args[0], "yyyyMMdd");
			PostWorker worker = new PostWorker();
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
 	    hibernateConfiguration.setProperties(hibernateProperties);
     	HibernateUtil.getInstance(hibernateConfiguration);
     	
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (session == null)
			System.out.println("session is null");
		session.close();
	}
}
