package com.softwarelikeyou.exception;

import java.io.Serializable;

public class RTSPPException extends LocalizedException { 
	private static final long	serialVersionUID	= 1L;

	public RTSPPException() {
		super();
	}
	
	public RTSPPException(String msg) {
		super(msg);
	}
	
	public RTSPPException(String msgKey, Serializable... varargs) {
		super(msgKey, varargs);
	}
	
	public RTSPPException(Throwable t, String msgKey) {
		super(t, msgKey);
	}

	public RTSPPException(Throwable t, String msgKey, Serializable... varargs) {
		super(t, msgKey, varargs);
	}	
	
	public RTSPPException(Throwable t) {
		super(t);
	}		
	
}
