package com.softwarelikeyou;

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
import com.softwarelikeyou.facade.ASCPCFacade;
import com.softwarelikeyou.model.entity.Daily;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;
import com.softwarelikeyou.model.entity.ascpc.ASCPCDaily;
import com.softwarelikeyou.util.HibernateUtil;

import junit.framework.TestCase;

public class ASCPCDailyTestCase extends TestCase {
	 
    private Properties hibernateProperties = new Properties();
 	 
    private Configuration hibernateConfiguration = new Configuration();

 	public void setUp() throws Exception {
 	    hibernateProperties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
 	    hibernateProperties.setProperty(Environment.URL, "jdbc:mysql://192.168.0.203/ercot");
 	    hibernateProperties.setProperty(Environment.USER, "root");
 	    hibernateProperties.setProperty(Environment.PASS, "dinky01");
 	    hibernateProperties.setProperty(Environment.SHOW_SQL, "false");
 	    hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, "validate");
 	    hibernateProperties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
 	        
 	    hibernateConfiguration.addAnnotatedClass(ASCPC.class);

 	    hibernateConfiguration.setProperties(hibernateProperties);
 	    
     	HibernateUtil.getInstance(hibernateConfiguration);
 	}
 	
 	public void tearDown() throws Exception {
 	    HibernateUtil.shutdown();
 	}
 	
	private Date intervalDate;
	private Date previousDate;
	
	private List<String> prices = Arrays.asList("NSPIN","REGDN","REGUP", "RRS");
	
	private void setIntervalDate(final Date value) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(value);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		intervalDate = calendar.getTime();
		calendar.add(Calendar.DATE, -1);
		previousDate = calendar.getTime();
	}
	
	private void print(ASCPCDaily daily) {
		System.out.println(daily.getName());
		System.out.println(daily.getType());
		System.out.println(daily.getIntervalDate());
		System.out.println(daily.getLow());
		System.out.println(daily.getAverage());
		System.out.println(daily.getHigh());
		System.out.println(daily.getAverageChange());
		System.out.println("\n");
	}
	
 	public void testASCPC() {
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if (session == null)
				System.out.println("session is null");
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date date = format.parse("20130209");
			setIntervalDate(date);
			
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
			    	    	print(entity);
			    	    	break;
			    	    case Peak:
			    	    	entity.setAverage(ASCPCFacade.findPeakAverage(intervalDate, price));
			    	    	entity.setAverageChange(WorkerHelper.formatFloat(entity.getAverage() - ASCPCFacade.findPeakAverage(previousDate, price)));
			    	    	entity.setHigh(ASCPCFacade.findPeakMax(intervalDate, price));
			    	    	entity.setLow(ASCPCFacade.findPeakMin(intervalDate, price));
			    	    	print(entity);
			    	    	break;
			    	    case OffPeak:
			    	    	entity.setAverage(ASCPCFacade.findOffPeakAverage(intervalDate, price));
			    	    	entity.setAverageChange(WorkerHelper.formatFloat(entity.getAverage() - ASCPCFacade.findOffPeakAverage(previousDate, price)));
			    	    	entity.setHigh(ASCPCFacade.findOffPeakMax(intervalDate, price));
			    	    	entity.setLow(ASCPCFacade.findOffPeakMin(intervalDate, price));
			    	    	print(entity);
			    	    	break;
			    		default:
			    			break;
			    	}
			    }
		    }
		    
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
    		if (session.isConnected())
    		    session.close();
		}
 	}
}
