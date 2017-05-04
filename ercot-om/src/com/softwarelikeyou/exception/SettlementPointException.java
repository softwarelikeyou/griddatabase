package com.softwarelikeyou.exception;

import java.io.Serializable;

public class SettlementPointException extends LocalizedException { 
	private static final long	serialVersionUID	= 1L;

	public SettlementPointException() {
		super();
	}
	
	public SettlementPointException(String msg) {
		super(msg);
	}
	
	public SettlementPointException(String msgKey, Serializable... varargs) {
		super(msgKey, varargs);
	}
	
	public SettlementPointException(Throwable t, String msgKey) {
		super(t, msgKey);
	}

	public SettlementPointException(Throwable t, String msgKey, Serializable... varargs) {
		super(t, msgKey, varargs);
	}	
	
	public SettlementPointException(Throwable t) {
		super(t);
	}		
	
}
