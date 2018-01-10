package com.griddatabase.viewcontroller.filter;

import java.util.Date;

public class DateFilter implements Filter<Date> {

	static final long serialVersionUID = 1L;
	
	protected Boolean allowNull = false;
	protected Date begin = null;
	protected Date end = null; 
	
	public Boolean getAllowNull() {
		return allowNull;
	}
	
	public void setAllowNull(Boolean allowNull) {
		this.allowNull = allowNull;
	}
	
	public Date getBegin() {
		return begin;
	}
	
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	
	public Date getEnd() {
		return end;
	}
	
	public void setEnd(Date end) {
		this.end = end;
	}
	
	public boolean matches(Object value) {

		if( value == null ) return allowNull ? true : false;
		if( !(value instanceof Date) ) throw new IllegalArgumentException();
		
		Date date = (Date) value;
		
		if( begin != null && begin.after(date) ) return false;
		if( end != null && end.before(date) ) return false;
		
		return true;
	}
	
}
