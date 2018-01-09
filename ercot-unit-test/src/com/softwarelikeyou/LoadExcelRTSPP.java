package com.softwarelikeyou;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.softwarelikeyou.exception.RTSPPException;
import com.softwarelikeyou.facade.ASCPCDailyFacade;
import com.softwarelikeyou.facade.CountyContourFacade;
import com.softwarelikeyou.facade.RTSPPFacade;
import com.softwarelikeyou.model.entity.CountyContour;
import com.softwarelikeyou.model.entity.Daily;
import com.softwarelikeyou.model.entity.ascpc.ASCPCDaily;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;
import com.softwarelikeyou.util.HibernateUtil;

import junit.framework.TestCase;

public class LoadExcelRTSPP extends TestCase {
	 
    private final static String PACKAGE = "com.softwarelikeyou.model.entity.rtspp";

    private Properties hibernateProperties = new Properties();
 	 
    private Configuration hibernateConfiguration = new Configuration();

 	public void setUp() throws Exception {
 	    hibernateProperties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
 	    hibernateProperties.setProperty(Environment.URL, "jdbc:mysql://192.168.0.203/ercot");
 	    hibernateProperties.setProperty(Environment.USER, "root");
 	    hibernateProperties.setProperty(Environment.PASS, "dinky01");
 	    hibernateProperties.setProperty(Environment.SHOW_SQL, "true");
 	    hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, "validate");
 	    hibernateProperties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
 	        
 	    hibernateConfiguration.addAnnotatedClass(CountyContour.class);

 	    hibernateConfiguration.setProperties(hibernateProperties);
 	    
     	HibernateUtil.getInstance(hibernateConfiguration);
 	}
 	
 	public void tearDown() throws Exception {
 	    HibernateUtil.shutdown();
 	}
 	
 	public void testCreate() {
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if (session == null)
				System.out.println("session is null");
			
			for (String namePart : CountyContourFacade.findNameParts()) {
				for (CountyContour contour : CountyContourFacade.findByNamePart(namePart)) 
					System.out.println(contour.getName());
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
