package com.softwarelikeyou;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.softwarelikeyou.facade.FileFacade;
import com.softwarelikeyou.model.dao.FileDAO;
import com.softwarelikeyou.model.dao.RTLMP1HDAO;
import com.softwarelikeyou.model.entity.File.FileType;
import com.softwarelikeyou.model.entity.CountyContour;
import com.softwarelikeyou.model.entity.RTLMP1H;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.softwarelikeyou.model.entity.ZipCode;
import com.softwarelikeyou.model.entity.ZipCodeTemperature;
import com.softwarelikeyou.util.FileUtil;
import com.softwarelikeyou.util.HibernateUtil;
import com.softwarelikeyou.util.ResourceUtil;

import junit.framework.TestCase;

public class FileArchiveTestCase extends TestCase {
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
	      
	    hibernateConfiguration
        .addAnnotatedClass(ZipCode.class)
        .addAnnotatedClass(ZipCodeTemperature.class)
        .addAnnotatedClass(CountyContour.class);
        
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
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
    		if (session.isConnected())
    			session.close();
		}
 	}
 	
	public void testCreateDate() {
		String directoryString = FileUtil.getRootString() + FileUtil.getOSType().getSlash() + "rtdam";
        File directory = new File(directoryString);
        if (!directory.exists()) {
        	System.out.println("Directory does not exist");
        	return;
        }
        
        try {
            for (String name : Arrays.asList(directory.list())) {
        	    File file = new File(FileUtil.getRootString() + FileUtil.getOSType().getSlash() + "rtdam" + FileUtil.getOSType().getSlash() + name);
                BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                System.out.println(name);
        	    Date createDate = new Date(attr.creationTime().toMillis());
        	    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        	    System.out.println(format.format(createDate));
        	    Calendar calendar = Calendar.getInstance();
        	    calendar.setTime(new Date());
        	    //calendar.add(Calendar.DAY_OF_MONTH, -5);
        	    if (createDate.before(calendar.getTime()))
        	    	System.out.println("Archive");
            }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public void testArchive() {
		String key = "h48damas";
    	Session session = null;
		try {	
			String directoryString = FileUtil.getRootString() + FileUtil.getOSType().getSlash() + key;
	        File directory = new File(directoryString);
	        if (!directory.exists()) {
	        	System.out.println("Directory " + directoryString + " does not exist");
	        	return;
	        }
	        
			String archiveString = FileUtil.getRootString() + FileUtil.getOSType().getSlash() + "archive" + FileUtil.getOSType().getSlash() + key;
	        File archive = new File(archiveString);
	        if (!archive.exists()) {
	        	System.out.println("Archive directory " + archiveString + " does not exist");
	        	return;
	        }
	        
			session = HibernateUtil.getSessionFactory().openSession();
			
			List<com.softwarelikeyou.model.entity.File> dbFiles = FileFacade.findAll();
			
			for (com.softwarelikeyou.model.entity.File dbFile : dbFiles) {
		        if (!dbFile.getName().equals("cdr.00012331.0000000000000000.20120901.130046.DAMSPNP4190_csv.zip"))
		        	continue;
				String fileString = directoryString + FileUtil.getOSType().getSlash() + dbFile.getName();
		        File file = new File(fileString);
		        if (!file.exists()) {
		        	System.out.println("File " + fileString + " does not exist");
		        	continue;
		        }
		        
	            BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
		    	Date createDate = new Date(attr.creationTime().toMillis());
		    	Calendar calendar = Calendar.getInstance();
		    	calendar.setTime(new Date());
		        calendar.add(Calendar.DAY_OF_MONTH, -5);
		    	if (createDate.before(calendar.getTime())) {
		            if (file.renameTo(new File(archiveString, file.getName())))
		                System.out.println("Archived " + file.getName());
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
