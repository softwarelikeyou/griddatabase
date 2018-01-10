package com.griddatabase.viewcontroller.filter;

import java.io.Serializable;

public interface Filter <T extends Serializable> extends Serializable {

	public boolean matches(Object value);
	
}
