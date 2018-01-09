package com.softwarelikeyou.collector.worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.softwarelikeyou.collector.saver.Post;
import com.softwarelikeyou.collector.saver.Saver;
import com.softwarelikeyou.facade.FileFacade;
import com.softwarelikeyou.model.entity.File;
import com.softwarelikeyou.model.entity.File.FileType;
import com.softwarelikeyou.util.ResourceUtil;
import com.softwarelikeyou.util.ZipUtil;

public class GeneralFileWorker implements Job {
	private static Logger logger = Logger.getLogger(GeneralFileWorker.class);
		
	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		List<Map<String, String>> filesToDownload = new ArrayList<Map<String, String>>();
		String key = context.getJobDetail().getName();
		String type = null;
		String format = null;
		String endswith = null;
		try {
			for (JobExecutionContext job : (List<JobExecutionContext>) context.getScheduler().getCurrentlyExecutingJobs()) {
				if (job.getJobDetail().getName().equals(context.getJobDetail().getName()) && !job.getJobInstance().equals(this)) {
					logger.info("Job " + context.getJobDetail().getName() + " is running");
					return;
				}
			}
			
			logger.info("Retrieving files for " + key);
	        type = ResourceUtil.get().getString(key + ".name");
			format = ResourceUtil.get().getString(key + ".type");
			endswith = ResourceUtil.get().getString(key + ".endswith");
	    	
			Class<?> saverClass = Class.forName(String.valueOf(ResourceUtil.get().getString(key + "." + format + ".saver")));
			Constructor<?> saverConstructor = saverClass.getConstructor();	
			Saver saver = (Saver) saverConstructor.newInstance();
			
	    	Class<?> postClass = Class.forName(String.valueOf(ResourceUtil.get().getString(key + ".saver.post")));
			Constructor<?> postConstructor = postClass.getConstructor();	
			Post post = (Post) postConstructor.newInstance();
			
			filesToDownload = getFilesToDownload(key);
			
			Collections.reverse(filesToDownload);

			if (filesToDownload.size() > 0) {
				List<String> existing = FileFacade.findByType(FileType.valueOf(type));
				if (existing == null)
					existing = new ArrayList<String>();
			    for (Map<String, String> map : filesToDownload) {
			    	if (!map.get("name").endsWith(endswith)) 
			    		continue;
			    	logger.info("Looking for file " + map.get("name"));
			    	if (!existing.contains(map.get("name"))) {
				        map.put("contents", getContents(map.get("url")));
				        saver.setMap(map);
				        if (!saver.execute())
				        	continue;
						logger.info("Executing post operations for " + type);
					    post.execute();
						logger.info("Saving " + type + " file " + map.get("name"));
				    	File file = new File();
				    	file.setName(map.get("name"));
						file.setDownloaded(new Date());
						file.setUrl(map.get("url"));
						file.setMimeType(File.MimeType.get(format.toUpperCase()));
						file.setType(FileType.get(type));
						file = FileFacade.createOrUpdate(file);
					}
				}
			}
		}
		catch (Exception e) {
			logger.error(e);
		}
	}

	private List<Map<String, String>> getFilesToDownload(final String key) throws IOException, XPathExpressionException {
		List<Map<String, String>> results = new ArrayList<Map<String, String>>();
		
        XPathFactory xFactor = XPathFactory.newInstance();
	    XPath xPath = xFactor.newXPath();
         
	    URL url = new URL(ResourceUtil.get().getString("collector.base.file.url") + key);
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true); 
        connection.setInstanceFollowRedirects(false); 
        connection.setRequestMethod("GET"); 
        connection.setRequestProperty("Content-Type", "application/xml"); 
        if (connection.getResponseCode() != 200)
 	        throw new IOException("HTTP error code : "+ connection.getResponseCode());
         
        BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
        StringBuffer response = new StringBuffer();
 	    String string;
 	    while ((string = br.readLine()) != null)
 	        response.append(string);
 	 
        connection.disconnect();
         
		InputSource inputSource = new InputSource(new StringReader(response.toString()));  
			
        NodeList places = (NodeList) xPath.evaluate("/" + key + "/file", inputSource, XPathConstants.NODESET);

        for (int i = 0; i < places.getLength(); i++) {
            Node place = places.item(i);
            String name = ((Node)xPath.evaluate("name", place, XPathConstants.NODE)).getTextContent();
            String href = ((Node)xPath.evaluate("href", place, XPathConstants.NODE)).getTextContent();
			if (name != null) {
			    Map<String, String> result = new HashMap<String, String>();
			    result.put("name", name);
			    result.put("url", href);
			    results.add(result);
			}
        }
        Collections.reverse(results);
		return results;
	}
	
	private String getContents(final String link) throws IOException {
		String results = null;
		URLConnection connection = null;
		InputStream is = null;
        URL url = null;
	    url = new URL(link);
	    connection = url.openConnection();
	    is = connection.getInputStream();
		if (is != null) 
		    results = new String(ZipUtil.unzip(is));
        return results;
	}
}
