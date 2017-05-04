package com.softwarelikeyou.exception;

import java.io.Serializable;

public class DailyException extends LocalizedException { 
	private static final long	serialVersionUID	= 1L;

	public DailyException() {
		super();
	}
	
	public DailyException(String msg) {
		super(msg);
	}
	
	public DailyException(String msgKey, Serializable... varargs) {
		super(msgKey, varargs);
	}
	
	public DailyException(Throwable t, String msgKey) {
		super(t, msgKey);
	}

	public DailyException(Throwable t, String msgKey, Serializable... varargs) {
		super(t, msgKey, varargs);
	}	
	
	public DailyException(Throwable t) {
		super(t);
	}
}
