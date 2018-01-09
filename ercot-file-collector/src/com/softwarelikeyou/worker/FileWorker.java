package com.softwarelikeyou.worker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.parser.ParserDelegator;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.softwarelikeyou.util.FileUtil;
import com.softwarelikeyou.util.ResourceUtil;

public class FileWorker implements Job {
	private static Logger logger = Logger.getLogger(FileWorker.class);
	private String key = null;
	private List<Map<String, String>> filesToDownload = new ArrayList<Map<String, String>>();
    private SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");

	public FileWorker() { }
	
	@SuppressWarnings("unchecked")
	public void execute(JobExecutionContext context) throws JobExecutionException {
		key = context.getJobDetail().getName();
		String link = null;
		URL url = null;
		URLConnection connection = null;
		InputStream is = null;
		try {
			for (JobExecutionContext job : (List<JobExecutionContext>) context.getScheduler().getCurrentlyExecutingJobs()) {
				if (job.getJobDetail().getName().equals(context.getJobDetail().getName()) && !job.getJobInstance().equals(this)) {
					logger.info("Job " + context.getJobDetail().getName() + " is running");
					return;
				}
			}
			
			logger.info("Retrieving files for " + key);
			link = ResourceUtil.get().getString(key + ".url");
			
			url = new URL(link);
		    try {
			    connection = url.openConnection();
				is = connection.getInputStream();
		    }
		    catch (IOException e) {
		        logger.error("Could not access EROCT website " + key);		    
		    }
			if (is == null) 
			    return;

			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
		    ParserDelegator parser = new ParserDelegator();
		    Callback callback = new Callback();  
			parser.parse(br, callback, false); 
			
			Collections.reverse(filesToDownload);
			
			if (filesToDownload.size() > 0) {
			    for (Map<String, String> map : filesToDownload) {
					String date = FileUtil.getDate(map.get("name"));
					if (date.length() != 8)
						date = format.format(new Date());
					
                    String directoryString = FileUtil.getRootString() + FileUtil.getOSType().getSlash() + key + FileUtil.getOSType().getSlash() + date;
                    File directory = new File(directoryString);
                    if (!directory.exists())
                    	directory.mkdirs();
                    
                    String fileName = directoryString + FileUtil.getOSType().getSlash() + map.get("name") + ".zip";
                    File file = new File(fileName);
                    if (!file.exists()) {
            	    	FileOutputStream writer = new FileOutputStream(file);
            		    writer.write(getContents(map.get("url")));
            		    writer.close();
            		    logger.info("Saving " + fileName);
                    }
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public class Callback extends ParserCallback {
		private Integer tableCount = 0;
		private Integer rowCount = 0;
		private String fileName;
		private String href;
		
		public void handleText(char[] data, int pos) {  
			if (tableCount == 3) {
		        String value = new String(data).trim();
		        if (rowCount == 1)
		            fileName = value;
			}
	    }  
			  
		public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos ){  
			if (t.toString().toLowerCase().equals(HTML.Tag.TABLE.toString().toLowerCase()))
				tableCount++;
			
			if (tableCount == 3) {
				if (t.toString().toLowerCase().equals(HTML.Tag.TR.toString().toLowerCase())) {
					rowCount = 0;
					fileName = "";
				}
				if (t.toString().toLowerCase().equals(HTML.Tag.TD.toString().toLowerCase()))
					rowCount++;
				if (rowCount == 4) {
					if (t.toString().toLowerCase().equals(HTML.Tag.A.toString().toLowerCase())) {
					    href = (String)a.getAttribute(HTML.Attribute.HREF);
					    if (fileName != null) {
					    	Map<String, String> result = new HashMap<String, String>();
		 			        result.put("name", fileName);
		 			        result.put("url", ResourceUtil.get().getString("ercot.base.url") + href);
		 			        filesToDownload.add(result);
					    }
					}
				}
			}
	    }
	}

	public byte[] getContents(final String link) throws IOException {
		byte[] results = null;
		URLConnection connection = null;
		InputStream is = null;
        URL url = null;
	    url = new URL(link);
	    connection = url.openConnection();
	    is = connection.getInputStream();
		if (is != null) 
		    results = IOUtils.toByteArray(is);
        return results;
	}
}
