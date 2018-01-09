package com.softwarelikeyou.collector.saver;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class Saver {

	protected Map<String, String> map = new HashMap<String, String>();
	
	public void setMap(Map<String, String> value) {
		this.map = value;
	}
	
	public Map<String, String> getMap() {
		return this.map;
	}
	
	public abstract boolean execute();
	
	public static String DATEFORMAT = "MM/dd/yyyy HH:mm";

	public static DecimalFormat form = new DecimalFormat("#####.##"); 

	private static DateFormat df = new SimpleDateFormat(DATEFORMAT); 
	
	public static Date getIntervalDateTime(final String dateString) throws ParseException {
        return df.parse(dateString);
	}
	
	public static Float toFloat(final String value) throws NumberFormatException {
		Float result = new Float(form.format(0.00f));
		result = Float.valueOf(value);
		return result;
	}
	
	public static float formatFloat(final float value) throws NumberFormatException {
		return toFloat(form.format(value));
	}
	
	public String filterDeliveryMinutes(final String deliveryInterval) {
		if (deliveryInterval.equals("1"))
		    return "15";
		if (deliveryInterval.equals("2"))
			return "30";
		if (deliveryInterval.equals("3"))
			return "45";
		if (deliveryInterval.equals("4"))
			return "00";
		return "00";
	}
	
	public static String filterDeliveryMinutes(final Integer deliveryInterval) {
		if (deliveryInterval == null)
			return "00";
		
		switch (deliveryInterval) {
		    case 1:
		    	return "15";
		    case 2:
			    return "30";
		    case 3:
			    return "45";
		    case 4:
			    return "00";
		    default:
			    return "00";
		}
	}
}
