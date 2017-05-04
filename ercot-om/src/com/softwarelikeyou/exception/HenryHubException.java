package com.softwarelikeyou.exception;

import java.io.Serializable;

public class HenryHubException extends LocalizedException { 
	private static final long	serialVersionUID	= 1L;

	public HenryHubException() {
		super();
	}
	
	public HenryHubException(String msg) {
		super(msg);
	}
	
	public HenryHubException(String msgKey, Serializable... varargs) {
		super(msgKey, varargs);
	}
	
	public HenryHubException(Throwable t, String msgKey) {
		super(t, msgKey);
	}

	public HenryHubException(Throwable t, String msgKey, Serializable... varargs) {
		super(t, msgKey, varargs);
	}	
	
	public HenryHubException(Throwable t) {
		super(t);
	}		
	
}
