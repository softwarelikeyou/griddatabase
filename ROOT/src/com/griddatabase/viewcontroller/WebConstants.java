package com.griddatabase.viewcontroller;

import java.util.regex.Pattern;

public interface WebConstants {
	
	public static final String DEFAULT_SECURITY_REDIRECT = "/login.zul";
	
	public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$", Pattern.CASE_INSENSITIVE);

}
