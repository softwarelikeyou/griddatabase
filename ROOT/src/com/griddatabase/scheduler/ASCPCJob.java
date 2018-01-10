package com.griddatabase.scheduler;

import java.io.ByteArrayInputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.griddatabase.analyzer.ascpc.CSVSaver;
import com.griddatabase.analyzer.ascpc.DailyWorker;
import com.griddatabase.analyzer.ascpc.PostWorker;
import com.griddatabase.util.ResourceUtil;
import com.softwarelikeyou.cache.FileCache;
import com.softwarelikeyou.facade.FileFacade;
import com.softwarelikeyou.model.entity.File;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;
import com.softwarelikeyou.model.entity.ascpc.ASCPCDaily;
import com.softwarelikeyou.pojo.ERCOTFile;
import com.softwarelikeyou.util.HibernateUtil;
import com.softwarelikeyou.util.Util;
import com.softwarelikeyou.util.ZipUtil;

public class ASCPCJob implements Job {

	private static Logger logger = Logger.getLogger(ASCPCJob.class);
	
	private static List<ERCOTFile> filesToDownload = new ArrayList<ERCOTFile>();
	private static ERCOTFile.FileType fileType = ERCOTFile.FileType.ASCPC;
	
	private static String directoryHome = "/home/ercot/data/ascpc";
	
	private static String directory;
	
    private static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private static SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	static {
		if (ResourceUtil.get().getString("ascpc.watch.dir") != null)
			directoryHome = ResourceUtil.get().getString("ascpc.watch.dir");
	}
	
	public static void main(String[] args) {
        if (args.length != 1) {
	        System.out.println("Usage: java ASCPCJob yyyyMMdd");
	        System.exit(1);
	    }
        
		String date = args[0];

		if (!validateDate(date)) {
			System.out.println("Date " + date + " is of incorrect format, must be yyyyMMdd");
			System.exit(1);
		}
		
        try {
        	hibernate();
			FileCache.initialize(File.FileType.ASCPC);
			logger.info("Retrieving " + fileType.name());
			CSVSaver saver = new CSVSaver();
			PostWorker post = new PostWorker();
			DailyWorker daily = new DailyWorker();
			filesToDownload = getFilesToDownload(date);
			if (filesToDownload.size() > 0) {
			    for (ERCOTFile file : filesToDownload) {
			    	System.out.println("Processing file " + file.getName());
				    System.out.println("Executing save operations");
				    saver.setFile(file);
				    if (!saver.execute()) continue;
				    System.out.println("Executing post operations");
				    post.setIntervalDate(saver.getIntervalDate());
					post.execute();
				    System.out.println("Executing daily operations");
					daily.setIntervalDate(saver.getIntervalDate());
					daily.execute();
				    System.out.println("Saving file " + file.getName());
					FileFacade.save(file.getName(), file.getUrl(), File.MimeType.CSV, File.FileType.ASCPC);
			    }
			}
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
        	HibernateUtil.shutdown();
        }
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
			
			FileCache.initialize(File.FileType.ASCPC);

			logger.info("Retrieving " + fileType.name());
			CSVSaver saver = new CSVSaver();
			PostWorker post = new PostWorker();
			DailyWorker daily = new DailyWorker();
			filesToDownload = getFilesToDownload();
			if (filesToDownload.size() > 0) {
			    for (ERCOTFile file : filesToDownload) {
			    	logger.info("Processing file " + file.getName());
				    logger.info("Executing save operations");
				    saver.setFile(file);
				    if (!saver.execute()) continue;
				    logger.info("Executing post operations");
				    post.setIntervalDate(saver.getIntervalDate());
					post.execute();
				    logger.info("Executing daily operations");
					daily.setIntervalDate(saver.getIntervalDate());
					daily.execute();
				    logger.info("Saving file " + file.getName());
					FileFacade.save(file.getName(), file.getUrl(), File.MimeType.CSV, File.FileType.ASCPC);
			    }
			}
		}
		catch (Exception e) {
			logger.error("ASCPC Job", e);
		}
	}

	private List<ERCOTFile> getFilesToDownload() {
		List<ERCOTFile> files = new ArrayList<ERCOTFile>();
		
		try {
			if (directoryHome == null)
				throw new Exception("ASCPC Directory is invalid");
		    directory = directoryHome + "/" + yearFormat.format(new Date()) + "/" + monthFormat.format(new Date());
			Path path = FileSystems.getDefault().getPath(directory);
		    String today = dateFormat.format(new Date());
			DirectoryStream<Path> ds = Files.newDirectoryStream(path, "*" + today + "*csv.zip");
			for (Path p : ds) {
		    	if (FileCache.getFiles().contains(p.getFileName().toString())) continue;
				String contents = new String(ZipUtil.unzip(new ByteArrayInputStream(Files.readAllBytes(p))));
		        ERCOTFile file = new ERCOTFile();
		        file.setName(p.getFileName().toString());
		        file.setContent(contents);
		        file.setUrl(directory + "/" + p.getFileName().toString());
		        file.setDate(new Date());
		        files.add(file);
			}
		}
		catch (Exception e) {
			logger.error("ASCPC Analyzer", e);
		}
		return files;
	}
	
	private static List<ERCOTFile> getFilesToDownload(final String value) {
		List<ERCOTFile> files = new ArrayList<ERCOTFile>();
		
		try {
			Date date = Util.stringToDate(value, "yyyyMMdd");
			if (directoryHome == null)
				throw new Exception("ASCPC Directory is invalid");
		    directory = directoryHome + "/" + yearFormat.format(date) + "/" + monthFormat.format(date);
			Path path = FileSystems.getDefault().getPath(directory);
		    String today = dateFormat.format(date);
			DirectoryStream<Path> ds = Files.newDirectoryStream(path, "*" + today + "*csv.zip");
			for (Path p : ds) {
		    	if (FileCache.getFiles().contains(p.getFileName().toString())) continue;
				String contents = new String(ZipUtil.unzip(new ByteArrayInputStream(Files.readAllBytes(p))));
		        ERCOTFile file = new ERCOTFile();
		        file.setName(p.getFileName().toString());
		        file.setContent(contents);
		        file.setUrl(directory + "/" + p.getFileName().toString());
		        file.setDate(new Date());
		        files.add(file);
			}
		}
		catch (Exception e) {
			logger.error("ASCPC Analyzer", e);
			e.printStackTrace();
		}
		return files;
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
 	    hibernateConfiguration.addAnnotatedClass(File.class);
 	    hibernateConfiguration.setProperties(hibernateProperties);
     	HibernateUtil.getInstance(hibernateConfiguration);
     	
		Session session = HibernateUtil.getSessionFactory().openSession();
		if (session == null)
			System.out.println("session is null");
		session.close();
	}
}
