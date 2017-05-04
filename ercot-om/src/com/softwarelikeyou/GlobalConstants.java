package com.softwarelikeyou;

import mazz.i18n.Msg;

public interface GlobalConstants {

	public static final String				DEFAULT_MESSAGE_BUNDLE				= "messages";
	public static final Msg.BundleBaseName	DEFAULT_MESSAGE_BUNDLE_BASE_NAME	= new Msg.BundleBaseName(DEFAULT_MESSAGE_BUNDLE);
	public static final String				DEFAULT_USERNAME					= "root";
	public static final String				DEFAULT_PASSWORD					= "password";	
	public static final String              DEFAULT_SALT						= "4g$h&MqL)";
	
	public static final Integer				USER_LIMIT							= 5;
}
