package com.softwarelikeyou.exception;

import java.io.Serializable;

public class H48DAMASException extends LocalizedException { 
	private static final long	serialVersionUID	= 1L;

	public H48DAMASException() {
		super();
	}
	
	public H48DAMASException(String msg) {
		super(msg);
	}
	
	public H48DAMASException(String msgKey, Serializable... varargs) {
		super(msgKey, varargs);
	}
	
	public H48DAMASException(Throwable t, String msgKey) {
		super(t, msgKey);
	}

	public H48DAMASException(Throwable t, String msgKey, Serializable... varargs) {
		super(t, msgKey, varargs);
	}	
	
	public H48DAMASException(Throwable t) {
		super(t);
	}		
	
}
