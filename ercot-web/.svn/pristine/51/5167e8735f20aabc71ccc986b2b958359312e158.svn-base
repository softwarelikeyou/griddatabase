package com.softwarelikeyou.viewcontroller.converter;

import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zkplus.databind.TypeConverter;
import org.zkoss.zul.Toolbarbutton;

public class SidebarStyleClassConverter implements TypeConverter {

	public Object coerceToBean(Object arg0, Component arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object coerceToUi(Object value, Component arg1) {
		
		if( value == null ) return "";
		
		if( value instanceof Toolbarbutton ) { 
			Toolbarbutton button = (Toolbarbutton) value;
			String contextPath = ((HttpServletRequest) Executions.getCurrent().getNativeRequest()).getRequestURL().toString();
			return contextPath.contains(button.getHref()) ? "sly-sidebar-item-highlighted" : "sly-sidebar-item"; 
		}
		else { 
			throw new IllegalArgumentException("object:" + value + " of type:" + value.getClass().getName() + "; expected type: " + String.class.getName());
		}
		
	}

}
