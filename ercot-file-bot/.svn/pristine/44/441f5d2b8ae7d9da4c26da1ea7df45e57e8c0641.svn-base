package com.griddatabase.bot.util;

import java.util.regex.PatternSyntaxException;

import com.griddatabase.bot.exception.FileException;

public class FileUtil {

	private static FileUtil instance = new FileUtil();
	
	private FileUtil() { }
	
	public static OSType OS = OSType.LINUX;
	
	public static OSType getOSType() {
		return OS;
	}
	
	public static String BASE_DIR = "/home/ercot/data/";
	
	public enum FileType {
		UNKNOWN, 
		ASCPC,
		RTDAM,
		RTSPP,
		H48DAMHP,
		H48DAMAS,
		RTLMP; 
	}
	
	static {
		String osString = System.getProperty("os.name").toLowerCase();
		if (osString.indexOf("nix") >= 0 || osString.indexOf("nux") >= 0)
			OS = OSType.LINUX;
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
			throw new FileException("Could not parse file date", e);
		}
		return result;
	}
}
