package com.softwarelikeyou.exception;

import java.io.Serializable;

public class RTDAMException extends LocalizedException { 
	private static final long	serialVersionUID	= 1L;

	public RTDAMException() {
		super();
	}
	
	public RTDAMException(String msg) {
		super(msg);
	}
	
	public RTDAMException(String msgKey, Serializable... varargs) {
		super(msgKey, varargs);
	}
	
	public RTDAMException(Throwable t, String msgKey) {
		super(t, msgKey);
	}

	public RTDAMException(Throwable t, String msgKey, Serializable... varargs) {
		super(t, msgKey, varargs);
	}	
	
	public RTDAMException(Throwable t) {
		super(t);
	}		
	
}
