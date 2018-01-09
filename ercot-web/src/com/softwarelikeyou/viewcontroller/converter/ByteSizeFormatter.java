package com.softwarelikeyou.viewcontroller.converter;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.softwarelikeyou.viewcontroller.ELFunctions;
import com.softwarelikeyou.viewcontroller.SessionUtil;

public class ByteSizeFormatter implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	protected static final String numberDisplayFormat = "###.#";

	protected static Pattern parsePattern = Pattern.compile("^(\\d+(?:\\.\\d+)?)\\s+(\\S+)$");
	
	public static ByteUnit getDisplayUnit(Integer bytes) { 
		return getDisplayUnit(new Double(bytes));
	}
	
	public static ByteUnit getDisplayUnit(Long bytes) { 
		return getDisplayUnit(new Double(bytes));
	}
		
	public static ByteUnit getDisplayUnit(Double bytes) {
			
		ByteUnit displayUnit = ByteUnit.BYTES;
		
		for( ByteUnit unit : ByteUnit.values() ) {
			if( Math.abs(bytes) >= unit.getNumberOfBytes() ) {
				displayUnit = unit;
			}
			else {
				break;
			}
		}
		
		return displayUnit;
	}
	
	public static Double getDisplayBytes(Integer bytes) { 
		return getDisplayBytes(bytes.doubleValue());
	}
	
	public static Double getDisplayBytes(Long bytes) {
		return getDisplayBytes(bytes.doubleValue());
	}
	
	public static Double getDisplayBytes(Double bytes) { 
		ByteUnit unit = getDisplayUnit(bytes);
		return bytes.doubleValue() / unit.getNumberOfBytes().doubleValue();		
	}

	public static String getFriendlyString(Integer bytes) { 
		return getFriendlyString(new Double(bytes), SessionUtil.getSelectedLocale());
	}
	public static String getFriendlyString(Integer bytes, Locale locale) { 
		return getFriendlyString(new Double(bytes), locale);
	}
	
	public static String getFriendlyString(Long bytes) {
		return getFriendlyString(new Double(bytes), SessionUtil.getSelectedLocale());
	}
	
	public static String getFriendlyString(Double bytes) {
		return getFriendlyString(bytes, SessionUtil.getSelectedLocale());
	}
	
	public static String getFriendlyString(Long bytes, Locale locale) {
		return getFriendlyString(new Double(bytes), locale);
	}
	
	public static String getFriendlyString(Double bytes, Locale locale) {
		ByteUnit unit = getDisplayUnit(bytes);
		Double value = bytes.doubleValue() / unit.getNumberOfBytes().doubleValue();
		
		NumberFormat f = NumberFormat.getInstance(locale);
		if (f instanceof DecimalFormat) {
		     ((DecimalFormat) f).applyPattern(numberDisplayFormat);
		}
		
		StringBuffer sb = new StringBuffer(f.format(value));
		sb.append(" ");
		sb.append(ELFunctions.getLabel(locale, unit.getLabel()));
		return sb.toString();
	}
	
	public static String getFriendlyPerSecondString(Long bytes) {
		return getFriendlyPerSecondString(new Double(bytes), SessionUtil.getSelectedLocale());
	}	
	
	public static String getFriendlyPerSecondString(Long bytes, Locale locale) {
		return getFriendlyPerSecondString(new Double(bytes), locale);
	}	
	
	public static String getFriendlyPerSecondString(Double bytes, Locale locale) {
		ByteUnit unit = getDisplayUnit(bytes);
		Double value = bytes / unit.getNumberOfBytes();
		
		NumberFormat f = NumberFormat.getInstance(locale);
		if (f instanceof DecimalFormat) {
		     ((DecimalFormat) f).applyPattern(numberDisplayFormat);
		 }
		
		StringBuffer sb = new StringBuffer(f.format(value));
		sb.append(" ");
		sb.append(ELFunctions.getLabel(locale, unit.getPerSecondLabel()));
		return sb.toString();
	}
	
	public static Double getBytes(String formattedString) {
		
		Matcher matcher = parsePattern.matcher(formattedString);
		
		if( matcher.matches() ) {
			
			Double value  = Double.valueOf(matcher.group(1));
			ByteUnit unit = ByteUnit.fromLabel(matcher.group(2));
			
			if( unit == null ) {
				throw new IllegalArgumentException("Invalid units specified");
			}
			
			return value * unit.getNumberOfBytes();
		}
		else {
			throw new IllegalArgumentException("Invalid friendly byte string specified");
		}
		
	}
}
