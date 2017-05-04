package com.griddatabase.bot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownServiceException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.html.parser.ParserDelegator;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.griddatabase.bot.exception.FileException;
import com.griddatabase.bot.util.FileUtil;

public class Worker {
	private static Logger logger = Logger.getLogger(Worker.class);
		
	private static Map<String, String> map = new HashMap<String, String>();
	
	static {		
		map.put("ascpc", "http://mis.ercot.com/misapp/GetReports.do?reportTypeId=12329&reportTitle=DAM%20Clearing%20Prices%20for%20Capacity&showHTMLView=&mimicKey");
		map.put("rtspp", "http://mis.ercot.com/misapp/GetReports.do?reportTypeId=12301&reportTitle=Settlement%20Point%20Prices%20at%20Resource%20Nodes,%20Hubs%20and%20Load%20Zones&showHTMLView=&mimicKey");
		map.put("rtdam", "http://mis.ercot.com/misapp/GetReports.do?reportTypeId=12331&reportTitle=DAM%20Settlement%20Point%20Prices&showHTMLView=&mimicKey");
		map.put("rtlmp", "http://mis.ercot.com/misapp/GetReports.do?reportTypeId=13073&reportTitle=LMPs%20by%20Resource%20Nodes,%20Load%20Zones%20and%20Trading%20Hubs&showHTMLView=&mimicKey");
        map.put("h48damhp", "http://mis.ercot.com/misapp/GetReports.do?reportTypeId=13018&reportTitle=48-Hour%20Highest%20Price%20AS%20Offer%20Selected&showHTMLView=&mimicKey");
        map.put("h48damas", "http://mis.ercot.com/misapp/GetReports.do?reportTypeId=13057&reportTitle=48%20Hour%20Ancillary%20Services%20Reports&showHTMLView=&mimicKey");					
        map.put("sysdemand", "http://mis.ercot.com/misapp/GetReports.do?reportTypeId=12340&reportTitle=System-Wide%20Demand&showHTMLView=&mimicKey");
	}
	
	public static void main(String[] args) {
        if (args.length != 1) {
        	logger.error("USAGE: java Worker type");
	        System.out.println("USAGE: java Worker type");
	        System.exit(1);
	    }
        if (!FileUtil.getOSType().equals(FileUtil.OSType.LINUX)) {
        	logger.info("ERROR: The current supported OS is Linux");
	        System.out.println("ERROR: The current supported OS is Linux");
	        System.exit(1);
        }
        
		String key = args[0];
		
		if (key == null) {
			logger.error("ERROR: File type cannot be null");
	        System.out.println("ERROR: File type cannot be null");
	        System.exit(1);
		}
		
		if (map.get(key) == null) {
			logger.error("ERROR: Unable to find URL for " + key);
	        System.out.println("ERROR: Unable to find URL for " + key);
	        System.exit(1);
		}
		
		try {
		    File root = new File(FileUtil.BASE_DIR);
		    if (!root.exists())
			    root.mkdir();
		}
		catch (SecurityException e) {
			logger.error("ERROR: " + FileUtil.BASE_DIR + " base directory does not exist or cannot be created", e);
	        System.exit(1);
		}
		
		logger.info("Retrieving files for " + key);
		System.out.println("Retrieving files for " + key);
		String link = map.get(key);
		URL url = null;
		URLConnection connection = null;
		InputStream is = null;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
		
		try {
		    url = new URL(link);
		}
		catch (MalformedURLException e) {
			logger.error("ERROR: Malformed URL " + link, e);
			System.out.println("ERROR: Malformed URL " + link);
			System.exit(1);
		}
		
		try {
	        connection = url.openConnection();
		}
		catch (IOException e) {
			logger.error("ERROR: Could not access EROCT website " + link, e);
		    System.exit(1);
		}
		
		try {
			is = connection.getInputStream();
			if (is == null) {
				logger.error("ERROR: Could not retrieve data from website " + link);
			    System.out.println("ERROR: Could not retrieve data from website " + link);	
			    System.exit(1);
			}
		}
		catch (UnknownServiceException e) {
			logger.error("ERROR: Could not retrieve data from website " + link, e);
		    System.out.println("ERROR: Could not retrieve data from website " + link);	
		    System.exit(1);
		}
		catch (IOException e) {
			logger.error("ERROR: Could not access EROCT website " + link, e);
		    System.out.println("ERROR: Could not access EROCT website " + link);	
		    System.exit(1);
		}

	    Callback callback = new Callback(); 
        InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
	    ParserDelegator parser = new ParserDelegator();
	    
		try {
			parser.parse(br, callback, false); 
		}
		catch (IOException e) {
			logger.error("ERROR: Could not read data", e);
		    System.out.println("ERROR: Could not read data");	
		    System.exit(1);
		}
			
		if (callback.getFilesToDownload().size() == 0) {
			logger.info("No file found");
		    System.exit(0);
		}
		
		Collections.reverse(callback.getFilesToDownload());
			
		try {
	        for (Map<String, String> map : callback.getFilesToDownload()) {
	        	Date date = null;
	        	
	        	try {
					date = dateFormat.parse(FileUtil.getDate(map.get("name")));
	        	}
	        	catch (FileException e) {
	        		logger.error("ERROR: Could not parse file date, placing file to TODO bucket", e);
	        	}
	        	catch (ParseException e) {
	        		logger.error("ERROR: Could not parse file date, placing file to TODO bucket", e);
	        	}
	        	
	        	String directory = null;
	        	
	        	if (date == null) {
	        		directory = FileUtil.BASE_DIR + key + "/TODO";
	        	}
	        	else {
	        		String year = yearFormat.format(date);
	        		String month = monthFormat.format(date);
	        		directory = FileUtil.BASE_DIR + key + "/" + year + "/" + month;
	        	}
	        	
	        	try {
	        	    File dir = new File(directory);
                    if (!dir.exists())
                        dir.mkdirs();
	        	}
	        	catch (SecurityException e) {
	    			logger.error("ERROR: Can not read file system", e);
	    	    }
	        	
                String fileName = directory + "/" + map.get("name") + ".zip";
                
                try {
                    File file = new File(fileName);
                    if (!file.exists()) {
            	        FileOutputStream writer = new FileOutputStream(file);
            		    writer.write(getContents(map.get("url")));
            		    writer.close();
            		    logger.info("Saving " + fileName);
                    }
                }
                catch (SecurityException e) {
	    			logger.error("ERROR: Can not read file system", e);
                }
                catch (FileNotFoundException e) {
	    			logger.error("ERROR: File " + fileName + " cannot be created", e);
                }
                catch (IOException e) {
	    			logger.error("ERROR: File " + fileName + " cannot write contents", e);
                }
	        }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static byte[] getContents(final String link) throws IOException {
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
