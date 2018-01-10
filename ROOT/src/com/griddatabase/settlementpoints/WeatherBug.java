package com.griddatabase.settlementpoints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.*;

import com.softwarelikeyou.exception.WeatherException;
import com.softwarelikeyou.util.ResourceUtil;

public class WeatherBug {

	private StringBuffer URI = new StringBuffer("http://i.wxbug.net/REST/SP/getLiveWeatherRSS.aspx");
		
	private static String API_KEY = "?api_key=";
	
	private static String LAT = "&lat=";
	
	private static String LONG = "&long=";
			
	private static String UNIT_TYPE = "&UnitType=0";
			
	private static String OUTPUT_TYPE = "&OutputType=1";
				
	private String latitude;
	
	private String longitude;	
	
	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(final String value) {
		latitude = value;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(final String value) {
		longitude = value;
	}
	
	public String getTemperature() throws WeatherException { 
		HttpURLConnection connection = null;
		BufferedReader br = null;
		try {
			if (latitude == null || longitude == null)
				throw new WeatherException("Latitude or Longitude cannot be empty");
			
			String key = ResourceUtil.get().getString("weather.weatherbug.restful.key");

			URI.append(API_KEY + key);
			URI.append(LAT + getLatitude());
			URI.append(LONG + getLongitude());
			URI.append(UNIT_TYPE);
			URI.append(OUTPUT_TYPE);
						
            URL url = new URL(URI.toString()); 
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); 
            connection.setInstanceFollowRedirects(false); 
            connection.setRequestMethod("GET"); 
            connection.setRequestProperty("Content-Type", "application/xml"); 
            
    		if (connection.getResponseCode() != 200) {
        		br = new BufferedReader(new InputStreamReader((connection.getErrorStream())));
        		StringBuffer response = new StringBuffer();
        		String string;
        	    while ((string = br.readLine()) != null)
        	    	response.append(string);
    			throw new WeatherException("HTTP error code : "+ connection.getResponseCode() + "-" + response.toString() + "-"  + URI.toString());
    		}
    		
    		br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
    	     			
    		StringBuffer response = new StringBuffer();
    		String string;
    	    while ((string = br.readLine()) != null)
    	    	response.append(string);
    	     		       		
    		return parse(response.toString());
		}
		catch (Exception e) {
			throw new WeatherException(e);
		}
		finally {
			if (br != null) {
				try {
				    br.close();
				}
				catch (IOException e) {
					throw new WeatherException(e);
				}
			}
			if (connection != null)
				connection.disconnect();
		}
        
	}
	
	private String parse(final String xml) throws WeatherException {
		String results = "";
		try {
			XPath xPath = XPathFactory.newInstance().newXPath();
			xPath.setNamespaceContext(new AWSNamespaceContext());
			XPathExpression xPathExpression = xPath.compile("aws:weather/aws:ob/aws:feels-like");
			InputSource inputSource = new InputSource(new StringReader(xml));          
			results = xPathExpression.evaluate(inputSource);
		}
		catch (Exception e) {
			throw new WeatherException(e);
		}
		return results;
	}
	
    public class AWSNamespaceContext implements NamespaceContext {
	    public String getNamespaceURI(String prefix) {
	        if (prefix.equals("aws"))
	            return "http://www.aws.com/aws";
	        else
	            return XMLConstants.NULL_NS_URI;
	    }
	        
	    public String getPrefix(String namespace) {
	        if (namespace.equals("http://www.aws.com/aws"))
	            return "aws";
	        else
	            return null;
	    }

	    public Iterator<?> getPrefixes(String namespace) {
	        return null;
	    }
	}  
}
