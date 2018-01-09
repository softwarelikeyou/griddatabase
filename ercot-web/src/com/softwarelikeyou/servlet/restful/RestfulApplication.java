package com.softwarelikeyou.servlet.restful;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.softwarelikeyou.model.entity.rtlmp.RTLMP;
import com.softwarelikeyou.servlet.restful.rtlmp.RTLMPRestful;

public class RestfulApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes =  new HashSet<Class<?>>();

	public RestfulApplication() {
		classes.add(RTLMPRestful.class);
		singletons.add(new RTLMPRestful());
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
