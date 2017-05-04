package com.softwarelikeyou.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.PatternSyntaxException;

import com.softwarelikeyou.exception.FileException;
import com.softwarelikeyou.model.entity.File.FileType;

public class FileUtil {

	private static FileUtil instance = new FileUtil();
	
	private FileUtil() { }
	
	private static OSType os = OSType.MAC;
	
	public static OSType getOSType() {
		return os;
	}
	
	private static String rootString;
	
	public static String getRootString() {
		return rootString;
	}
	
	static {
		String osString = System.getProperty("os.name").toLowerCase();
		if (osString.indexOf("win") >= 0)
			os = OSType.WINDOWS;
		if (osString.indexOf("mac") >= 0)
			os = OSType.MAC;
		if (osString.indexOf("nix") >= 0 || osString.indexOf("nux") >= 0)
			os = OSType.LINUX;
		
		rootString = ResourceUtil.get().getString("ercot." + os.getValue());
		
		File root = new File(rootString);
		if (!root.exists())
			root.mkdir();
	}
	
	public static void initialize() {
		if (instance == null)
			instance = new FileUtil();
	}
	
	public enum OSType {
		MAC("MAC", "/"),
		WINDOWS("WINDOWS", "\\"),
		LINUX("LINUX", "/");
		
		private String value;
		private String slash;
		
		private OSType(String value, String slash) {
			this.value = value;
			this.slash = slash;
		}
		
		public String getValue() {
			return value;
		}
		
		public String getSlash() {
			return slash;
		}
		
		public static OSType fromString(String value) {
			if (value == null)
				return null;
			
			for (OSType type : OSType.values()) {
				if (type.toString().equals(value))
					return type;
			}
			
			return null;
		}
	}
	
	public static String archive(FileType type, String name) throws FileException {
		if (type == null)
			throw new FileException("File type cannot be empty");
		if (name == null)
			throw new FileException("File name cannot be empty");
		
		try {
			StringBuilder urlString = new StringBuilder(ResourceUtil.get().getString("archive.base.url"));
	        urlString.append("?type=" + type.name().toLowerCase());
	        urlString.append("&file=" + name);
	        System.out.println(urlString.toString());
			URL url = new URL(urlString.toString());
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        connection.setDoOutput(true); 
	        connection.setInstanceFollowRedirects(false); 
	        if (connection.getResponseCode() != 200)
	 	        throw new FileException("HTTP error code : "+ connection.getResponseCode());
	         
	        BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
				
	        StringBuffer response = new StringBuffer();
	 	    String string;
	 	    while ((string = br.readLine()) != null)
	 	        response.append(string);
	 	    
	        connection.disconnect();
	        
	        return response.toString();
		}
		catch (MalformedURLException e) {
			throw new FileException(e);
		}
		catch (IOException e) {
			throw new FileException(e);
		}
	}
	
	public static String getDate(String name) throws FileException {
		String result = null;
		try {
			String firstPass = null;
			if (name.startsWith("cdr"))
		        firstPass = name.replaceAll("^cdr\\.", "");
			else
		        firstPass = name.replaceAll("^ext\\.", "");
		    String secondPass = firstPass.replaceAll("^\\d+\\.", "");
		    String thirdPass = secondPass.replaceAll("^\\d+\\.", "");
		    result = thirdPass.substring(0, 8);
		}
		catch (PatternSyntaxException e) {
			throw new FileException(e);
		}
		return result;
	}
	
	@Deprecated
	public static Date getTimeStamp(final String name) throws FileException {
        String result = name.substring(59, 72);
		String result1 = result.replaceAll("_", "");
		String result2 = result1 + "00";
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddkkmmss");
		Date date = null;
		
		try {
			date = format.parse(result2);
		} 
		catch (ParseException e) {
			throw new FileException(e);
		}
		return date;
	}
}
