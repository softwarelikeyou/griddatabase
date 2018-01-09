package com.softwarelikeyou;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import junit.framework.TestCase;

public class TransformTestCase extends TestCase {
	
	private List<Map<String, String>> filesToDownload = new ArrayList<Map<String, String>>();
	
	public void testHTTPGet() {
	    try {
            XPathFactory xFactor = XPathFactory.newInstance();
	        XPath xPath = xFactor.newXPath();
            
			URL url = new URL("http://localhost:8080/ercot-file-collector/download?type=rtlmp");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); 
            connection.setInstanceFollowRedirects(false); 
            connection.setRequestMethod("GET"); 
            connection.setRequestProperty("Content-Type", "application/kml"); 
            if (connection.getResponseCode() != 200) {
    	        System.out.println("HTTP error code : "+ connection.getResponseCode());
    	        return;
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
 			
            StringBuffer response = new StringBuffer();
    	    String string;
    	    while ((string = br.readLine()) != null)
    	        response.append(string);
    	 
            connection.disconnect();
            
		    InputSource inputSource = new InputSource(new StringReader(response.toString()));  
			
            NodeList places = (NodeList) xPath.evaluate("/rtlmp/file", inputSource, XPathConstants.NODESET);
  
            for (int i = 0; i < places.getLength(); i++) {
            	Node place = places.item(i);
                String name = ((Node)xPath.evaluate("name", place, XPathConstants.NODE)).getTextContent();
                String href = ((Node)xPath.evaluate("href", place, XPathConstants.NODE)).getTextContent();
                System.out.println(name + "-" + href);
			    if (name != null) {
			    	Map<String, String> result = new HashMap<String, String>();
 			        result.put("name", name);
 			        result.put("url", href);
 			        filesToDownload.add(result);
			    }
            }
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }


	}
}
