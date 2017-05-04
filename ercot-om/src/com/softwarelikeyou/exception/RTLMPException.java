package com.softwarelikeyou.exception;

import java.io.Serializable;

public class RTLMPException extends LocalizedException { 
	private static final long	serialVersionUID	= 1L;

	public RTLMPException() {
		super();
	}
	
	public RTLMPException(String msg) {
		super(msg);
	}
	
	public RTLMPException(String msgKey, Serializable... varargs) {
		super(msgKey, varargs);
	}
	
	public RTLMPException(Throwable t, String msgKey) {
		super(t, msgKey);
	}

	public RTLMPException(Throwable t, String msgKey, Serializable... varargs) {
		super(t, msgKey, varargs);
	}	
	
	public RTLMPException(Throwable t) {
		super(t);
	}		
	
}
