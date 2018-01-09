package com.softwarelikeyou.exception;

import java.io.Serializable;

import com.softwarelikeyou.GlobalConstants;

public class LocalizedException extends mazz.i18n.exception.LocalizedException {
	private static final long serialVersionUID	= 1L;

	private String msgKey;
	private Serializable[] varargs = new Serializable[0];
	
	public LocalizedException() {
		super();
	}
	
	public LocalizedException(String msgKey) {
		super(GlobalConstants.DEFAULT_MESSAGE_BUNDLE_BASE_NAME, msgKey);
		this.msgKey = msgKey;
	}
	
	public LocalizedException(String msgKey, Serializable... varargs) {
		super(GlobalConstants.DEFAULT_MESSAGE_BUNDLE_BASE_NAME, msgKey, (Object[])varargs);
		this.msgKey = msgKey;
		this.varargs = varargs;
	}
	
	public LocalizedException(Throwable t, String msgKey) {
		super(t, GlobalConstants.DEFAULT_MESSAGE_BUNDLE_BASE_NAME, msgKey);
		this.msgKey = msgKey;
	}

	public LocalizedException(Throwable t, String msgKey, Serializable... varargs) {
		super(t, GlobalConstants.DEFAULT_MESSAGE_BUNDLE_BASE_NAME, msgKey, (Object[])varargs);
		this.msgKey = msgKey;
		this.varargs = varargs;
	}	
	
	public LocalizedException(Throwable t) {
		super(t);
	}
	
	public String getMsgKey() {
		return msgKey;
	}
	
	public Serializable[] getVarArgs() { 
		return varargs;
	}
	
}
