package com.softwarelikeyou;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.softwarelikeyou.facade.CountyContourFacade;
import com.softwarelikeyou.model.entity.CountyContour;
import com.softwarelikeyou.util.HibernateUtil;

import junit.framework.TestCase;

public class CountyContourTestCase extends TestCase {
	 
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
 	        
 	    hibernateConfiguration.addAnnotatedClass(CountyContour.class);

 	    hibernateConfiguration.setProperties(hibernateProperties);
 	    
     	HibernateUtil.getInstance(hibernateConfiguration);
 	}
 	
 	public void tearDown() throws Exception {
 	    HibernateUtil.shutdown();
 	}

	
 	public void test() {
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if (session == null)
				System.out.println("session is null");
			for (String namePart : CountyContourFacade.findNameParts()) {
			    for (CountyContour contour : CountyContourFacade.findByNamePart(namePart)) {
					System.out.println(contour.getName());
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
