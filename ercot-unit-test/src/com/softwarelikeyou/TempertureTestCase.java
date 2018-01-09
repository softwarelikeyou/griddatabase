package com.softwarelikeyou;

import java.util.Calendar;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.softwarelikeyou.NDFD.Type;
import com.softwarelikeyou.exception.WeatherException;
import com.softwarelikeyou.facade.SettlementPointFacade;
import com.softwarelikeyou.facade.SettlementPointTemperatureFacade;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.softwarelikeyou.model.entity.SettlementPointTemperature;
import com.softwarelikeyou.util.HibernateUtil;

import junit.framework.TestCase;

public class TempertureTestCase extends TestCase {
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
 	        
 	    hibernateConfiguration.addAnnotatedClass(SettlementPointTemperature.class);
 	    hibernateConfiguration.addAnnotatedClass(SettlementPoint.class);

 	    hibernateConfiguration.setProperties(hibernateProperties);
 	    
     	HibernateUtil.getInstance(hibernateConfiguration);
 	}
 	
 	public void tearDown() throws Exception {
 	    HibernateUtil.shutdown();
 	}

 	public void testCount() {
 		Session session = null;
 		
 		try {
 			session = HibernateUtil.getSessionFactory().openSession();
			if (session == null)
				System.out.println("session is null");
			
			for (SettlementPointTemperature spt: SettlementPointTemperatureFacade.findAllWhereTemperatureIsMinus50(10)) {
							
				try {
					
			        SettlementPoint sp  = SettlementPointFacade.findById(spt.getSettlementPoint());

			        
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(spt.getDateTime());
				    calendar.set(Calendar.MINUTE, 0);
				    calendar.set(Calendar.SECOND, 0);
				    calendar.set(Calendar.MILLISECOND, 0);
				    
				    
					NDFD ndfd = new NDFD();
					ndfd.setSeed(calendar.getTime());
					ndfd.setLatitude(sp.getLatitude());
					ndfd.setLongitude(sp.getLongitude());
					
					String temp = "";
				    temp = ndfd.getTemperature(Type.LATLONG);
					   
					if (temp.equals("")) {
						try {
							WeatherBug weatherBug = new WeatherBug();
							weatherBug.setLatitude(sp.getLatitude());
							weatherBug.setLongitude(sp.getLongitude());
							temp = weatherBug.getTemperature();
						}
						catch (WeatherException e) {
						    System.out.println("Unable to retrieve temperature for " + sp.getName() + " - " + e.getMessage());
						}
					}
					
					System.out.println(spt.getSettlementPoint() + ", " + ndfd.getSeed().toString() + ", " + sp.getLatitude() + ", " + sp.getLongitude() + ", " + temp );

					if (temp != null && !temp.equals("")) {
				        System.out.println("Backfilling Temperature at " + sp.getName() + " to " + temp);
						SettlementPointTemperature entity = new SettlementPointTemperature();
						entity.setDateTime(calendar.getTime());
						entity.setSettlementPoint(sp.getName());
						entity.setTemperature(Integer.valueOf(temp));
						//SettlementPointTemperatureFacade.createOrUpdate(entity);
				    }
					
					Thread.sleep(5000);
					
				}
				catch (Exception e) {
				    e.printStackTrace();
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
 	public void testTest() {
 		Session session = null;
 		
 		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if (session == null)
				System.out.println("session is null");
						
			for (SettlementPoint sp : SettlementPointFacade.findAll()) {
				
				Calendar calendar = Calendar.getInstance();
				for (int year = 2010; year <= 2013; year++) {
				    calendar.set(Calendar.YEAR, year);
				    
				    for (int day = 1; day <=365; day++) {
					    calendar.set(Calendar.DAY_OF_YEAR, day);
							
				        calendar.set(Calendar.HOUR_OF_DAY, 24);
					    calendar.set(Calendar.MINUTE, 0);
					    calendar.set(Calendar.SECOND, 0);
					    
					    SettlementPointTemperature spt = new SettlementPointTemperature();
					    spt.setSettlementPoint(sp.getName());
					    spt.setDateTime(calendar.getTime());
					    SettlementPointTemperatureFacade.createOrUpdate(spt);
					    
					    for (int hour = 1; hour <= 23; hour++) {
					        calendar.set(Calendar.HOUR_OF_DAY, hour);
						    calendar.set(Calendar.MINUTE, 0);
						    calendar.set(Calendar.SECOND, 0);
						    
						    spt = new SettlementPointTemperature();
						    spt.setSettlementPoint(sp.getName());
						    spt.setDateTime(calendar.getTime());
						    SettlementPointTemperatureFacade.createOrUpdate(spt);
					    }
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
