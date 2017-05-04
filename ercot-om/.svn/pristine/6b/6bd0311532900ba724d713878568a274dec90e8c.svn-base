package com.softwarelikeyou.analyzer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.softwarelikeyou.util.ResourceUtil;

public class RemoteEventFacade {

	private RemoteEventFacade() { }
	
	public static void firePriceThreshold(final String type, final String content) throws Exception {
		if (type == null)
			throw new Exception("Type cannot be empty");
		
		switch (type) {
		    case "RTLMP":
		    	System.out.println("RTLMP Price Threshold Event");
		    	postEvent(type, content);
		    	break;
		    case "RTSPP":
		    	System.out.println("RTSPP Price Threshold Event");
		    	postEvent(type, content);
		    	break;		    
		    default:
			    break;
		}
	}
	
	 private static void postEvent(final String type, final String content) throws Exception {
		 if (type == null || content == null)
			 throw new Exception("Type or content cannot be empty");
		 
		 StringBuffer parameters = new StringBuffer();
		 parameters.append("type=" + type);
		 parameters.append("&content=" + content);
		 
         HttpURLConnection connection = null;  
		 try {
	         URL url;
	         url = new URL(ResourceUtil.get().getString("remote-event-url"));
	         connection = (HttpURLConnection)url.openConnection();
	         connection.setConnectTimeout(1000 * 5);
	         connection.setReadTimeout(1000 * 5);
	         connection.setRequestMethod("POST");
	         connection.setRequestProperty("Content-Type", "text/csv");
	         connection.setRequestProperty("Content-Length", "" + parameters.toString().getBytes().length);
	         connection.setRequestProperty("Content-Language", "en-US");  
		     connection.setUseCaches (false);
	         connection.setDoInput(true);
	         connection.setDoOutput(true);

	         DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
	         wr.writeBytes(parameters.toString());
	         wr.flush ();
	         wr.close ();

	         if (connection.getResponseCode() != 200)
	  	        throw new IOException("HTTP error code : "+ connection.getResponseCode());
	         
	         BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			 StringBuffer response = new StringBuffer();
	  	     String string;
	  	     while ((string = br.readLine()) != null)
	  	         response.append(string);
	         connection.disconnect();
	 		 System.out.println(response.toString());  
	     } 
         finally {
             if(connection != null)
	             connection.disconnect(); 
	     }
	 }
	 
	 public void sendEmail() {
		 
	 }
}
