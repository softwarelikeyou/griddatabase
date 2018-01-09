package com.softwarelikeyou.test;

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

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import junit.framework.TestCase;

public class H48DAMASTestCase extends TestCase {

	static final int BUFFER = 2048;
	
	public void testWorker() {
		try {
			for (Map<String, String> map : getFilesToDownload()) {
			    for (Map<String, String> entry : getContents(map.get("url"))) {
					for (String key : entry.keySet()) {
						System.out.println(key);
					    System.out.println(entry.get(key));
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<Map<String, String>> getFilesToDownload() throws IOException, XPathExpressionException {
		List<Map<String, String>> results = new ArrayList<Map<String, String>>();
		
        XPathFactory xFactor = XPathFactory.newInstance();
	    XPath xPath = xFactor.newXPath();
         
	    URL url = new URL("http://192.168.0.201/ercot-file-collector/download?type=h48damas");
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
			
        NodeList places = (NodeList) xPath.evaluate("/" + "h48damas" + "/file", inputSource, XPathConstants.NODESET);

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
