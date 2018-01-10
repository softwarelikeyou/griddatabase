package com.griddatabase.viewcontroller.filter;

import java.io.Serializable;
import java.util.List;

public interface ListFilter <T extends Serializable> {

	public List<T> filter(List<T> list);
	
	public void addPropertyFilter(String property, Filter<?> filter);
	
}
