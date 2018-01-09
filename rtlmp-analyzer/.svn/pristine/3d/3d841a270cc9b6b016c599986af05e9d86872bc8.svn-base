package com.softwarelikeyou.ercot.analyzer.rtlmp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;

public class EventWorker {
	
	private Date RTDTimestamp;
	
	public void setRTDTimestamp(Date value) {
		RTDTimestamp = value;
	}
	
	public Date getRTDTimestamp() {
		return RTDTimestamp;
	}
	
	public boolean execute() {
		if (RTDTimestamp == null)
			return false;
        HttpURLConnection connection = null;
	    //OutputStreamWriter wr = null;
	    BufferedReader rd  = null;
	    StringBuilder sb = null;
	    String line = null;
	    URL serverAddress = null;
	    
	    try {
	        serverAddress = new URL("http://192.168.0.202/servlet/lmpUpdate?RTDTimestamp=" + RTDTimestamp.getTime());	        
	          
	        connection = (HttpURLConnection)serverAddress.openConnection();      
	        connection.setRequestMethod("GET");
	        connection.setDoOutput(true);
	        connection.setReadTimeout(10000);
	        connection.connect();
	        
	        //get the output stream writer and write the output to the server
	        //not needed here since we are not POSTING
	        //wr = new OutputStreamWriter(connection.getOutputStream());
	        //wr.write("");
	        //wr.flush();
	        
	        rd  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        sb = new StringBuilder();
	        
	        while ((line = rd.readLine()) != null)
	            sb.append(line + '\n');
	        
	        if (sb.toString().contains("Error"))
	        	return false;	        
	    } 
	    catch (MalformedURLException e) {
	        e.printStackTrace();
	        return false;	        
	    } 
	    catch (ProtocolException e) {
	        e.printStackTrace();
            return false;	        
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	    finally {
	        connection.disconnect();
	        rd = null;
	        sb = null;
	        //wr = null;
	        connection = null;
	   }
        
	   return true;                   
	}
}
