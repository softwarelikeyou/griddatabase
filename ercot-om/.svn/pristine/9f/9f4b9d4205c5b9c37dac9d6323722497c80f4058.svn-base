package com.softwarelikeyou.model.entity.rtlmp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.softwarelikeyou.model.entity.Helper;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU15M;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU1H;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU24H;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU30M;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU5M;

public class RTLMPHelper extends Helper {

	private static Map<String, Class<? extends RTLMP>> classMap = new HashMap<String, Class<? extends RTLMP>>();
	
	static {
		classMap.put("HU5M", RTLMPHU5M.class);
		classMap.put("HU15M", RTLMPHU15M.class);
		classMap.put("HU30M", RTLMPHU30M.class);
		classMap.put("HU1H", RTLMPHU1H.class);
		classMap.put("HU24H", RTLMPHU24H.class);
	}
	
	public static Class<? extends RTLMP> getClass(final String key) {
		if (key == null)
			return null;
		return classMap.get(key);
	}
	
	public static boolean validateDate(final String date) {
		boolean results = true;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd kk:mm:ss");
			format.parse(date);
		}
		catch (ParseException e) {
			results = false;
		}
		return results;
	}
	
	public static boolean validateClass(final String key) {
		return getClass(key) == null ? false : true;
	}
	
}
