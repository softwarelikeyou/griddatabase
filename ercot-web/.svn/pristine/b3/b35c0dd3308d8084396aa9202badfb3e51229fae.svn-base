package com.softwarelikeyou.viewcontroller;

import com.softwarelikeyou.util.ResourceUtil;

public class FeatureUtil {
	
	public static String productHeaderString() { 
		return ServerVersion.PRODUCT;
	}
	
	public static String vendorCopyrightString() {
		return "&copy;" + ServerVersion.SERVER_VENDOR_COPYRIGHT;
	}
	
	public static String vendorWebsiteString() {
		return ServerVersion.SERVER_VENDOR_WEBSITE;
	}
	
	public static String helpWebsiteURL() { 
		return ServerVersion.SERVER_HELP_WEBSITE;
	}
	
	public static String signupWebsiteURL() { 
		return ServerVersion.SERVER_SIGNUP_WEBSITE;
	}
	
	public static String googleMapAPIKey() {
		try {
			return "zk.googleAPIkey='" + ResourceUtil.get().getString("google.maps.api.key") + "'";
		}
		catch (Exception x) {
			throw new RuntimeException(x);
		}
	}
}
