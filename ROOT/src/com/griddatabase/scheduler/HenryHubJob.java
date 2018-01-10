package com.griddatabase.scheduler;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.softwarelikeyou.facade.HenryHubFacade;
import com.softwarelikeyou.model.entity.HenryHub;

public class HenryHubJob implements Job {

	private static Logger logger = Logger.getLogger(HenryHubJob.class);

	private static String xls = "http://www.eia.gov/dnav/ng/hist_xls/RNGWHHDd.xls";

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
			
		    HttpURLConnection connection = null;
	 		POIFSFileSystem fileSystem = null;
	 		
	 		URL url = new URL(xls);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput (true);
			connection.setDoOutput (true);
			connection.setRequestProperty ("Content-Type","application/vnd.ms-excel");
			
		    fileSystem = new POIFSFileSystem(connection.getInputStream());
		      
		    HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
		      
		    HSSFSheet sheet = workBook.getSheetAt(1);
		      
		    for (int row = sheet.getFirstRowNum() + 3; row <= sheet.getLastRowNum(); row++) {
		    	try {
		            Date date = sheet.getRow(row).getCell(0).getDateCellValue();
		            if (date == null)
		            	continue;
		    	    
		            Float price = (float) sheet.getRow(row).getCell(1).getNumericCellValue();
		    	    if (price == null)
		    	    	continue;
		    	    
		            HenryHub hh = new HenryHub();
		    	    hh.setDate(date);
		    	    hh.setPrice(price);
		            HenryHubFacade.createOrUpdate(hh);
		    	}
		    	catch (Exception e) {
					logger.error(e.getCause(), e);
		    	}
		    }
			logger.info("Updated Henry Hub Prices");
		}
		catch (Exception e) {
			logger.error(e.getCause(), e);
		}
	}
}
