package com.griddatabase.model.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.softwarelikeyou.exception.ZipCodeException;
import com.softwarelikeyou.facade.CountyContourFacade;
import com.softwarelikeyou.facade.ZipCodeFacade;
import com.softwarelikeyou.model.entity.CountyContour;
import com.softwarelikeyou.model.entity.ZipCode;
import com.softwarelikeyou.model.entity.ZipCodeTemperature;

public class CountyContourCache {

	private static Logger logger = Logger.getLogger(CountyContourCache.class);
	private static Map<String, List<CountyContour>> contourMap = new HashMap<String, List<CountyContour>>();
	private static Map<String, Color> colorMap = new HashMap<String, Color>();

	private CountyContourCache() { }
	
	private static CountyContourCache instance = null;
	
	private static boolean start = false;
	
	private static StringBuffer countyKML;
	
	public static boolean isStarted() {
		return start;
	}
	
	public static CountyContourCache getInstance() {
		if (instance == null || !start) {
			instance = new CountyContourCache();
			start();
			logger.info("Start County Contour Cache");
		}
		return instance;
	}
	
	public static void start() {
		try {		
			for (String namePart : CountyContourFacade.findNameParts())
			    contourMap.put(namePart, CountyContourFacade.findByNamePart(namePart));
			if (contourMap.size() > 0)
				start = true;
			countyKML = retrieveCountyKML();
		}
		catch (Exception e) {
			logger.error("CountyContour Cache", e);
		}
	}
	
	public static void stop() {
		try {
			start = false;
			contourMap.clear();
			colorMap.clear();
		}
		catch (Exception e) {
			logger.error("CountyContour Cache", e);
		}
	}
	
	public static List<String> getNameParts() {
		List<String> results = new ArrayList<String>();
		if (start) {
			for (String key : contourMap.keySet())
				results.add(key);
		}
		return results;
	}
	
	public static List<CountyContour> getContours(final String namePart) {
		List<CountyContour> list = new ArrayList<CountyContour>();
		if (start && namePart != null) {
			if (contourMap.containsKey(namePart))
			    list = contourMap.get(namePart);
		}
		return list;
	}
	
	public static void setColor(final ZipCodeTemperature zipCodeTemperature) throws ZipCodeException {
		Color color = Color.GREEN;
		if (zipCodeTemperature.getTemperature() >= 90)
			color = Color.RED;
		if (zipCodeTemperature.getTemperature() >= 80 && zipCodeTemperature.getTemperature() < 90)
			color = Color.YELLOW;
		if (zipCodeTemperature.getTemperature() >= 60 && zipCodeTemperature.getTemperature() < 80)
			color = Color.GREEN;
		if (zipCodeTemperature.getTemperature() < 60)
			color = Color.BLUE;
		ZipCode zipCode = ZipCodeFacade.findByName(zipCodeTemperature.getName());
		colorMap.put(zipCode.getCounty(), color);
	}
	
	public static Color getColor(final String county) {
		Color results = Color.GREEN;
		results = colorMap.get(county);
		if (results == null)
			results = Color.GREEN;
		return results;
	}
	
	public enum Color {

		RED("#transRedPoly"),
		BLUE("#transBluePoly"),
		GREEN("#transGreenPoly"),
		YELLOW("#transYellowPoly");
		
		private String dataKey;

		private Color(String dataKey) {
			this.dataKey = dataKey;
		}

		public String getKey() {
			return dataKey;
		}
		
		public static Color fromString(final String value) {
			for (Color c : Color.values()) {
				if (c.dataKey.equals(value)) 
					return c;
			}
			return null;
		}
	}
	
	public static StringBuffer getCountyKML() {
		return countyKML;
	}
	
	private static StringBuffer retrieveCountyKML() {
		StringBuffer out = new StringBuffer();
		out.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        out.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\" xmlns:kml=\"http://www.opengis.net/kml/2.2\" xmlns:atom=\"http://www.w3.org/2005/Atom\">");
        out.append(" <Document xmlns=\"\">");
        out.append("  <name>OverlayTexas.kml</name>");
        out.append("  <Style id=\"transRedPoly\"><LineStyle><width>1.5</width></LineStyle><PolyStyle><color>7d0000ff</color></PolyStyle></Style>");
        out.append("  <Style id=\"transBluePoly\"><LineStyle><width>1.5</width></LineStyle><PolyStyle><color>7dff0000</color></PolyStyle></Style>");
        out.append("  <Style id=\"transGreenPoly\"><LineStyle><width>1.5</width></LineStyle><PolyStyle><color>7d00ff00</color></PolyStyle></Style>");
        out.append("  <Style id=\"transYellowPoly\"><LineStyle><width>1.5</width></LineStyle><PolyStyle><color>7d00ffff</color></PolyStyle></Style>");
		for (String countyPart : CountyContourCache.getNameParts()) {
			out.append("  <Placemark>");
			out.append("   <name>" + countyPart + "</name>");
			out.append("   <visibility>0</visibility>");
			String[] parts = countyPart.split("=");
			out.append("   <styleUrl>" + CountyContourCache.getColor(parts[0].toUpperCase()).getKey() + "</styleUrl>");
			out.append("   <Polygon>");
			out.append("    <extrude>0</extrude>");
			out.append("    <altitudeMode>clampToGround</altitudeMode>");
			out.append("    <outerBoundaryIs>");
			out.append("     <LinearRing>");
			out.append("      <coordinates>");
			for (CountyContour contour : CountyContourCache.getContours(countyPart)) 
				out.append("       " + contour.getCoordinate() + ",0");
			out.append("      </coordinates>");
	        out.append("     </LinearRing>");
	        out.append("    </outerBoundaryIs>");
	        out.append("   </Polygon>");
	        out.append("  </Placemark>");
		}
		out.append(" </Document>");
        out.append("</kml>");
        return out;
	}
}
