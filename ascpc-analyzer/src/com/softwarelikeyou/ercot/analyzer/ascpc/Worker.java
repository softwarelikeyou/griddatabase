package com.softwarelikeyou.ercot.analyzer.ascpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.softwarelikeyou.analyzer.WorkerHelper;
import com.softwarelikeyou.cache.FileCache;
import com.softwarelikeyou.facade.FileFacade;
import com.softwarelikeyou.model.entity.File;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;
import com.softwarelikeyou.model.entity.ascpc.ASCPCDaily;
import com.softwarelikeyou.pojo.ERCOTFile;
import com.softwarelikeyou.util.HibernateUtil;
import com.softwarelikeyou.util.ResourceUtil;

public class Worker {
	private static Logger logger = Logger.getLogger(Worker.class);
	
	public static void main(String[] args) {
        if (args.length != 1) {
	        System.out.println("Usage: java Worker yyyymmdd");
	        System.exit(1);
	    }
        
		List<ERCOTFile> filesToDownload = new ArrayList<ERCOTFile>();
		ERCOTFile.FileType fileType = ERCOTFile.FileType.ASCPC;
		String date = args[0];
		String endswith = "csv.zip";
		
		if (!WorkerHelper.validateDate(date)) {
			System.out.println("Date " + date + " is of incorrect format, must be yyyymmdd");
			System.exit(1);
		}
		 
        if (!initialize()) {
	        logger.error("Could not initialize hibernate");
	        System.exit(1);
	    }
        	
		FileCache.initialize(File.FileType.ASCPC);

		try {			
			logger.info("Retrieving " + date + " " + fileType.name());
			CSVSaver saver = new CSVSaver();
			PostWorker post = new PostWorker();
			DailyWorker daily = new DailyWorker();
			filesToDownload = WorkerHelper.getFilesToDownload(fileType.name().toLowerCase(), date);
			if (filesToDownload.size() > 0) {
			    for (ERCOTFile file : filesToDownload) {
			    	if (!file.getName().endsWith(endswith)) continue;
			    	if (FileCache.getFiles().contains(file.getName())) continue;
			    	logger.info("Processing file " + file.getName());
				    file.setContent(WorkerHelper.getContents(file.getUrl()));				    
				    logger.info("Executing save operations");
				    saver.setFile(file);
				    if (!saver.execute()) continue;
				    logger.info("Executing post operations");
				    post.setIntevalDate(saver.getIntervalDate());
					post.execute();
				    logger.info("Executing daily operations");
					daily.setIntervalDate(saver.getIntervalDate());
					daily.execute();
				    logger.info("Saving file " + file.getName());
					FileFacade.save(file.getName(), file.getUrl(), File.MimeType.XML, File.FileType.ASCPC);
			    }
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (!HibernateUtil.getSessionFactory().isClosed())
			    HibernateUtil.shutdown();
		}
	}
	
	public static boolean initialize() throws HibernateException {
	    Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(Environment.DRIVER, ResourceUtil.get().getString("hibernate.connection.driver_class"));
        hibernateProperties.setProperty(Environment.URL, ResourceUtil.get().getString("hibernate.connection.url"));
        hibernateProperties.setProperty(Environment.USER, ResourceUtil.get().getString("hibernate.connection.username"));
	    hibernateProperties.setProperty(Environment.PASS, ResourceUtil.get().getString("hibernate.connection.password"));
	    hibernateProperties.setProperty(Environment.DIALECT, ResourceUtil.get().getString("hibernate.dialect"));
	    hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, ResourceUtil.get().getString("hibernate.hbm2ddl.auto"));
	    hibernateProperties.setProperty(Environment.SHOW_SQL, ResourceUtil.get().getString("hibernate.show_sql"));

        Configuration hibernateConfiguration = new Configuration()
        .addAnnotatedClass(ASCPC.class)
        .addAnnotatedClass(File.class)
        .addAnnotatedClass(ASCPCDaily.class)
        .setProperties(hibernateProperties);
        
	    logger.info("Initializing Hibernate");
        HibernateUtil.getInstance(hibernateConfiguration);
        return HibernateUtil.test();
	}
}
