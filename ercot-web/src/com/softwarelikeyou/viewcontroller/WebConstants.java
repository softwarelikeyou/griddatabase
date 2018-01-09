package com.softwarelikeyou.viewcontroller;

import java.util.regex.Pattern;

public interface WebConstants {
	
	public static final String BASE_IMAGE_URL = "/images";
	public static final String DEFAULT_IMAGE_EXT = ".png";

	public static final String STAGE_INDICATOR_IMAGE_RUNNING = "process";
	public static final String STAGE_INDICATOR_IMAGE_FINISHED = "check";
	public static final String STAGE_INDICATOR_IMAGE_TODO = "clipboard-empty";	
	public static final String STAGE_INDICATOR_IMAGE_ERROR = "error";
	
	public static final String STAGE_INDICATOR_IMAGE_SIZE = "small";
	
	public static final String AJAX_INDICATOR_NEUTRAL = "neutral";
	public static final String AJAX_INDICATOR_ACTIVE = "enabled";
	
	public static final String ERROR_MSGS_URL = "/error-msgs.zul";
	
	public static final String DEFAULT_SECURITY_REDIRECT = "/";
	
	public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$", Pattern.CASE_INSENSITIVE);
	
	public static final String EMPTY_STRING = "";
	public static final String DOUBLE_DASH = "--";
	public static final String X_OUT_OF_Y_FORMAT = "%d / %d";
}
