package com.griddatabase.settlementpoints;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.xml.sax.InputSource;

import javax.xml.xpath.*;

import com.softwarelikeyou.exception.WeatherException;

public class NDFD {

	public enum Type {
		LATLONG,
		ZIPCODE;
	}
	
	//http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdXMLclient.php?zipCodeList=77057&product=time-series&begin=2012-08-29T14:00:00&end=2012-08-29T15:00:00&appt=appt
	private StringBuffer URI = new StringBuffer("http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdXMLclient.php");
	
	private static String LAT = "?lat=";
	
	private static String LONG = "&lon=";
			
	private static String PRODUCT = "&product=time-series";
			
	private static String BEGIN = "&begin="; //2004-01-01T00:00:00
			
	private static String END = "&end="; //2013-04-20T00:00:00
			
	private static String TEMP = "&appt=appt";
	
	private static String ZIPCODELIST = "?zipCodeList=";
	
	private String zipCode;
	
	private String latitude;
	
	private String longitude;
	
	private String begin;
	
	private String end;
	
	private Date seed;

	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(final String value) {
		zipCode = value;
	}
	
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
	
	public Date getSeed() {
		return seed;
	}
	
	public void setSeed(final Date value) {
		seed = value;
	}
	
	public String getTemperature(final Type type) throws WeatherException {  
		HttpURLConnection connection = null;
		BufferedReader br = null;
		StringBuffer response = null;
		try {
			if (type == null)
				throw new WeatherException("Type cannot be empty");
			
			DateFormat datePart = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat timePart = new SimpleDateFormat("kk:mm:ss");
			Calendar calStart = new GregorianCalendar();
			Calendar calEnd = new GregorianCalendar();
			
			calStart.setTime(seed);
			calStart.set(Calendar.MINUTE, 0);
			calStart.set(Calendar.SECOND, 0);
			calStart.set(Calendar.MILLISECOND, 0);
			
			calEnd.setTime(calStart.getTime());
			calEnd.add(Calendar.HOUR, 1);
			calEnd.set(Calendar.MINUTE, 0);
			calEnd.set(Calendar.SECOND, 0);
			calEnd.set(Calendar.MILLISECOND, 0);
			
			begin = datePart.format(calStart.getTime()) + "T" + timePart.format(calStart.getTime());
			end = datePart.format(calEnd.getTime()) + "T" + timePart.format(calEnd.getTime());
			
			if (type.equals(Type.LATLONG)) {
			    if (latitude == null || longitude == null || seed == null)
				    throw new WeatherException("Latitude, Longitude, or date cannot be empty");
				URI.append(LAT + getLatitude() + LONG + getLongitude() + PRODUCT + BEGIN + begin + END + end + TEMP);
			}
			
			if (type.equals(Type.ZIPCODE)) {
			    if (zipCode == null || seed == null)
				    throw new WeatherException("Zip code or date cannot be empty");
				URI.append(ZIPCODELIST+ getZipCode() + PRODUCT + BEGIN + begin + END + end + TEMP);
			}
			
            URL url = new URL(URI.toString()); 
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); 
            connection.setInstanceFollowRedirects(false); 
            connection.setRequestMethod("GET"); 
            connection.setRequestProperty("Content-Type", "application/xml"); 
            
    		if (connection.getResponseCode() != 200) {
        		br = new BufferedReader(new InputStreamReader((connection.getErrorStream())));
        		response = new StringBuffer();
        		String string;
        	    while ((string = br.readLine()) != null)
        	    	response.append(string);
        		connection.disconnect();
    			throw new WeatherException("HTTP error code : "+ connection.getResponseCode() + "-" + response.toString() + "-"  + URI.toString());
    		}
    		
    		br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
    	     			
    		response = new StringBuffer();
    		String string;
    	    while ((string = br.readLine()) != null)
    	    	response.append(string);
    	 
    		connection.disconnect();
    		        		
    		return parse(response.toString());
    			
		}
		catch (Exception e) {
			throw new WeatherException(e);
		}
		finally {
			connection = null;
			br = null;
			response = null;
		}
        
	}
	
	private String parse(final String xml) throws WeatherException {
		String results = "";
		XPath xPath = null;
		XPathExpression xPathExpression = null;
		InputSource inputSource = null;
		try {
			xPath = XPathFactory.newInstance().newXPath();
			xPathExpression = xPath.compile("/dwml/data/parameters/temperature/value");
			inputSource = new InputSource(new StringReader(xml));          
			results = xPathExpression.evaluate(inputSource);
		}
		catch (Exception e) {
			throw new WeatherException(e);
		}
		finally {
		    xPath = null;
			xPathExpression = null;
		    inputSource = null;
		}
		return results;
	}
}
