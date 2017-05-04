package com.softwarelikeyou.analyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.softwarelikeyou.exception.FileException;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.softwarelikeyou.pojo.ERCOTFile;
import com.softwarelikeyou.util.ResourceUtil;
import com.softwarelikeyou.util.ZipUtil;

public class WorkerHelper {
	
	public static List<ERCOTFile> getFilesToDownload(final String key, final String date) throws IOException, XPathExpressionException, FileException {
        List<ERCOTFile> files = new ArrayList<ERCOTFile>();
		
        XPathFactory xFactor = XPathFactory.newInstance();
	    XPath xPath = xFactor.newXPath();
         
	    URL url = new URL(ResourceUtil.get().getString("download.url") + key + "&date=" + date);
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
		        ERCOTFile file = new ERCOTFile();
		        file.setName(name);
		        file.setUrl(href);
		        file.setDate(ERCOTFile.getTimeStamp(ERCOTFile.FileType.valueOf(key.toUpperCase()),name));
		        files.add(file);
			}
        }
		return files;
	}
	
	public static String getContents(final String link) throws IOException {
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
	
	public static boolean validateDate(final String date) {
		boolean results = true;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
			format.parse(date);
		}
		catch (ParseException e) {
			results = false;
		}
		return results;
	}
	
	public static boolean validateType(final String type) {
		boolean results = true;
		try {
	        SettlementPoint.Type.valueOf(type);
		}
		catch (IllegalArgumentException e) {
			results = false;
		}
		return results;
	}
	
	public static DecimalFormat form = new DecimalFormat("#####.##");

	public static Float toFloat(final String value) throws NumberFormatException {
		Float result = new Float(form.format(0.00f));
		result = Float.valueOf(value);
		return result;
	}
	
	public static float formatFloat(final float value) throws NumberFormatException {
		return toFloat(form.format(value));
	}
}
