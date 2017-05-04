package com.softwarelikeyou.exception;

import java.io.Serializable;

public class WeatherException extends LocalizedException { 
	private static final long	serialVersionUID	= 1L;

	public WeatherException() {
		super();
	}
	
	public WeatherException(String msg) {
		super(msg);
	}
	
	public WeatherException(String msgKey, Serializable... varargs) {
		super(msgKey, varargs);
	}
	
	public WeatherException(Throwable t, String msgKey) {
		super(t, msgKey);
	}

	public WeatherException(Throwable t, String msgKey, Serializable... varargs) {
		super(t, msgKey, varargs);
	}	
	
	public WeatherException(Throwable t) {
		super(t);
	}		
	
}
