package com.softwarelikeyou.ercot.analyzer.rtlmp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.softwarelikeyou.analyzer.WorkerHelper;
import com.softwarelikeyou.cache.FileCache;
import com.softwarelikeyou.facade.FileFacade;
import com.softwarelikeyou.model.entity.File;
import com.softwarelikeyou.pojo.ERCOTFile;
import com.softwarelikeyou.pojo.ERCOTFileDateComparator;
import com.softwarelikeyou.util.HibernateUtil;

public class Worker implements Job {
	private static Logger logger = Logger.getLogger(Worker.class);
	
	public static void main(String[] args) {
        if (args.length != 1) {
	        System.out.println("Usage: java Worker date (format: yyyymmdd)");
	        System.exit(1);
	    }
		 
		if (!WorkerHelper.validateDate(args[0])) {
			logger.error("Date " + args[0] + " is of incorrect format, must be yyyymmdd");
			System.exit(1);
		}
		
        if (!initialize()) {
	        logger.error("Could not initialize hibernate");
	        System.exit(1);
	    }
      
		try {			
			Worker worker = new Worker();
			worker.executeCSV(args[0]);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getCause(), e);
		}
		finally {
			if (!HibernateUtil.getSessionFactory().isClosed())
			    HibernateUtil.shutdown();
		}
	}
	
	public static boolean initialize() throws HibernateException {
	    logger.info("Initializing Hibernate");
        HibernateUtil.getInstance(RTLMPHelper.getHibernateConfiguration());
        return HibernateUtil.test();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			for (JobExecutionContext job : (List<JobExecutionContext>) context.getScheduler().getCurrentlyExecutingJobs()) {
				if (job.getJobDetail().getName().equals(context.getJobDetail().getName()) && !job.getJobInstance().equals(this)) {
					logger.info("Job " + context.getJobDetail().getName() + " is running");
					return;
				}
			}
			
			Date current = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String dateString = format.format(current);
			
			if (!WorkerHelper.validateDate(dateString)) {
				logger.error("Date " + dateString + " is of incorrect format, must be yyyymmdd");
				return;
			}
			
	        if (!initialize()) {
		        logger.error("Could not initialize hibernate");
		        System.exit(1);
		    }
	        
			Worker worker = new Worker();
			worker.executeCSV(dateString);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getCause(), e);
		}		
		finally {
			if (!HibernateUtil.getSessionFactory().isClosed())
			    HibernateUtil.shutdown();
		}
	}
	
	private void executeCSV(final String value) throws Exception {
		if (value == null)
			return;
		
		String dateString = value;
		List<ERCOTFile> filesToDownload = new ArrayList<ERCOTFile>();
		ERCOTFile.FileType fileType = ERCOTFile.FileType.RTLMP;
		String endswith = "csv.zip";
		FileCache.initialize(File.FileType.RTLMP);
		
		logger.info("Retrieving " + dateString + " " + fileType.name());
		CSVSaver saver = new CSVSaver();
		PostWorker post = new PostWorker();
		EventWorker event = new EventWorker();
		filesToDownload = WorkerHelper.getFilesToDownload(fileType.name().toLowerCase(), dateString);
	    Collections.sort(filesToDownload, new ERCOTFileDateComparator());
		if (filesToDownload.size() > 0) {
		    for (ERCOTFile file : filesToDownload) {
		    	if (!file.getName().endsWith(endswith)) 
		    		continue;
		    	if (FileCache.getFiles().contains(file.getName()))
		    		continue;
		    	logger.info("Processing file " + file.getName());
			    file.setContent(WorkerHelper.getContents(file.getUrl()));
			    saver.setFile(file);
			    logger.info("Executing save operations");
			    if (!saver.execute())
			        continue;
			    logger.info("Executing post operations");
				post.setRTDTimestamp(saver.getRTDTimestamp());
				post.execute();
			    logger.info("Executing event operations");
				event.setRTDTimestamp(saver.getRTDTimestamp());
				event.execute();
			    logger.info("Saving file " + file.getName());
				FileFacade.save(file.getName(), file.getUrl(), File.MimeType.CSV, File.FileType.RTLMP);
			}
		}
	}
}
