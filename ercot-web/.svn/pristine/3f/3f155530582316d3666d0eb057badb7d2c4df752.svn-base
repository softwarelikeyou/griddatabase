package com.softwarelikeyou.viewcontroller;

import java.util.Locale;

import mazz.i18n.Msg;

public class ELFunctions {

	public enum IconSize {
		
		SMALL("small", "16x16"),
		MEDIUM("medium", "24x24"),
		LARGE("large", "32x32"),
		XL("xl", "48x48");

		private String stringValue;
		private String pixelValue;
		
		IconSize(String stringValue, String pixelValue) { 
			this.stringValue = stringValue;
			this.pixelValue = pixelValue;
		}
		
		public String getStringValue() {
			return stringValue;			
		}
		
		public String getPixelValue() {
			return pixelValue;
		}
		
		static IconSize fromString(String string) {
			
			for( IconSize size : IconSize.values() ) { 
				if( size.stringValue.equals(string) ) return size;
			}
			
			return null;
		}
	}
	
	public static String getImageURL(String iconType, String iconSize) { 
		
		IconSize size = IconSize.fromString(iconSize);
		
		if( size == null ) throw new IllegalArgumentException("Invalid icon size, must be: small, medium, or large");
		
		StringBuilder builder = new StringBuilder(WebConstants.BASE_IMAGE_URL);
		builder.append("/").append(iconType).append("-");
		builder.append(size.getPixelValue()).append(WebConstants.DEFAULT_IMAGE_EXT);
		
		return builder.toString();
	}
	
	public static String getLabel(String labelKey) {
		return getLabel(SessionUtil.getSelectedLocale(), labelKey);
	}
	
	public static String getLabelWithParams(String labelKey, Object... varargs) {
		return getLabelWithParams(SessionUtil.getSelectedLocale(), labelKey, varargs);
	}
	
	public static String getMessage(String msgKey) {
		return getMessage(SessionUtil.getSelectedLocale(), msgKey);
	}
	
	public static String getLabel(Locale locale, String labelKey) {
		return Msg.createMsg(locale, labelKey).toString();
	}
	
	public static String getLabelWithParams(Locale locale, String labelKey, Object... varargs) {
		return Msg.createMsg(locale, labelKey, varargs).toString();
	}
	
	public static String getMessage(Locale locale, String msgKey) {
		return Msg.createMsg(locale, msgKey).toString();
	}
	
	public static String getMessageWithParams(String msgKey, Object... varargs) { 
		return Msg.createMsg(SessionUtil.getSelectedLocale(), msgKey, varargs).toString(); 
	}
	
	public static String getMessageWithParams(Locale locale, String msgKey, Object... varargs) { 
		return Msg.createMsg(locale, msgKey, varargs).toString(); 
	}
	
	public static Object getComponentID(String classPath, String fieldID){
		
        try {
            Class<?> clz = Class.forName(classPath);
            Object obj = clz.getMethod("getFieldID", String.class).invoke(null, fieldID);
            return obj;
        }
        catch(Exception x) {
            throw new RuntimeException(x);
        }
        
    }
	
	public static Object getStaticField(String name){
		
        try {
            int i = name.lastIndexOf(".");
            String field = name.substring(i+1,name.length());
            name = name.substring(0,i);
            Class<?> clz = Class.forName(name);
            Object obj = clz.getField(field).get(null);
            return obj;
        }
        catch(Exception x) {
            throw new RuntimeException(x);
        }
        
    }
}
