 package com.softwarelikeyou.collector.worker;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

import com.softwarelikeyou.collector.saver.Saver;
import com.softwarelikeyou.collector.saver.csv.H48DAMASCSVSaver;
import com.softwarelikeyou.facade.FileFacade;
import com.softwarelikeyou.model.entity.File.FileType;
import com.softwarelikeyou.util.ResourceUtil;

public class GeneralFileWorker implements Job {
	private static Logger logger = Logger.getLogger(GeneralFileWorker.class);
		
	static final int BUFFER = 2048;

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
			
			String name = context.getJobDetail().getName();
			logger.info("Retrieving files for " + name);
			String type = null;
			String endswith = null;
			String format = null;
			List<Map<String, String>> agg = new ArrayList<Map<String, String>>();
			List<Map<String, String>> cs = new ArrayList<Map<String, String>>();
			
	        type = ResourceUtil.get().getString(name + ".name");
			endswith = ResourceUtil.get().getString(name + ".endswith");
			format = ResourceUtil.get().getString(name + ".format");
			Saver saver = null;
			
			List<String> existing = FileFacade.findByType(FileType.valueOf(type));
			if (existing == null)
				existing = new ArrayList<String>();
			
			List<Map<String, String>> zipFiles = getFilesToDownload(name);
			
			for (Map<String, String> zipFile : zipFiles) {
			    
				logger.info(zipFile.get("url"));
				
				List<Map<String, String>> csvs = getContents(zipFile.get("url"));
				
				for (Map<String, String> csv : csvs) {
					
					for (String fileName : csv.keySet()) {
						if (!fileName.endsWith(endswith)) 
				    		continue;
				    	if (!existing.contains(fileName)) {
					    	Map<String, String> h48damas = new HashMap<String, String>();
					    	h48damas.put("fileName", fileName);
				    		h48damas.put("contents", csv.get(fileName));
				    		h48damas.put("url", zipFile.get("url"));
				    		if (fileName.startsWith("48h_Agg"))
				    			agg.add(h48damas);
				    		if (fileName.startsWith("48h_Cleared") || fileName.startsWith("48h_Self"))
				    			cs.add(h48damas);
				    	}
					}
				}
			}
			
			if (format.equals("csv"))
				saver = new H48DAMASCSVSaver();
			
			if (!saver.saveAG(agg))
				logger.error("Could not save H48DAMAS AG list");
			else
				; // post.execute
			if (!saver.saveCS(cs))
				logger.error("Cound not save H48DAMAS CS list");
			else
				; // post.execute
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Map<String, String>> getFilesToDownload(String type) throws IOException, XPathExpressionException {
		List<Map<String, String>> results = new ArrayList<Map<String, String>>();
		
        XPathFactory xFactor = XPathFactory.newInstance();
	    XPath xPath = xFactor.newXPath();
         
	    URL url = new URL(ResourceUtil.get().getString("collector.base.file.url") + type);
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
			
        NodeList places = (NodeList) xPath.evaluate("/" + type + "/file", inputSource, XPathConstants.NODESET);

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
	
	private List<Map<String, String>> getContents(final String link) throws IOException {
		List<Map<String, String>> results = new ArrayList<Map<String, String>>();
		if (link == null)
			return results;
		URLConnection connection = null;
		ZipInputStream zis = null;
        URL url = null;
	    url = new URL(link);
	    connection = url.openConnection();
	    zis = new ZipInputStream(connection.getInputStream());
		ZipEntry ze = zis.getNextEntry();
		while (ze != null) {
			Map<String, String> map = new HashMap<String, String>();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int count;
            byte data[] = new byte[BUFFER];
			BufferedOutputStream dest = new BufferedOutputStream(out, BUFFER);
			while ((count = zis.read(data, 0, BUFFER)) != -1)
		        dest.write(data, 0, count);
		    dest.flush();
		    dest.close();
			out.flush();
			out.close();
			map.put(ze.getName(), new String(out.toByteArray()));
			results.add(map);
			ze = zis.getNextEntry();
		}
        zis.closeEntry();
    	zis.close();
        return results;
	}
}
